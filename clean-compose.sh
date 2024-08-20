#!/bin/sh
# shellcheck disable=SC2086

root_dir=$(pwd)
docker_dir="$root_dir/local"

docker-compose -f $docker_dir/docker-compose-develop.yml --project-name=queue-develop down && \
docker-compose -f $docker_dir/docker-compose-e2e.yml --project-name=queue-e2e down
