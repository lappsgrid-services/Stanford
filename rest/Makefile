jar:
	mvn package
	cp target/*.jar src/main/docker

docker:
	make -C src/main/docker docker
	
tag:
	make -C src/main/docker tag

push: tag
	make -C src/main/docker push

start:
	make -C src/main/docker start

stop:
	make -C src/main/docker stop


clean:
	mvn clean
	rm src/main/docker/*.jar

