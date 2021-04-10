# Blog

<https://linianhui.github.io/rabbitmq>

# Run 

```sh
docker-compose up -d
```

# Test by api

[api.http](api.http)

# Tcpdump

```sh
docker-compose down

docker run -d --rm --name temp-container-for-copy-volume -v rabbitmqexample_volume_tcpdump:/data lnhcode/tool tail -f /dev/null

docker cp temp-container-for-copy-volume:/data/rabbitmq-temp.pcap rabbitmq-temp.pcap

docker stop temp-container-for-copy-volume
```