def call() {
    node {
        git branch: 'main', url: "https://github.com/Devops-54/${COMPONENT}.git"
        env.APP_TYPE="python"
        common.lintChecks()
        env.ARGS="-Dsonar.sources=."
        common.sonarChecks()
        common.testCases()
    }
}


// def lintChecks(){
//    sh '''
//         echo Installing PYLint for ${COMPONENT}  
//         #pip3 install pylint
//         #pylint *.py
//         echo lint checks completed for ${COMPONENT}

//    '''         
// }


// def call(COMPONENT) {
//    pipeline {
//        agent { label 'WS' }
//        environment {
//            SONARCRED = credentials('SONARCRED')
//            SONARURL  = "172.31.74.222"
//        }
//        stages { 
//            stage('Lint Checks') {
//                steps {
//                    script {
//                        lintChecks()
//                    }   
//                }
//            }

//            stage('Sonar Checks') {
//                steps {
//                        script {
//                            env.ARGS="-Dsonar.sources=."
//                            common.sonarChecks()
//                        }                  
//                    }
//                }
//                
//            stage('Testing') {
//                steps {
//                        sh "echo Testing"
//                    }
//                }
//            }                                         
//        }
//    }
