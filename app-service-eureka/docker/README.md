## 搭建环境和启动流程

* 快速启动 
 
`docker build -t center:v1 .`  （← 带版本最好， 我测试直接用的 →）或者 `docker build -t center .`

`docker run --name=center30005 -d -p 30005:30000 -v /Users/lcc/Downloads/dockertest/center.jar:/center.jar  --privileged=true -e tZ="Asia/Shanghai" center`

** = DockerFile 需要和jar 在一个目录下。
-d = 后台运行
-p = 对外端口：容器内端口
-v = 对外地址（服务器硬盘地址） ：容器内地址


这个的好处是直接把jar包暴露在外面了，到时候直接更新jar，然后重启容器就行了



这样各个镜像就启动了

