# 1. Alap image, ami tartalmazza a Jenkins-t és az OpenJDK-t
FROM jenkins/jenkins:lts

# Válts root felhasználóra az apt-get parancsokhoz
USER root

# Frissítés és az OpenJDK 17, Git, Maven, Supervisor telepítése
RUN apt-get update && apt-get install -y openjdk-17-jdk supervisor git maven && apt-get clean

# 2. A projekt kódjának letöltése GitHub-ról a main branchról
WORKDIR /app
RUN git clone -b main https://github.com/martinkovacs22/myfirstspring.git .

# 3. A Maven build futtatása (JAR fájl készítése)
RUN mvn clean package

# 4. Supervisord beállítások másolása
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# 5. A JAR fájl másolása a target mappából a konténerbe
COPY target/web-0.0.1-SNAPSHOT.jar /app/app.jar

# 6. A konténer indításakor a supervisor fogja kezelni a Jenkins és az alkalmazás futtatását
ENTRYPOINT ["/usr/bin/supervisord"]
