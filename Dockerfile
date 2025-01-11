# 1. Alap image - az OpenJDK 17-et használjuk
FROM jenkins/jenkins:lts

# Válts root felhasználóra az apt-get parancsokhoz
USER root

# Frissítés és az OpenJDK 17 telepítése
RUN apt-get update && apt-get install -y openjdk-17-jdk supervisor && apt-get clean

# 2. Alkalmazás JAR fájljának bemásolása az image-be
ARG JAR_FILE=target/elsoproject-1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# 3. A konténer indításakor a supervisor fogja kezelni a Jenkins és az alkalmazás futtatását
ENTRYPOINT ["/usr/bin/supervisord"]
