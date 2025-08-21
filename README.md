# order-app-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

#### dependency module boot
    <!-- Observability optional -->
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-micrometer-registry-prometheus</artifactId>
    </dependency>
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-arc</artifactId>
    </dependency>

    <!-- All Internal modules -->

#### dependency module domain
    <!-- Observability optional -->
     <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-arc</artifactId>
    </dependency>

#### dependency module application
     <!-- module domain-->

#### dependency module rest
    <!-- module application-->

#### dependency module BD
    <!-- module domain-->
  
    private OrderEntity toEntity(Order order) {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCode(order.getOrderCode().value());
        orderEntity.setDescription(order.getOrderDescription().value());
        orderEntity.setItems(order.getItemList().stream().map(items -> {
        final OrderLineEntity itemEntity = new OrderLineEntity();
        itemEntity.setModel(items.modelo());
        itemEntity.setQuality(items.calidad());
        itemEntity.setQuantity(items.quantity());
        return itemEntity;
        }).toList());

		return orderEntity;
	}


#### dependency module Kafka in
     <!-- module application-->

#### dependency module Kafka out
     <!-- module domain-->

## Minikube create topic en panda

kubectl exec -it redpanda-0 -n applications -- rpk topic create orders -p 1 -r 1
kubectl exec -it redpanda-0 -n applications -- rpk topic create products -p 1 -r 1

kubectl exec -it redpanda-0 -n applications -- rpk topic list

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/order-app-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

