#!/bin/bash

CONFIG_FILE="/var/jenkins_home/config.xml"

# Ellenőrizd, hogy a config.xml létezik-e
if [ -f "$CONFIG_FILE" ]; then
    echo "Jenkins config.xml found. Disabling security..."

    # Cseréld ki a <useSecurity>true</useSecurity>-t <useSecurity>false</useSecurity>-re
    sed -i 's/<useSecurity>true<\/useSecurity>/<useSecurity>false<\/useSecurity>/' "$CONFIG_FILE"

    # Töröld a <securityRealm> és <authorizationStrategy> blokkokat
    sed -i '/<authorizationStrategy/,/<\/authorizationStrategy>/d' "$CONFIG_FILE"
    sed -i '/<securityRealm/,/<\/securityRealm>/d' "$CONFIG_FILE"
else
    echo "No config.xml found. Skipping security configuration."
fi

# Indítsd el a supervisord-ot (ami elindítja a Jenkins-t és a Spring Boot-ot)
exec /usr/bin/supervisord -c /app/myfirstspring/WebProject/supervisord.conf
