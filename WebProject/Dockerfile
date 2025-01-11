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

# 4. A Maven build futtatása (JAR fájl készítése)
RUN mvn clean package

# 5. A JAR fájl másolása a target mappából (a konténerbe)
COPY WebProject/target/web-0.0.1-SNAPSHOT.jar /app/app.jar

# 6. Jenkins elindítása háttérben
RUN nohup /usr/local/bin/jenkins.sh &

# 7. Spring Boot alkalmazás elindítása
CMD ["java", "-jar", "/app/app.jar"]
