import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Diese Klasse erstellt einen TCP Client und verbindet ihn mit einem TCP Server der gegeben IP Adresse.
 * Es ist ein Scanner integriert, der Konsoleneingaben aufnimmt.
 * Das Programm läuft auf multithreading, dh senden und empfangen sind einzelne Threads.
 *
 * @author Severin Goddon
 */
public class TCPClient {
    private Scanner scanner = new Scanner(System.in);

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
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        new Thread(() -> { //empfangen thread
            try {
                listen(socket);
            }catch (IOException e){
                System.out.println("upsala");
            }
        }).start();

        new Thread(() -> { //senden thread
            send(printWriter);
        }).start();

    }

    public void listen(Socket socket) throws IOException {
        while (true){
            System.out.println(getMessage(socket));
        }
    }
    public void send(PrintWriter printWriter){
        while (true){
            String nachricht = this.scanner.nextLine();
            printWriter.print(nachricht);
            printWriter.flush();
        }
    }

    public String getMessage(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] buffer = new char[2000]; //maximale Paketlänge
        int anzahlZeichen = bufferedReader.read(buffer, 0, 2000); // blockiert bis die Nachricht empfangen wurde
        return new String(buffer, 0, anzahlZeichen);
    }
}