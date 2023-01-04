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
                echo '拉取git仓库代码- success'
            }
        }
        stage('通过maven构建项目') {
            steps {
                echo '通过maven构建项目- success'
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
