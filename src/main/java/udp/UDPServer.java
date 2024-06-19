package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // Создаем серверный сокет, который будет слушать на порту 9876
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("Сервер UDP запущен и ожидает сообщения...");

            while (true) {
                // Получаем пакет данных от клиента
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Извлекаем сообщение из пакета
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Фиксируем время получения сообщения
                LocalDateTime receiveTime = LocalDateTime.now();
                System.out.println("Получено сообщение: " + message);
                System.out.println("Время приёма: " + receiveTime);
                System.out.println("Адрес клиента: " + clientAddress);

                // Формируем ответ с временем приёма
                String response = "Время приёма: " + receiveTime.toString();
                sendData = response.getBytes();

                // Отправляем ответ клиенту
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
