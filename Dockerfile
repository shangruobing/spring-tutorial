FROM bellsoft/liberica-runtime-container:jre-17-slim-musl

RUN  ["mkdir","/upload","&&","mkdir","/template"]

EXPOSE 8080

COPY target/spring-tutorial-0.0.1.jar /app.jar

ENTRYPOINT ["java","-Duser.timezone=GMT+08","-jar","/app.jar"]