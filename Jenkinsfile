// 所有的脚本命令都放在pipeline
pipeline{
	//指定任务在那个集群
    agent any
    //声明全局变量
    environment{
    	key = 'value'
	}
	stages {
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
                echo '执行脚本- success'
            }
        }
    }
}
