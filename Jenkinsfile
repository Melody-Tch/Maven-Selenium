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
   stage('Install Tomcat') {
		sh 'sudo ansible-playbook 1installtomcat.yml'
   }
   stage('Setup Tomcat') {
		sh 'sudo ansible-playbook 1installtomcat.yml'
   }
   stage('Install Tomcat') {
		sh 'sudo ansible-playbook 1installtomcat.yml'
   }
   stage('Clean'){
		sh 'rm -rf dist && rm -rf dist.tar.gz && rm -rf release/*.tar.gz'
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

node{
    def userInput = false;
    def didTimeout = false;
    def aws_s3_deploy_bucket_name = 'devopscdp-sit';
    def aws_s3_deploy_bucket_region = 'ap-southeast-1';

    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'kenjisato', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])  
    {
	    stage('Cleanup SIT')
        {
            awsIdentity() //show us what aws identity is being used
            withAWS(region: aws_s3_deploy_bucket_region) {
            s3Delete(bucket: aws_s3_deploy_bucket_name, path: '/')
            }
        }
        stage('Deploy SIT')
        {
            echo "performing deployment"
            dir("devopscdp_ui_dist") {
                unstash "devopscdp_ui_dist"
            }
            withAWS(region: aws_s3_deploy_bucket_region) {
            s3Upload(file: 'devopscdp_ui_dist/dist', bucket: aws_s3_deploy_bucket_name, path: '')
            }
        }
    }
}
def getTimeStamp(){
	def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
	def date = new Date()
	return dateFormat.format(date);
}