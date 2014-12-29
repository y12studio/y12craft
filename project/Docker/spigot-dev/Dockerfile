FROM y12docker/spigot:1.8.141230
# https://github.com/y12studio/y12docker/tree/master/spigot
#
# install maven
#
ENV MAVEN_VERSION 3.2.3
ENV M2_HOME /opt/maven
ENV M2 $M2_HOME/bin

RUN wget -qO- http://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz  | tar xvz -C /tmp && \
    mv /tmp/apache-maven-* $M2_HOME && \
    chmod +x $M2/mvn

#
# install gradle
#
ENV GRADLE_VERSION 2.1
ENV GRADLE_HOME /opt/gradle
RUN wget -qO- https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip  -O /tmp/gradle.zip && \
    unzip -d /tmp /tmp/gradle.zip &&\
    mv /tmp/gradle-* $GRADLE_HOME

ENV PATH $PATH:$JAVA_HOME/bin:$M2:$GRADLE_HOME/bin
