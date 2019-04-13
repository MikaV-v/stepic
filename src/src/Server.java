import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static Scanner in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(8080);
                System.out.println("Сервер запущен!");
                clientSocket = server.accept();
                try {
                    in = new Scanner(new InputStreamReader
                            (clientSocket.getInputStream()));
                    out = new BufferedWriter(
                            new OutputStreamWriter(
                                    clientSocket.getOutputStream()));
                    while (true) {
                        String word = in.nextLine();
                        System.out.println("С клиента полечно сообщение : " + word);
                        out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                        out.flush(); // выталкиваем все из буфера
                        if (word.equalsIgnoreCase("quit")){
                            break;
                        }
                    }
                } finally { // в любом случае сокет будет закрыт
                    System.out.println("Socket closed!");
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
