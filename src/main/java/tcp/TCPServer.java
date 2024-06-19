package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9876)) {
            System.out.println("Сервер TCP запущен и ожидает сообщения...");

            while (true) {
                // Принимаем подключение от клиента
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Получаем сообщение от клиента
                String message = in.readLine();
                LocalDateTime receiveTime = LocalDateTime.now();
                InetAddress clientAddress = clientSocket.getInetAddress();

                // Выводим информацию о полученном сообщении
                System.out.println("Получено сообщение: " + message);
                System.out.println("Время приёма: " + receiveTime);
                System.out.println("Адрес клиента: " + clientAddress);

                // Отправляем ответ клиенту с временем приёма
                out.println("Время приёма: " + receiveTime.toString());

                // Закрываем соединение
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
