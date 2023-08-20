def call() {
    node {
        common.lintChecks()
        env.ARGS="-Dsonar.java.binaries=target/"                  
        common.sonarChecks()
    }
}


// def lintChecks(){
//    sh '''
//        echo Performing lintCheck for ${COMPONENT}
//        mvn checkstyle:check || true
//        echo lint checks completed for ${COMPONENT}
//
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

//           stage('Lint Checks') {
//                steps {
//                    script {
//                        lintChecks()
//                    }   
//                }
//            }

//            stage('Code Compile') {
//                steps {
//                        sh "mvn clean compile"                  
//                }
//            }

//            stage('Sonar Checks') {
//                steps {
//                    script {
//                        env.ARGS="-Dsonar.java.binaries=target/"
//                        common.sonarChecks()
//                    }                  
//                }
//            }

//            stage('Testing') {
//                steps {
//                    sh "echo Testing"
//                }
//            }
//        }    
//    }
// }