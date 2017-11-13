import java.text.SimpleDateFormat

node {
    
currentBuild.result = "SUCCESS"
def timeStamp;
def project_id;
def aws_s3_bucket_name;
def aws_s3_deploy_bucket_name;
def aws_s3_bucket_region;

try {
   stage('Checkout') { // for display purposes
    checkout scm;  
   }
    stage('Install Ansible') {
		sh 'sudo yum install epel-release -y'
        sh 'sudo yum install ansible -y'
        sh 'sudo yum install epel-release -y'
   }
   stage('Install Tomcat') {
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/1installtomcat.yml'
   }
   stage('Setup Tomcat') {
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/2setuptomcat.yml'
   }
   stage('Install Apache') {
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/3installapache.yml'
   }
   stage('Setup Apache'){
		sh 'sudo ansible-playbook /var/lib/jenkins/workspace/ApacheJenkins_master-KJ62CKVCZFGDMZ4FWDQF3YVB2X67VWNSRTZOPXKS2ZK575ZNBT6Q/4setupapache.yml'
   }
		}
	}
}catch (err) {

        currentBuild.result = "FAILURE"

        throw err
    }
}