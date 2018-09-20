FROM openjdk:8-jdk-alpine

COPY start.sh /usr/bin/start
RUN chmod +x /usr/bin/start

RUN addgroup -S gdd && adduser -S gdd -G gdd
USER gdd
COPY *.jar /home/gdd
WORKDIR /home/gdd
CMD ["/usr/bin/start"]
