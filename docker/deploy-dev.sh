date=$(date "+%Y%m%d")
image_name="shangruobing/infoweaver:spring-tutorial-remote-$date"

container_name="spring-tutorial-remote-dev"
sudo docker rm -f $container_name

sudo docker pull "$image_name"

echo "Starting to Run Docker Container $image_name"

# 开发环境使用18020端口,不保存附件
sudo docker run -itd --name $container_name \
  -p 18020:8020 \
  -e SPRING_PROFILES_ACTIVE=dev \
  -v /home/ubuntu/spring-tutorial/template:/template \
  "$image_name"
