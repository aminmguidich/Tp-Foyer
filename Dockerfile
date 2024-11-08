FROM ubuntu:latest
LABEL authors="hama"

ENTRYPOINT ["top", "-b"]