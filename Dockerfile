# Estágio 1: Build com Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o pom.xml e baixa as dependências para aproveitar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline
# Copia o código-fonte
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Execução com JRE
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/safe-boleto-api-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]