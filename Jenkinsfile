import java.text.SimpleDateFormat

node {
currentBuild.result = "SUCCESS"
try {
   stage('Checkout') { // for display purposes
    checkout scm;  
   }
    stage('Install Ansible') {
		sh 'sudo yum install epel-release -y'
        sh 'sudo yum install ansible -y'
   }
    stage('Build Maven Project') {
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/playbooks/buildmaven.yml'
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