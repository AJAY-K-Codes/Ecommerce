plugins {
	java
	id("org.springframework.boot") version "4.1.1"
	id("io.spring.dependency-management") version "1.1.7"
	jacoco
}



group = "com.project.ecommerce"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	dependencies {
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
	}


}

tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}
jacoco{
	toolVersion="0.8.13"
}
tasks.jacocoTestReport{
	dependsOn(tasks.test)
	reports {
		xml.required.set(true)
		xml.outputLocation.set(file("reports/jacoco/Test/jacocoTestReports.xml"))
		html.required.set(true)
	}
}
