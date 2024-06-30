plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.0"
    id("io.micronaut.aot") version "4.4.0"
}

version = "0.1"
group = "com.examplecrud"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("io.micronaut.servlet:micronaut-servlet-processor")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.data:micronaut-data-tx-hibernate")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-hibernate-jpa")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    compileOnly("io.micronaut:micronaut-http-client")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.h2database:h2")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass = "com.examplecrud.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false
micronaut {
    runtime("tomcat")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.examplecrud.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}


