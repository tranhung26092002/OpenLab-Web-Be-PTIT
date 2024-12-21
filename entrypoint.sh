#!/bin/bash

# Kiểm tra MQTT Broker đã sẵn sàng chưa
until nc -z -v -w30 mqtt-broker 1883
do
  echo "Waiting for MQTT Broker..."
  sleep 5
done

# Sau khi MQTT Broker sẵn sàng, khởi động ứng dụng
echo "MQTT Broker is ready. Starting the application..."
java -jar /app/mqtt-service.jar
