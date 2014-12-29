
[y12docker/spigot at master · y12studio/y12docker](https://github.com/y12studio/y12docker/tree/master/spigot)

Mon Dec 29 19:50:42 CST 2014
```
$ docker build --no-cache=true -t y12craft/spigotdev:1.8.141230 .
$ docker run y12craft/spigotdev:1.8.141230 mvn --version
Apache Maven 3.2.3 (33f8c3e1027c3ddde99d3cdebad2656a31e8fdf4; 2014-08-11T20:58:10+00:00)
Maven home: /opt/maven
Java version: 1.8.0_25, vendor: Oracle Corporation
Java home: /usr/lib/jvm/java-8-oracle/jre
Default locale: zh_TW, platform encoding: UTF-8
OS name: "linux", version: "3.13.0-24-generic", arch: "amd64", family: "unix"

$ docker run y12craft/spigotdev:1.8.141230 gradle --version

------------------------------------------------------------
Gradle 2.1
------------------------------------------------------------

Build time:   2014-09-08 10:40:39 UTC
Build number: none
Revision:     e6cf70745ac11fa943e19294d19a2c527a669a53

Groovy:       2.3.6
Ant:          Apache Ant(TM) version 1.9.3 compiled on December 23 2013
JVM:          1.8.0_25 (Oracle Corporation 25.25-b02)
OS:           Linux 3.13.0-24-generic amd64


```
