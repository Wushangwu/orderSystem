FROM eclipse-temurin:8-jre
RUN mkdir -p /my-server
WORKDIR /my-server
COPY ["target/oauth2-server-0.0.1-SNAPSHOT-exec.jar", "app.jar"]
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms256m -Xmx256m -Djava.security.egd=file:/dev/./urandom"
ENV ARGS=""
EXPOSE 8080
CMD java ${JAVA_OPTS} -jar app.jar $ARGS
