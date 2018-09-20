FROM openjdk:8-jre

COPY core/build/install/core /srv/code
ENTRYPOINT ["/srv/code/bin/core"]