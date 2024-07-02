date=$(date "+%Y%m%d")
image_name="shangruobing/infoweaver:spring-tutorial-$date"

container_name="spring-tutorial-prod"
sudo docker rm -f $container_name
#sudo docker rmi -f "$(sudo docker images -q "shangruobing/infoweaver:spring-tutorial*")"

sudo docker pull "$image_name"

echo "Starting to Run Docker Container $image_name"

# 生产环境使用8020端口
sudo docker run -itd --name $container_name \
  -p 8020:8020 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -v /home/ubuntu/spring-tutorial/upload:/upload \
  -v /home/ubuntu/spring-tutorial/template:/template \
  "$image_name"
