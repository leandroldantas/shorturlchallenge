FROM payara/server-full
LABEL maintainer="SHORT URL CHALLENGE"
COPY SETUP/mysql-connector-java-5.1.47-bin.jar /opt/payara/glassfish/domains/domain1/lib/ext
COPY SETUP/glassfish-resources.xml /opt/payara/glassfish/bin

ENTRYPOINT ${PAYARA_PATH}/generate_deploy_commands.sh &&\
    echo "add-resources /opt/payara/glassfish/bin/glassfish-resources.xml" > mycommands.asadmin &&\
    echo "deploy /opt/deploy/short-url-1.0-SNAPSHOT.war" >> mycommands.asadmin &&\
    cat ${DEPLOY_COMMANDS} >> mycommands.asadmin &&\
    ${PAYARA_PATH}/bin/asadmin start-domain -v --postbootcommandfile mycommands.asadmin ${PAYARA_DOMAIN}