package com.ptit.service.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ptit.service.domain.entities.Node;
import com.ptit.service.domain.entities.SensorData;
import com.ptit.service.domain.services.NodeService;
import com.ptit.service.domain.services.SensorDataService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    private SensorDataService sensorDataService;
    @Autowired
    private NodeService nodeService;

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Subscribe to specific node data
    @MessageMapping("/node/{nodeId}")
    @SendTo("/topic/sensorData/{nodeId}")
    public SensorData getNodeData(@DestinationVariable Long nodeId) {
        // Get latest sensor data for the specific node
        return sensorDataService.getLatestDataForNode(nodeId);
    }

    // Subscribe to real-time updates for a specific node
    @MessageMapping("/subscribe/node/{nodeId}")
    public void subscribeToNode(@DestinationVariable Long nodeId) {
        Node node = nodeService.getNodeById(nodeId);
        if (node != null) {
            messagingTemplate.convertAndSend(
                    "/topic/sensorData/" + nodeId,
                    sensorDataService.getLatestDataForNode(nodeId));
        }
    }

    // Get historical data for a node
    @MessageMapping("/node/history/{nodeId}")
    @SendTo("/topic/history/{nodeId}")
    public List<SensorData> getNodeHistory(@DestinationVariable Long nodeId) {
        return sensorDataService.getNodeHistory(nodeId);
    }

    @MessageMapping("/publish/command/{nodeId}")
    public void publishCommand(@DestinationVariable Long nodeId, @Payload String commandJson) {
        // Nhận lệnh từ client và gửi lệnh đến thiết bị
        log.info("Received command: {}", commandJson);

        // Giả sử commandJson có cấu trúc như { "nodeName": "node_1", "led": 1 }
        try {
            // Parse JSON command để lấy trạng thái của các thiết bị
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode commandNode = objectMapper.readTree(commandJson);
            String nodeName = commandNode.path("nodeName").asText();

            // Xử lý các thiết bị trong lệnh
            Node node = nodeService.getNodeById(nodeId);
            if (node != null) {
                // Tạo thông điệp MQTT chứa các trạng thái thiết bị
                ObjectNode mqttMessage = objectMapper.createObjectNode();

                // Lặp qua tất cả các trường trong lệnh (ngoại trừ "nodeName")
                commandNode.fieldNames().forEachRemaining(field -> {
                    if (!field.equals("nodeName")) {
                        mqttMessage.put(field, commandNode.path(field).asInt()); // Thêm các thiết bị (led, buzzer, etc.)
                    }
                });

                // Gửi thông điệp MQTT đến thiết bị
                String mqttCommand = mqttMessage.toString();
                try {
                    mqttClient.publish("mqtt/remote/gateway", mqttCommand.getBytes(), 2, false);
                    log.info("Command published to MQTT: {}", mqttCommand);
                } catch (Exception e) {
                    log.error("Error publishing command", e);
                }
            }
        } catch (Exception e) {
            log.error("Error processing command JSON", e);
        }
    }
}
