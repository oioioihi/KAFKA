#!/bin/sh
# shellcheck disable=SC2112
# shellcheck disable=SC2034
# shellcheck disable=SC2086

root_dir=$(pwd)
docker_dir="$root_dir/local"
api_dir="$root_dir/api"
consumer_dir="$root_dir/consumer"
api_docker_image="api:local"
consumer_docker_image="consumer:local"

function image_exists() {
    image_name=$1
    if docker images --format '{{.Repository}}:{{.Tag}}' | grep -q "$image_name"; then
        return 0
    else
        return 1
    fi
}

function build_docker() {
    echo "Building Docker Images..."

    if ! image_exists "$api_docker_image"; then
        docker build -t $api_docker_image -f $api_dir/Dockerfile .
    else
        echo "$api_docker_image already exists. Skipping build."
    fi

    if ! image_exists "$consumer_docker_image"; then
        docker build -t $consumer_docker_image -f $consumer_dir/Dockerfile .
    else
        echo "$consumer_docker_image already exists. Skipping build."
    fi

    echo "Successfully! Docker Images build"
}

function run_e2e() {
    echo "Starting the QueueService..."
    docker-compose -f $docker_dir/docker-compose-e2e.yml --project-name=queue-e2e down && \
    docker-compose -f $docker_dir/docker-compose-e2e.yml --project-name=queue-e2e up -d
    echo "QueueService is running"
}

build_docker || { echo "Error: build_docker failed"; exit 1; }
run_e2e || { echo "Error: run_e2e failed"; exit 1; }