FROM y12craft/spigotdev:1.8.141230
#
# https://github.com/y12studio/y12docker/tree/master/spigot
# https://github.com/y12studio/y12craft/Docker/spigot-dev
# Install plugin

ENV APP_DIR /opt/spigot
ENV GIT_DIR /opt/git

RUN mkdir $GIT_DIR && cd $GIT_DIR && \
    git clone --depth 1 https://github.com/y12studio/y12craft &&\
    cd y12craft/project/DropDiamond &&\
    sed -i 's|../libs|/opt/spigot|' build.gradle &&\
    gradle jar &&\
    mkdir $APP_DIR/plugins &&\
    mv build/libs/DropDiamond-1.0.jar $APP_DIR/plugins/
