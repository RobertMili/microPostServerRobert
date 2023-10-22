//package com.example.micropostserverrobert.rabbitMQ;
//
//
//import com.example.micropostserverrobert.dto.MessageDto;
//
//import com.example.micropostserverrobert.entity.Message;
//
//import com.example.micropostserverrobert.service.MessageService;
//import com.rabbitmq.client.*;
//
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDateTime;
//import java.util.concurrent.TimeoutException;
//
//
//import lombok.SneakyThrows;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//
//
//@Configuration
//public class RabbitConnection {
//    private Connection connection;
//    private Channel channel;
//    private static final String QUEUE_MESSAGE_GET = "messages";
//    private static final String QUEUE_NAME_SENDING_MESSAGE = "shortUrl";
//
//    private String messageConvert;
//
//    MessageDto urlDto = new MessageDto();
//
//    @Autowired
//    public MessageService urlService;
//
//
//    public RabbitConnection() {
//        try {
//
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setHost(System.getenv().getOrDefault("ROCKET_RABBIT_HOST", "localhost"));
//            factory.setPort(5672);
//
//            connection = factory.newConnection();
//            channel = connection.createChannel();
//
//
//            channel.queueDeclare(QUEUE_MESSAGE_GET, false, false, false, null);
//        } catch (IOException | TimeoutException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void startConsuming() {
//        try {
//            channel.basicConsume(QUEUE_MESSAGE_GET, true, new DefaultConsumer(channel) {
//                @SneakyThrows
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope,
//                                           AMQP.BasicProperties properties, byte[] body) {
//                    String message = new String(body, StandardCharsets.UTF_8);
//                    JSONObject jsonPayload = new JSONObject(message);
//                    String extractedMessage = jsonPayload.getString(QUEUE_MESSAGE_GET);
//
//                    messageConvert = extractedMessage;
//
//                    System.out.println("Received message: " + messageConvert);
//
//                }
//
////                private UrlResponseDto addingInDatabaseMessage() {
////                    urlDto.setUrl(messageConvert);
////
////                    Url urlToRet = urlService.generateSHortLink(urlDto);
////
////                    UrlResponseDto urlResponseDto = new UrlResponseDto();
////                    urlResponseDto.setOriginalUrl(urlToRet.getOriginalUrl());
////                    urlResponseDto.setExpirationDate(LocalDateTime.now());
////                    urlResponseDto.setShortLink(urlToRet.getShortLink());
////                    return urlResponseDto;
////                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//                public void publishMessage(String sendingMessage) {
//                    try {
//
//                        ConnectionFactory factory = new ConnectionFactory();
//                        factory.setHost(System.getenv().getOrDefault("ROCKET_RABBIT_HOST", "localhost"));
//                        factory.setPort(5672);
//
//                        try (Connection connection = factory.newConnection();
//                             Channel channel = connection.createChannel()) {
//                            channel.queueDeclare(QUEUE_MESSAGE_GET, false, false, false, null);
//
//                            JSONObject jsonAd = new JSONObject();
//                            jsonAd.put("message", sendingMessage);
//
//                            String message = jsonAd.toString();
//
//
//                            channel.basicPublish("", QUEUE_NAME_SENDING_MESSAGE, null, message.getBytes(StandardCharsets.UTF_8));
//                            System.out.println("Sent message to RabbitMQ: " + message);
//
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    } catch (TimeoutException | IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
