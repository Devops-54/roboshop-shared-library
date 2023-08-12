def sonarChecks(){
    sh '''
        echo Sonar Checks In Progress
        sonar-scanner -Dsonar.sources=. -Dsonar.login=50f7343f3f183720df2f8fb67c87dba098f84910 -Dsonar.host.url=http://172.31.74.222:9000 -Dsonar.projectKey=${COMPONENT}
        curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > sonar-quality-gate.sh
        sonar-quality-gate.sh ${SONARCRED_USER} ${SONARCRED_PSW} ${SONARURL} ${COMPONENT}
        echo Sonar Checks completed

    '''
}