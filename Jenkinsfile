pipeline{
	//指定任务在那个集群
    agent any
    //声明全局变量
    environment{
    	key = 'value'
	}
	stages {
	    stage("checkout mvn") {
	        steps {
                sh '${maven}mvn --version'
            }
        }
        stage("check java") {
            steps {
                sh '${jdk17}/bin/java -version'
            }
        }
        stage('拉取git仓库代码') {
            steps {
		    checkout([$class: 'GitSCM', branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[credentialsId: '5452a98c-5eed-4a0e-acde-3e17ee48b4d8', url: 'git@github.com:nzp1/mytest.git']]])
            }
        }
        stage('通过maven构建项目') {
            steps {
                sh '/var/jenkins_home/maven/bin/mvn clean package -DskipTests'
            }
        }
		stage('传输文件') {
            steps {
                echo '传输文件- success'
            }
        }
		stage('执行脚本') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'my_cloud', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''cd  /root/docker-contains/mytest
mv ./target/*.jar ./back
mv ./update/* ./
docker-compose -f docker-compose.yml down
docker-compose -f docker-compose.yml up -d''', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/root/docker-contains/mytest/update', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}
