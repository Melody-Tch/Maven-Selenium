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
		sh 'sudo ansible-playbook 2setuptomcat.yml'
   }
   stage('Install Apache') {
		sh 'sudo ansible-playbook 3installapache.yml'
   }
   stage('Setup Apache'){
		sh 'sudo ansible-playbook 4setupapache.yml'
   }
   stage('GetDependencies'){
		sh 'npm install --max-old-space-size=400'
   }
   stage('Build'){
		sh 'npm run build --prod --max-old-space-size=400'
   }
   stage('Archive') {
		sh 'mkdir -p release'
		sh 'cd dist && tar -czvf ../release/devopscdp-$(timeStamp).tar.gz . && cd ..'
		stash includes: 'release/*.tar.gz', name: 'devopscdp_ui'
		stash includes: 'dist/*', name: 'devopscdp_ui_dist'
   }
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'kenjisato', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])  
	 {
		stage('Publish to S3'){
		unstash 'devopscdp_ui'
		awsIdentity() //show us what aws identity is being used
		def targetLocation = project_id + '/' + timeStamp;
		withAWS(region: aws_s3_bucket_region) {
		s3Upload(file: 'release', bucket: aws_s3_bucket_name, path: targetLocation)
		}
		}
	}
}catch (err) {

        currentBuild.result = "FAILURE"

        throw err
    }
}