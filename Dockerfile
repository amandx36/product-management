# ==============================
# STAGE 1 : BUILD STAGE
# ==============================
# Is stage ka kaam sirf project build karna hai (JAR banana)

# Maven + Java 17 wali image use kar rahe hain
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Container ke andar working directory set kar rahe hain
WORKDIR /app

# Sirf pom.xml copy kar rahe hain pehle
# Taaki Docker dependencies cache kar sake
COPY pom.xml .

# Maven dependencies download kar leta hai
# Next build fast ho jata hai
RUN mvn dependency:go-offline

# Ab poora source code copy kar rahe hain
COPY src ./src

# Project build kar rahe hain (tests skip kar rahe hain for speed)
# Isse target/ folder ke andar .jar file ban jaati hai
RUN mvn clean package -DskipTests


# ==============================
# STAGE 2 : RUN STAGE
# ==============================
# Is stage me sirf app run hoti hai (lightweight image)

# Sirf Java Runtime Environment wali image
FROM eclipse-temurin:17-jre

# Container ke andar working directory
WORKDIR /app

# Build stage se sirf JAR file copy kar rahe hain
# Maven, source code wagairah nahi le ja rahe
COPY --from=build /app/target/*.jar app.jar

# Container ke andar app 8080 port pe chalegi
EXPOSE 8080

# Container start hote hi ye command run hogi
# Java application start ho jaayegi
ENTRYPOINT ["java", "-jar", "app.jar"]
