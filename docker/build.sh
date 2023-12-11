open -a Docker

cd ..

mvn clean package

date=$(date "+%Y%m%d")
image_name="shangruobing/infoweaver:spring-tutorial-remote-$date"

echo "Starting to Build Docker Image $image_name"
sudo docker rmi -f "$(sudo docker images -q "shangruobing/infoweaver:spring-tutorial-remote*")"
sudo docker build --platform linux/amd64 -t "$image_name" . && sudo docker push "$image_name"
#sudo docker buildx build --platform linux/amd64 -t "$image_name" . && sudo docker push "$image_name"
