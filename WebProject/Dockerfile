# 1. Alap image, ami tartalmazza a Jenkins-t és az OpenJDK-t
FROM jenkins/jenkins:lts

# Válts root felhasználóra az apt-get parancsokhoz
USER root

# Frissítés és az OpenJDK 17, Git, Maven telepítése
RUN apt-get update && apt-get install -y openjdk-17-jdk git maven && apt-get clean

# 2. A projekt kódjának letöltése GitHub-ról a main branchról
WORKDIR /app
RUN git clone -b main https://github.com/martinkovacs22/myfirstspring.git .

# 3. Átváltás a megfelelő mappába a Maven build futtatásához
WORKDIR /app/myfirstspring/WebProject

# A Maven build futtatása a Docker image építésekor
RUN mvn clean install

# 4. A JAR fájl másolása a konténerbe
COPY ./target/web-0.0.1-SNAPSHOT.jar /app/app.jar

# 5. Jenkins elindítása
ENTRYPOINT ["/usr/local/bin/jenkins.sh"]

# 6. Spring Boot alkalmazás elindítása
CMD ["java", "-jar", "/app/app.jar"]
