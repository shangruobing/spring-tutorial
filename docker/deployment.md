# Server Common Command

*此文档记录了Springboot与MySQL打包Docker并部署Ubuntu所遇问题及解决方案*
> *Written by **Ruobing Shang** on September 26, 2023*

## Maven

### Build

```shell
mvn clean package
```

## Docker

### Install

```shell
# 安装启动Docker
sudo apt install -y docker.io
sudo systemctl start docker
sudo systemctl enable docker
sudo docker version
```

### Build

```shell
# 当前平台打包
sudo docker build -t imagename:0.0.1 .
# 指定平台打包
sudo docker buildx build --platform linux/amd64 -t imagename:0.0.1 .
```

### Run
```shell
#  -d表示deamon,守护进程后台执行; 8080:8080 主机端口:docker端口
sudo docker run -itd --name imagename -p 8080:8080 imagename:0.0.1
```

### Publish and Download

```shell
# 登录后push到Dockerhub
sudo docker login
sudo docker tag imagename:0.0.1 shangruobing/infoweaver:imagename-0.0.1
sudo docker push shangruobing/infoweaver:imagename-0.0.1

# 从Dockerhub下载image并运行
sudo docker pull shangruobing/infoweaver:imagename-0.0.1
sudo docker run -itd --name imagename-0.0.1 -p 8080:8080 shangruobing/infoweaver:imagename-0.0.1

# 本地打包
sudo docker save -o "infoweaver-spring-tutorial-$date".tar shangruobing/infoweaver:imagename-0.0.1
sudo chmod 777  "infoweaver-spring-tutorial-$date".tar
```

### Common

```shell
# 列出所有容器
sudo docker ps -a
# 删除所有容器
sudo docker rm -f $(sudo docker ps -aq)
# 列出所有镜像
sudo docker images
# 根据Id删除镜像
sudo docker rmi containerId -f
# 根据Id查看镜像日志
sudo docker logs -f containerId
# 根据Id或名称进入到容器到bash或sh
sudo docker exec -it containerId /bin/sh
sudo docker exec -it containerName /bin/sh
# 将容器内的数据拷贝到主机
sudo docker cp containerName:/upload /home/ubuntu/upload
```

### Volume

```shell
sudo docker volume create imagename-volume
```

### Docker打包出错
> ERROR [internal] load metadata for docker.io
将/Users/Username/.docker/config.json下的credsStore改为credStore

```json5
{
  //"credsStore": "desktop"
  "credStore": "desktop"
}
```

## MySQL

### Remote Connection

1. 查看数据库服务是否启动`systemctl status mysql`
2. 查看数据库端口是否为3306`show global variables like 'port';`
3. 查看root用户权限，%代表允许所有连接`SELECT user,host FROM mysql.user;`
4. 查看配置文件mysqld.conf的bind_address地址是否为0.0.0.0

> 配置文件路径 /etc/mysql/mysql.conf.d/mysqld.cnf

### Set Password

```shell
# 进入MySQL命令行
sudo mysql -u root
# 使用mysql数据库
use mysql;

create user 'admin'@'localhost';
# 查看root的密码插件是否为auth_socket;是则需要更改,否则密码无效
SELECT User, Host, plugin FROM mysql.user;
UPDATE user SET plugin='mysql_native_password' WHERE User = 'admin';
UPDATE user SET plugin='caching_sha2_password' WHERE User = 'root';
# 刷新权限
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';
# 重启服务
sudo service mysql restart;
# 修改密码
ALTER USER 'admin'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
# 二选一,推荐第一种
update mysql.user set authentication_string=password('123456') where user='root';
```

## Redis

```shell
sudo docker run -itd --name redis -p 6379:6379 redis:latest --requirepass '123456'
```

## RabbitMq
```shell
sudo docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:3.12-management
```