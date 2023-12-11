FROM bellsoft/liberica-runtime-container:jdk-all-17-slim-musl
RUN  ["mkdir","/upload","&&","mkdir","/template"]

EXPOSE 8020

COPY target/red-packet-0.0.1.jar /app.jar

ENTRYPOINT ["java","-Duser.timezone=GMT+08","-jar","/app.jar"]