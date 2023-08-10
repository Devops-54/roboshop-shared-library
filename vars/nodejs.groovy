def lintChecks(){
    sh '''
        echo Installing JSLint for ${COMPONENT}
        npm i jslint
        node_modules/jslint/bin/jslint.js server.js || true
        echo lint checks completed for ${COMPONENT}

    '''         
}

def sonarChecks(){
    sh '''
        echo Sonar Checks In Progress
        sonar-scanner -Dsonar.sources=. -Dsonar.login=50f7343f3f183720df2f8fb67c87dba098f84910 -Dsonar.host.url=http://172.31.74.222:9000 -Dsonar.projectKey=${COMPONENT}
        echo Sonar Checks completed

    '''
}

def call(COMPONENT) {
    pipeline {
        agent { label 'WS' }
        stages { 

            stage('Lint Checks') {
                steps {
                    script {
                        lintChecks()
                    }   
                }
            }

            stage('Code Compile') {
                steps {
                        sh "npm install"                  
                }
            }
      
            stage('Sonar Checks') {
                steps {   
                    script {           
                       sonarChecks()
                    }
                }
             }
            stage('Testing') {
                steps {   
                    sh "echo Testing In Progress"
                }
            }
        }
    }
}   