# Jenkins és a Spring Boot indítása supervisord-dal
FROM jenkins/jenkins:lts

USER root

# Frissítés, szükséges csomagok telepítése
RUN apt-get update && apt-get install -y openjdk-17-jdk git maven supervisor && apt-get clean

# A projekt kódjának letöltése
WORKDIR /app
RUN git clone --single-branch -b main https://github.com/martinkovacs22/myfirstspring.git /app/myfirstspring

# A Maven build futtatása
WORKDIR /app/myfirstspring/WebProject
RUN mvn clean install

# JAR fájlok másolása
COPY ./target/web-0.0.1-SNAPSHOT.jar /app/app.jar

# Supervisor konfiguráció másolása
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Két port megnyitása
EXPOSE 1001
EXPOSE 8081

# Supervisor elindítása
CMD ["/usr/bin/supervisord"]

# Add script to image
COPY init.sh /usr/local/bin/init.sh
RUN chmod +x /usr/local/bin/init.sh

# Override entrypoint to run init.sh
ENTRYPOINT ["/usr/bin/tini", "--", "/usr/local/bin/init.sh"]

#indito parancs
#docker run -d -p 1004:8080 -p 1005:8081 --name mycontainer myfirstspring