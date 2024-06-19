package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
 * Запускал сервер и клиента из одного окна в двух разных консолях:
 *
 * 1. Для запуска сервера:
 *    - Открыл класс `UDPServer`.
 *    - Создал конфигурацию запуска, указав класс `UDPServer`.
 *    - Запустил сервер через эту конфигурацию. Сервер начал ожидать входящие сообщения.
 *
 * 2. Для запуска клиента:
 *    - Открыл класс `UDPClient`.
 *    - Создал конфигурацию запуска, указав класс `UDPClient`.
 *    - В поле "Program arguments" добавил аргумент `localhost` для указания адреса сервера.
 *    - Запустил клиента через эту конфигурацию.
 *
 * Таким образом, сервер и клиент успешно взаимодействовали между собой, отправляя и получая сообщения.
 */


public class UDPClient {
    public static void main(String[] args) {
        System.out.println("Запуск клиента...");

        if (args.length < 1) {
            System.out.println("Использование: java UDPClient <адрес_сервера>");
            return;
        }

        String serverAddress = args[0];
        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress IPAddress = InetAddress.getByName(serverAddress);

            // Запрашиваем у пользователя сообщение для отправки на сервер
            System.out.println("Введите сообщение для отправки на сервер: ");
            String message = scanner.nextLine();

            // Отправляем сообщение на сервер
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            // Получаем ответ от сервера
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Выводим ответ от сервера
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Ответ от сервера: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
