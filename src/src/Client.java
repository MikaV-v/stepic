import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static Socket clientSocket;
    private static Scanner reader;
    private static Scanner in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 8080);
                reader = new Scanner(new InputStreamReader(System.in));
                in = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                while (true) {
                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                    String word = reader.nextLine();
                    if (word.equalsIgnoreCase("quit")){
                        System.out.println("Закрываем!");
                        out.write(word + "\n");
                        out.flush();
                        break;
                    }
                    out.write(word + "\n");
                    out.flush();
                    String serverWord = in.nextLine();
                    System.out.println(serverWord);
                }
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }

    }
}
