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
                    rubyVersion = sh(returnStdout: true, script: 'echo `cat .ruby-version`').trim()
                    echo "Install New Ruby...${rubyVersion}..."
                    String rubyVersion = "${rubyVersion}"
                    mytool.InstallNewRuby(rubyVersion)
                }
            }
        }

        stage('DB migrate') {
            steps {
                script {
                    echo "DB:migrate Phase..."
                    echo "Staging_name is ${PHASE_NAME}\n"
                    if ( "${PHASE_NAME}" == "staging" ) { 
                        // String railsEnv = "staging"
                        String railsEnv = "staging"
                    } else {
                        String railsEnv = ""     

                    }
                    echo "RAILS_ENV in Jenkinsfile is ${railsEnv}"
                    mytool.RunDBpreparation(railsEnv)
                }
            }
        }

        stage('Bundle Install') {
            steps {
                script {
                    echo "Bundle Install Stage..."
                    mytool.RunBundleInstall()
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