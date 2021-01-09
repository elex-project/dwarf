plugins {
	java
	`java-library`
	`maven-publish`
	id("com.github.ben-manes.versions") version "0.36.0"
}

group = "com.elex-project"
version = "1.0-SNAPSHOT"
description = ""

// Repository credential, Must be defined in ~/.gradle/gradle.properties
val repoUser : String by project
val repoPassword : String by project

repositories {
	maven {
		credentials {
			username = repoUser
			password = repoPassword
		}
		url = uri("https://repository.elex-project.com/repository/maven/")
	}

}

java {
	withSourcesJar()
	withJavadocJar()
	sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
	targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
}

configurations {
	compileOnly {
		extendsFrom(annotationProcessor.get())
	}
	testCompileOnly {
		extendsFrom(testAnnotationProcessor.get())
	}
}

tasks.jar {
	manifest {
		attributes(mapOf(
				"Implementation-Title" to project.name,
				"Implementation-Version" to project.version,
				"Implementation-Vendor" to "ELEX co.,pte.",
				"Automatic-Module-Name" to "${project.group}.${project.name}"
		))
	}
}

tasks.compileJava {
	options.encoding = "UTF-8"
}

tasks.compileTestJava {
	options.encoding = "UTF-8"
}

tasks.test {
	useJUnitPlatform()
}

tasks.javadoc {
	if (JavaVersion.current().isJava9Compatible) {
		(options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
	}
	(options as StandardJavadocDocletOptions).encoding = "UTF-8"
	(options as StandardJavadocDocletOptions).charSet = "UTF-8"
	(options as StandardJavadocDocletOptions).docEncoding = "UTF-8"

}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
			pom {
				// todo
				name.set(project.name)
				description.set(project.description)
				url.set("https://")
				properties.set(mapOf(
						"myProp" to "value",
						"prop.with.dots" to "anotherValue"
				))
				licenses {
					license {
						// todo
						name.set("licenseName")
						url.set("licenseUrl")
					}
				}
				developers {
					developer {
						id.set("elex-project")
						name.set("Elex")
						email.set("developer@elex-project.com")
					}
				}
				scm {
					// todo
					connection.set("scm:git:https://github.com/my-library.git")
					developerConnection.set("scm:git:https://github.com/my-library.git")
					url.set("https://github.com/my-library/")
				}
			}
		}
	}

	repositories {
		maven {
			name = "mavenLocal"
			url = uri("file://${buildDir}/repo")
		}
		maven {
			name = "mavenElex"
			val releaseUrl = uri("https://repository.elex-project.com/repository/maven-releases/")
			val snapshotUrl = uri("https://repository.elex-project.com/repository/maven-snapshots/")
			url = if (version.toString().endsWith("SNAPSHOT") ) snapshotUrl else releaseUrl

			credentials {
				username = repoUser
				password = repoPassword
			}
		}
	}
}

dependencies {
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
	implementation("org.slf4j:slf4j-api:1.7.30")
	implementation("org.jetbrains:annotations:20.1.0")

	compileOnly("org.projectlombok:lombok:1.18.16")
	annotationProcessor("org.projectlombok:lombok:1.18.16")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.16")

	testImplementation("ch.qos.logback:logback-classic:1.2.3")
	testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.register("printHello") {
	group = "elex"
	doLast {
		println("-?$repoUser")
		println("Hello")

	}
}