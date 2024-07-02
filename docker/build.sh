open -a Docker

cd ..

mvn clean package

date=$(date "+%Y%m%d")
image_name="shangruobing/infoweaver:spring-tutorial-$date"

echo "Starting to Build Docker Image $image_name"
sudo docker rmi -f "$(sudo docker images -q "shangruobing/infoweaver:spring-tutorial*")"
sudo docker build --platform linux/amd64 -t "$image_name" .

#sudo docker push "$image_name"

sudo docker save -o "infoweaver-spring-tutorial-$date".tar "$image_name"
sudo chmod 777  "infoweaver-spring-tutorial-$date".tar