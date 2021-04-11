# Blog

<https://linianhui.github.io/rabbitmq>

# Run 

```sh
docker-compose up -d
```

# RabbitAdmin

<http://192.168.2.201:15672>

# Test by api

[api.http](api.http)

# Tcpdump

```sh
docker-compose down

docker run -d --rm --name temp-container-for-copy-volume -v rabbitmqexample_volume_tcpdump:/data lnhcode/tool tail -f /dev/null

docker cp temp-container-for-copy-volume:/data/rabbitmq-temp.pcap rabbitmq-temp.pcap

docker stop temp-container-for-copy-volume
```