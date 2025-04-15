# Build stage (uses Maven to compile the JAR)
FROM amazoncorretto:17.0.6-al2023 as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime stage (only needs the JAR + JRE)
FROM amazoncorretto:17.0.6-al2023
WORKDIR /app
COPY --from=builder /app/target/dietcompass-*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]