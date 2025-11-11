plugins {
    id("java")
    id("maven-publish")
    id("io.freefair.lombok") version "9.0.0"
    id ("com.vanniktech.maven.publish") version "0.35.0"
}

group = "de.ztiger"
version = "1.0"

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
            groupId = project.group.toString()
            artifactId = "minimessage-parser"
            version = project.version.toString()
        }
    }
}

mavenPublishing {
    coordinates(group.toString(), name, version.toString())

    pom {
        name.set("Minimessage Parser")
        description.set("A simple minimessage parser for Minecraft plugins.")
        inceptionYear.set("2025")
        url.set("https://github.com/username/mylibrary/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
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
            url.set("https://github.com/zTig3r/MiniMessageParser/")
            connection.set("scm:git:git://github.com/zTig3r/MiniMessageParser.git")
            developerConnection.set("scm:git:ssh://git@github.com/zTig3r/MiniMessageParser.git")
        }
    }
}