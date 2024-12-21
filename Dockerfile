FROM openjdk:11-jre-slim

WORKDIR /app

# Sao chép ứng dụng JAR
COPY target/mqtt-service.jar /app/mqtt-service.jar

# Sao chép script entrypoint.sh vào container
COPY entrypoint.sh /entrypoint.sh

# Cấp quyền thực thi cho script
RUN chmod +x /entrypoint.sh

# Chạy ứng dụng với script entrypoint
ENTRYPOINT ["/entrypoint.sh"]
