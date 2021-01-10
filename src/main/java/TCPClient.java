import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Diese Klasse erstellt einen TCP Client und verbindet ihn mit einem TCP Server der gegeben IP Adresse.
 * Es ist ein Scanner integriert, der Konsoleneingaben aufnimmt. Die Kommunikation funktioniert im Ping-Pong Modus,
 * dh es kann immer der Server eine Nachricht schreiben und dann der Client immer abwechselnd. (Applikationsbedingt, um
 * das zu beheben müsste man Multithreading machen)
 *
 * @author Severin Goddon
 */
public class TCPClient {
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        try {
            client.test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void test() throws IOException {
        String ip = "192.168.8.164"; // <----- ipadresse hier eingeben 8====D
        int port = 6969;
        Socket socket = new Socket(ip,port); // mit dem Server verbinden
        chatStarten(socket);
    }

    public void chatStarten(Socket socket) throws IOException {
        String nachricht = null;
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        while (true) {
            nachricht = scanner.nextLine();
            printWriter.print(nachricht);
            printWriter.flush();
            System.out.println(getMessage(socket));
        }

    }
    public String getMessage(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] buffer = new char[2000]; //maximale Paketlänge
        int anzahlZeichen = bufferedReader.read(buffer, 0, 2000); // blockiert bis die Nachricht empfangen wurde
        return new String(buffer, 0, anzahlZeichen);
    }
}