FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY start.sh /usr/bin/start
RUN chmod +x /usr/bin/start

RUN addgroup -S gdd && adduser -S gdd -G gdd

#RUN useradd --system -m -s /bin/false gdd
USER gdd
COPY *.jar /home/gdd
WORKDIR /home/gdd
CMD ["/usr/bin/start"]

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx2G","-jar","service.jar"]
