#!/bin/bash

# Tạo các thư mục cần thiết cho Mosquitto nếu chưa tồn tại và cấp quyền
echo "Setting up Mosquitto directories..."
mkdir -p /mosquitto/config /mosquitto/data /mosquitto/log
chmod -R 755 /mosquitto

# Kiểm tra MQTT Broker đã sẵn sàng chưa
echo "Checking if MQTT Broker is ready..."
until nc -z -v -w30 mqtt-broker 1883
do
  echo "Waiting for MQTT Broker..."
  sleep 5
done

# Sau khi MQTT Broker sẵn sàng, khởi động ứng dụng
echo "MQTT Broker is ready. Starting the application..."
java -jar /app/mqtt-service.jar
