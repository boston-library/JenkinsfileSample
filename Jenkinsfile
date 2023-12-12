#!groovy
@Library("bpllib@Nov17") _

def mytool = new org.bpl.mytools()

String branchName = "${env.branchName}"
String gitHttpURL = "${env.gitHttpURL}"
String credentialsId = "${env.credentialsId}"
// String rubyVersion = "2.7.8"

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

        stage('Install New Ruby') {
            steps {
                script {
                    sh '''
                      #!/bin/bash --login
                      set -x
                      echo "Here is Daniel Miao"
                      pwd
                      whoami
                      ls -alt
                      rubyVersion=`cat .ruby-version`
                      echo "Prepared rubyVersion is ${rubyVersion}, can it be retrieved by outside library??? "
                    '''    

                    echo "Install New Ruby...${rubyVersion}..."
                    // String ruby_version = "2.7.8"
                    mytool.InstallNewRuby(rubyVersion)
                }
            }
        }

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