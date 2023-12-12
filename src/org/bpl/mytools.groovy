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
 
      ## EXPECTED_RUBY=`cat .ruby-version`
      echo "ruby_version is ${RUBYVERSION}"

      if [ -s /var/lib/jenkins/.rvm/bin/rvm ]; then 
        source /var/lib/jenkins/.rvm/bin/rvm
      else 
        exit
      fi    

      echo "after sourcing rvm..."
      /var/lib/jenkins/.rvm/bin/rvm install ${RUBYVERSION}
      /var/lib/jenkins/.rvm/bin/rvm get stable
      /var/lib/jenkins/.rvm/bin/rvm use ${RUBYVERSION} --default

      # # bundle install
      whereis ruby
      ruby --version                    
           
      '''.stripIndent()
   }
}


def RunBundleInstall(){
  println("RUN bundle install ")
  sh '''
    #!/bin/bash -l
   
    echo "In  shared library,  bundle install" 
    if [ -s /var/lib/jenkins/.rvm/bin/rvm ]; then 
       source /var/lib/jenkins/.rvm/bin/rvm
    else 
       exit
    fi    
    
    ## rvm use ${EXPECTED_RUBY} --default
    bundle install

  '''
}

def RunDBpreparation(railsEnv){
  println("RUN DB prepare and migrate ")
  withEnv(["RAILS_ENV=${railsEnv}"]){
    sh '''
      #!/bin/bash -l
     
      echo "RAILS_ENV from Jenkinsfile is ${RAILS_ENV}"
      echo "In  shared library,  db:prepare and db:migrate " 
      if [ -s /var/lib/jenkins/.rvm/bin/rvm ]; then 
         source /var/lib/jenkins/.rvm/bin/rvm
      else 
         exit
      fi    
      
      ## RAILS_ENV=${RAILS_ENV} bundle exec rails db:prepare
      ## RAILS_ENV=${RAILS_ENV} bundle exec rails db:migrate

      bundle exec rails db:prepare
      bundle exec rails db:migrate

    '''
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
