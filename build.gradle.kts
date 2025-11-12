plugins {
    id("java")
    id("maven-publish")
    id("io.freefair.lombok") version "9.0.0"
}

group = "de.ztiger"
version = project.findProperty("version") ?: "dev"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.10-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = group.toString()
            artifactId = project.name
            version = version.toString()

            pom {
                name.set("Inventory Framework")
                description.set("A simple inventory framework for Minecraft plugins.")
                url.set("https://github.com/zTig3r/MiniMessageParser")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("ztiger")
                        name.set("zTig3r")
                        url.set("https://github.com/zTig3r/")
                    }
                }
                scm {
                    url.set("https://github.com/zTig3r/MiniMessageParser")
                    connection.set("scm:git:git://github.com/zTig3r/MiniMessageParser.git")
                    developerConnection.set("scm:git:ssh://git@github.com/zTig3r/MiniMessageParser.git")
                }
            }
        }
    }
}