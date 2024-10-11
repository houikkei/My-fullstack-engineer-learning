# Docker

### Docker 命令背记手册
### 容器操作命令
### 创建并启动容器                 docker run -it ubuntu bash
### 列出当前运行的容器              docker ps
### 列出所有容器，包括未运行的       docker ps -a
### 停止一个运行中的容器            docker stop [CONTAINER_ID]
### 启动一个已停止的容器            docker start [CONTAINER_ID]
### 重启容器                      docker restart [CONTAINER_ID]
### 删除一个或多个容器              docker rm [CONTAINER_ID]
### 从仓库拉取镜像                 docker pull ubuntu
### 列出本地存储的镜像              docker images
### 删除一个或多个镜像              docker rmi [IMAGE_ID]
### 从 Dockerfile 构建镜像         docker build -t my-image:tag .
### 获取容器的日志                  docker logs [CONTAINER_ID]
### 管理 Docker 网络               docker network ls
### 管理 Docker 卷                 docker volume ls
### 显示 Docker 系统信息            docker info
### 显示 Docker 版本信息            docker version


### 镜像管理命令
### 显示镜像的详细信息             docker inspect ubuntu
### 为镜像添加一个新的标签          docker tag ubuntu:18.04 myubuntu:latest
### 将镜像保存为 tar 归档文件       docker save myimage > myimage.tar
### 从 tar 归档文件加载镜像         docker load < myimage.tar
### 从归档文件创建镜像              docker import mycontainer.tar myimage
### 将容器快照导出为归档文件         docker export mycontainer > mycontainer.tar
### 创建一个新容器但不启动它         docker create ubuntu:18.04
### 从容器创建镜像                 docker commit mycontainer mynewimage
### 显示容器文件系统的更改          docker diff mycontainer

### 其他常用命令
### 立刻终止容器的运行          docker kill [CONTAINER_ID]
### 在运行的容器中执行命令       docker exec -it [CONTAINER_ID] /bin/bash
### 连接到正在运行的容器         docker attach [CONTAINER_ID]
### 从容器中复制文件 / 目录到主机，反之亦然     docker cp [CONTAINER_ID]:/path/to/file /dest
### 暂停容器中所有的进程          docker pause [CONTAINER_ID]
### 恢复                           docker unpause [CONTAINER_ID]
