package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/*
 * Запускал сервер и клиента из одного окна в двух разных консолях:
 *
 * 1. Для запуска сервера:
 *    - Открыл класс `TCPServer`.
 *    - Создал конфигурацию запуска, указав класс `TCPServer`.
 *    - Запустил сервер через эту конфигурацию. Сервер начал ожидать входящие сообщения.
 *
 * 2. Для запуска клиента:
 *    - Открыл класс `TCPClient`.
 *    - Создал конфигурацию запуска, указав класс `TCPClient`.
 *    - В поле "Program arguments" добавил аргумент `localhost` для указания адреса сервера.
 *    - Запустил клиента через эту конфигурацию.
 *
 * Таким образом, сервер и клиент успешно взаимодействовали между собой, отправляя и получая сообщения.
 */

public class TCPClient {
    public static void main(String[] args) {
        System.out.println("Запуск клиента...");

        if (args.length < 1) {
            System.out.println("Использование: java TCPClient <адрес_сервера>");
            return;
        }

        String serverAddress = args[0];
        try (Socket socket = new Socket(serverAddress, 9876);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            // Запрашиваем у пользователя сообщение для отправки на сервер
            System.out.println("Введите сообщение для отправки на сервер: ");
            String message = scanner.nextLine();

            // Отправляем сообщение на сервер
            out.println(message);
            // Получаем ответ от сервера
            String response = in.readLine();
            // Выводим ответ от сервера
            System.out.println("Ответ от сервера: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
