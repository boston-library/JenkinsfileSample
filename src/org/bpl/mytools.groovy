package org.bpl

def GetCode(srcType,branchName,gitHttpURL,credentialsId){
	if (srcType == "git"){
		println("Downloading code from branch: ${branchName}")
		checkout([
			$class: 'GitSCM', branches: [[name: "${branchName}"]], 
			extensions: [],
			userRemoteConfigs: [[credentialsId: "{$credentialsId}", url: "${gitHttpURL}"]]
			])
	}
}

def InstallNewRuby(rubyVersion){
   println("Installing new ruby version by being called: ${rubyVersion}")
   withEnv(["RUBYVERSION=${rubyVersion}"]){
      sh '''
           #!/bin/bash --login
           set -x
           pwd
           whoami
           ls -alt 
           
           ## EXPECTED_RUBY=`cat .ruby-version`
           echo "Inside shell script, install New Ruby... by calling library"
           echo "ruby_version is ${RUBYVERSION}"

           echo "Call assigned ruby-version" 

           if [ -s /var/lib/jenkins/.rvm/bin/rvm ]; then 
              source /var/lib/jenkins/.rvm/bin/rvm
           else 
              exit
           fi    
           
           echo "after sourcing rvm..."
           /bin/bash -l -c "/var/lib/jenkins/.rvm/bin/rvm install ${RUBYVERSION}"
           /bin/bash -l -c "/var/lib/jenkins/.rvm/bin/rvm use ${RUBYVERSION} --default"

           # # $HOME/.rvm/bin/rvm reinstall ${RUBYVERSION}
           # # $HOME/.rvm/bin/rvm use ${RUBYVERSION} --default
           bundle install
           
           whereis ruby
           ruby --version                    
           
      '''.stripIndent()
   }
}

def RunRSpec(){
	println("Running Spec Tests ")
	sh '''
	    #!/bin/bash -l
        set -x
        pwd
        whoami
        ls -alt 
        
        EXPECTED_RUBY=`cat .ruby-version`
        echo "EXPECTED_RUBY is ${EXPECTED_RUBY}"
        
        if [ -s /var/lib/jenkins/.rvm/bin/rvm ]; then 
           source /var/lib/jenkins/.rvm/bin/rvm
        else 
           exit
        fi    
        
        ## rvm use ${EXPECTED_RUBY} --default
        ## bundle install
        
        ## bin/rails exec rspec
        rspec	
	'''
}
