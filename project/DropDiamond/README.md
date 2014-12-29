ref [FreeThePlugins/GappleDropFree](https://github.com/FreeThePlugins/GappleDropFree)

move spigot-1.8.jar to local libs

local spigot-1.8.jar ref [java - How to add local .jar file dependency to build.gradle file? - Stack Overflow](http://stackoverflow.com/questions/20700053/how-to-add-local-jar-file-dependency-to-build-gradle-file)

ls build/libs/DropDiamond-1.0.jar

## Test Report

```
$ docker build --no-cache=true -t y12craft/ddiamond .
$ docker run y12craft/ddiamond ls /opt/git/y12craft/project/DropDiamond/libs
README.md
spigot-1.8.jar
$ docker run y12craft/ddiamond ls /opt/spigot/plugins
DropDiamond-1.0.jar
$ CID=$(docker run -d -p=25565:25565 y12craft/ddiamond)
$ sudo docker-bash $CID

# cd /opt/spigot
# tail logs/latest.log
[12:06:43] [Server thread/INFO]: Preparing spawn area: 92%
[12:06:44] [Server thread/INFO]: Preparing spawn area: 97%
[12:06:44] [Server thread/INFO]: Preparing start region for level 2 (Seed: -6948303131020203335)
[12:06:45] [Server thread/INFO]: Preparing spawn area: 16%
[12:06:46] [Server thread/INFO]: Preparing spawn area: 35%
[12:06:47] [Server thread/INFO]: Preparing spawn area: 51%
[12:06:48] [Server thread/INFO]: Preparing spawn area: 68%
[12:06:49] [Server thread/INFO]: Preparing spawn area: 88%
[12:06:50] [Server thread/INFO]: [Y12CraftDropDiamond] Enabling Y12CraftDropDiamond v1.0

```
