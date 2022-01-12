# Cronitor Ping API Client

Cronitor is a service for heartbeat-style monitoring of anything that can send an HTTP request. It's particularly well suited for monitoring cron jobs, Jenkins jobs, or any other scheduled task.

This java library provides a simple abstraction for the pinging of a Cronitor monitor. For a better understanding of the API this library talks to, please see our [Telemetry API docs](https://cronitor.io/docs/telemetry-api).

## Install
You can download the cronitor client JAR from the Maven central repository.
[https://repo.maven.apache.org/maven2/io/cronitor/client/1.4.0/](https://repo.maven.apache.org/maven2/io/cronitor/client/1.4.0/)

### Install with Maven
If you are using Maven, simply add this line in your pom.xml file :
```
<dependency>
    <groupId>io.cronitor</groupId>
    <artifactId>client</artifactId>
    <version>1.5.0</version>
</dependency>
```

### Usage with Spring

Declare a new bean in your Spring configuration :
```
<bean id="cronitorClient" class="io.cronitor.client.CronitorClient">
     <constructor-arg index="0" value="apiKey"/>
     <constructor-arg index="1" value="environment"/> // optional
</bean>
```

Then simply inject this bean into the class containing the routine to monitor :
```
@Resource
private CronitorClient cronitorClient;
```

### Usage without Spring
Simply declare a new CronitorClient instance in the class containing the routine to monitor:
```
CronitorClient cronitorClient = new CronitorClient('yourApiKey');

To send events to an environment other than the default ('production'), you can pass the environment as the second argument.
CronitorClient cronitorClient = new CronitorClient('yourApiKey', 'production');
```

### Examples

### send a run event (a job has started)
```java
    cronitorClient.run("nightly-data-export");
```

### send a complete event (a job has completed successfully)
```java
    cronitorClient.complete("nightly-data-export");
```

### send a failure event (a job has failed)
```java
    cronitorClient.fail("nightly-data-export");
```

### send a tick event (heartbeat events)
```java
    cronitorClient.tick("heartbeat-monitor");
```

### pause a monitor
```java
    cronitorClient.pause("nightly-data-export", numberOfHours);
```

### unpause a monitor
```java
    cronitorClient.unpause("nightly-data-export");
```

### including messages
```java
    // each event method supports an optional message param
    cronitorClient.run("nightly-data-export", "Started by user 123");

    cronitorClient.complete("nightly-data-export", "Alive!");

    cronitorClient.fail("nightly-data-export", e.printStackTrace());
```

### including metrics
```java
    Map<String, Integer> metrics = new HashMap<String, Integer>() {
            {
                put("count", 100);
                put("error_count", 5);
            }
        };
    // each event method supports an optional message param
    cronitorClient.complete("nightly-data-export", "ok", metrics);

    cronitorClient.fail("nightly-data-export", e.printStackTrace(), metrics);
```

## Development

The contained Dockerfile/docker-compose.yml file will allow you to build and test this library. Build the container with with `docker-compose build`. Run tests with `docker-compose up`.

If you want to share an idea, report an issue, or just say hello you can open an issue on this repo or email us at support@cronitor.io.

## Authors
- [@lboix](https://github.com/lboix)
- [@nnerny](https://github.com/nnerny)
- [@firone](https://github.com/firone)
- [@aflanagan](https://github.com/aflanagan)
- [@vcanuel](https://github.com/vcanuel)

