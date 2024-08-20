# Kafka Playground

## Introduction

Kafka의 여러가지 설정들과 Spring Boot의 설정 등을 테스트하기 위한 프로젝트입니다.
시스템은 크게 2가지 모듈로 구성됩니다.

- API : 이벤트 Produce
- Consumer : 이벤트 Consume

### Version Information

- Java `17`
- Spring Boot `3.1.6`
- Redis `7.0`

### Quick Start

로컬 환경에서 서비스를 실행합니다.

```shell
./run.sh
```

실행되는 컨테이너는 다음과 같습니다.

- api
    - Port: `8080`
- consumer
    - Port: `8081`
- redis
    - Port: `6379`
- kafka1, kafka2, kafka3
    - Port: `9092`, `9093`, `9094`
- kafka-ui
    - Port: `9999`

### Build

프로젝트를 빌드합니다.

```shell
# api application build
./gradlew :api:build

# consumer application build
./gradlew :consumer:build
```

### Local Setup

로컬 환경에서 서비스를 실행합니다.

`Redis`와 `Kafka`가 실행 중이어야 합니다. 다음 명령어를 통해 필요한 인프라를 실행합니다.

```shell
./setup.sh
```

### Tip

- [Kafka UI](http://localhost:9999)를 통해 Kafka Topic을 확인할 수 있습니다.

