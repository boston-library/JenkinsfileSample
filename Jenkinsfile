#!groovy
@Library("bpllib@Nov17") _

def mytools = new bpllib.devops.mytools()

String branchName = "${env.branchName}"
String gitHttpURL = "${env.gitHttpURL}"
String credentialsId = "${env.credentialsId}"

pipeline {
    agent any
    options {
        skipDefaultCheckout true
    }

    stages {
        stage('GetCode') {
            steps {
                script {
                    mytools.GetCode("git", branchName, gitHttpURL, credentialsId)
                }

            }

            steps {
                script {
                    echo 'Hello from Rongbing Miao'
                    
                    // git branch: 'Nov17', url: 'https://github.com/boston-library/JenkinsfileSample.git'
                    
                    // sh '''
                    //     #!/bin/bash -l
                    //     set -x
                    //     pwd
                    //     whoami
                    //     ls -alt 
                        
                    //     EXPECTED_RUBY=`cat .ruby-version`
                    //     echo "EXPECTED_RUBY is ${EXPECTED_RUBY}"
                        
                    //     ##if [ -s /var/lib/jenkins/.rvm/bin/rvm ]; then 
                    //     ##    source /var/lib/jenkins/.rvm/bin/rvm
                    //     ##else 
                    //     ##    exit
                    //     ##fi    
                        
                    //     ##rvm use ${EXPECTED_RUBY} --default
                    //     ##bundle install
                        
                    //     ##whereis ruby
                    //     ##ruby --version                    
                        
                    //     '''
                }
            }
        }

        stage('Testing') {
            steps {
                script {
                    echo "Testing Stage..."
                }
            }
        }

        stage('Deploying') {
            steps {
                script {
                    echo "Deeploy Stage..."
                }
            }
        }

    }
}