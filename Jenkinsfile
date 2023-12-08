#!groovy
@Library("bpllib@Nov17") _

def mytool = new org.bpl.mytools()

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
                    echo "branchName is ${branchName}"
                    echo "gitHttpURL is ${gitHttpURL}"
                    echo "credentialsId is ${credentialsId}"
                    
                    echo "mytool is ${mytool}"
                    // mytool.GetCode("git", branchName, gitHttpURL, credentialsId)
                    mytool.GetCode("git", branchName, gitHttpURL, credentialsId)
                }
            }
        }

        // stage('Install New Ruby') {
        //     steps {
        //         script {
        //             echo "Install New Ruby..."
        //             mytool.InstallNewRuby()
        //         }
        //     }
        // }

        stage('Testing') {
            steps {
                script {
                    echo "Testing Stage..."
                    mytool.RunRSpec()
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