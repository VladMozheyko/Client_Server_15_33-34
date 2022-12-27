import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /*
    План занятия
    1) Сокеты
    2) Создание клиент-серверного приложения

     */

    /*
    Теория
    Компьютеры могут обмениваться данными при помощи сетей. Для обмена данными у каждого компьютера есть адрес в сети.
    У компьютера есть MAC_адрес - личный адрес компьютера, ip-адрес - адрес компьютера в сети, он может динамически меняться.
    Существуют протоколы - правила обмена данными для компьютерных сетей: http, udp
    Компьютерная сеть  - объединение нескольких компьютеров.

    Сокет - условно можно представить в виде квартиры в доме, где дом является ip-адресов, когда мы говорим о подключении к
    такому-то компьютеру, кроме адреса, необходимо указать и сокет, т.е. открытый пункт связи между устройствами.

    Для работы с сокетами в Java существуют классы: Socket и SeverSocket

    SeverSocket - класс для приема подключений, он принимает адрес сокета, на котором будет ожидать подключения клиента

    Socket - класс для работы с сокетами, т.е. оправка и чтение сообщений

     OutputStreamWriter  - класс для записи сообщений

     BufferedReader  - класс для чтения сообщений
     */

    /*
    Не путать потоки ввода-вывода(записи, чтения) с потоками исполнения
     */

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(8015);
        int count = 0;

        while (true){

             //Обращаемся к серверу, чтобы принять клиента
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент номер: " + count++);
            // Создаем поток записи сообщения для клиентов         // Получаем поток записи
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            // Создаем поток для чтения сообщений клиента   // Получаем поток для чтения
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Считываем строку
            String request = bufReader.readLine();
            Thread.sleep(5000);  // Ждем 5 секунд
            String response = "Длина вашего сообщения: " + request.length() + "\n"; // Выводим на консоль длину сообщения
            writer.write(response);   // Отправляю ответ клиенту
            writer.flush();
            writer.close();           // Закрываем потоки записи и чтения
            bufReader.close();
            clientSocket.close();  // Закрываем соединение с клиентом

        }

    }
}
