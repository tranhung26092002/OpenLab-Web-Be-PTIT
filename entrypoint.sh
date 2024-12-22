#!/bin/bash

# Kiểm tra thư mục config
if [ ! -d "/mosquitto/config" ]; then
  echo "Tạo thư mục config..."
  mkdir -p /mosquitto/config
fi

# Kiểm tra file cấu hình mosquitto.conf
if [ ! -f "/mosquitto/config/mosquitto.conf" ]; then
  echo "File mosquitto.conf không tồn tại. Tạo file cấu hình mặc định..."
  
  # Tạo file mosquitto.conf với cấu hình cơ bản
  cat <<EOF > /mosquitto/config/mosquitto.conf
# mosquitto.conf - Cấu hình mặc định cho Mosquitto

# Cổng nghe MQTT
listener 1883

# Cổng web admin (tuỳ chọn)
listener 9001
protocol websockets

# Đường dẫn log
log_dest file /mosquitto/log/mosquitto.log

# Quản lý kết nối
allow_anonymous true

# Thông báo lỗi
pid_file /mosquitto/mosquitto.pid
EOF
fi

# Kiểm tra MQTT Broker đã sẵn sàng chưa
until nc -z -v -w30 mqtt-broker 1883
do
  echo "Waiting for MQTT Broker..."
  sleep 5
done

# Sau khi MQTT Broker sẵn sàng, khởi động ứng dụng
echo "MQTT Broker is ready. Starting the application..."
java -jar /app/mqtt-service.jar
