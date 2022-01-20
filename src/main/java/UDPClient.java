import java.io.IOException;
import java.net.*;

/**
 * Diese Klasse erstellt einen einfachen UDP CLient, der lediglich eine Nachricht per UDP an einen Server der
 * gegeben IP sendet. Danach sendet der Server sie wieder zurück und der Client printet. Es wird KEINE Verbindung
 * aufgebaut
 * @author Severin Goddon
 */
public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public UDPClient() throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket();
        this.address = InetAddress.getByName("192.168.8.164"); //<---- hier Server IP angeben!
    }

    public String sendeNachricht(String nachricht) throws IOException {
        this.buf = nachricht.getBytes();
        DatagramPacket packet = new DatagramPacket(this.buf, this.buf.length, this.address, 4446);
        this.socket.send(packet); //paket senden
        packet = new DatagramPacket(this.buf, this.buf.length);
        this.socket.receive(packet); //paket wieder empfangen (echo)
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

}