REPO=docker.lappsgrid.org
ORG=lappsgrid
IMAGE=stanford
VERSION=gdd

docker:
	docker build -t $(ORG)/$(IMAGE) .
	
tag:
	docker tag $(ORG)/$(IMAGE) $(REPO)/$(ORG)/$(IMAGE):$(VERSION)
	
push:
	docker push $(REPO)/$(ORG)/$(IMAGE):$(VERSION)
	
start:
	docker run -d -p 8080:8080 --name stanford $(REPO)/$(ORG)/$(IMAGE):$(VERSION)

stop:
	docker rm -f stanford
