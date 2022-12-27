import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /*
    "127.0.0.1" - localhost. Работаем в пределах этого компьютера
     */

    public static void main(String[] args) throws IOException {
        // Создаем сокет для работы с сервером
        Socket clientSocket = new Socket("127.0.0.1", 8015) ;


        OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String str = scanner.next();
        writer.write(str+ "\n");         // Отправляю сообщение серверу
        writer.flush();

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message = bufReader.readLine();  //  Принимаю сообщение от сервера
        System.out.println(message);       //  Вывожу его на консоль
        bufReader.close();          //  Закрываю потоки и соединение
        writer.close();
        clientSocket.close();


    }
}
