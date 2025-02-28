FROM openjdk:21-jdk-slim

ENV JAVA_HOME=/usr/local/openjdk-21
ENV PATH="${JAVA_HOME}/bin:${PATH}"

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src
# Copia o .env para dentro do container
COPY .env /app/.env  

RUN ./mvnw clean package

CMD ["sh", "-c", "java -jar $(ls target/*.jar | head -n 1)"]
