def lintChecks(){
    sh '''
        echo Installing JSLint for ${COMPONENT}
        npm i jslint
        node_modules/jslint/bin/jslint.js server.js || true
        echo lint checks completed for ${COMPONENT}

    '''         
}


def call(COMPONENT) {
    pipeline {
        agent { label 'WS' }
        environment {
            SONARCRED = credentials('SONARCRED')
            SONARURL  = "172.31.74.222"
        }
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