import java.text.SimpleDateFormat

node {
currentBuild.result = "SUCCESS"
try {
   stage('Checkout') { // for display purposes
    checkout scm;  
   }
  //   stage('Install Ansible') {
	// 	sh 'sudo yum install epel-release -y'
  //   sh 'sudo yum install ansible -y'
  //  }
  //   stage('Install Maven') {
	// 	sh 'cd /usr/local'
  //   sh 'sudo wget http://www-eu.apache.org/dist/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.tar.gz'
  //   sh 'sudo tar xzf apache-maven-3.5.2-bin.tar.gz'
  //   sh 'sudo ln -s apache-maven-3.5.2  maven'
  //   sh 'rm -f /usr/local/apache-maven-3.5.2-bin.tar.gz'
  //   sh 'mvn -version'
  //  }
   stage('Build Maven Project') {
		sh 'cd /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/user_api_new'
    sh 'sudo env "PATH=$PATH" mvn -Dmaven.test.failure.ignore=true clean install'
    sh 'cd'
   }
   stage('Install Tomcat') {
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/playbooks/1installtomcat.yml'
   }
   stage('Setup Tomcat') {
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/playbooks/2setuptomcat.yml'
   }
   stage('Install Apache') {
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/playbooks/3installapache.yml'
   }
   stage('Setup Apache'){
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/playbooks/4setupapache.yml'
   }
}catch (err) {

        currentBuild.result = "FAILURE"

        throw err
    }
}