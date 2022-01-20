import java.io.IOException;

/**
 * Diese Klasse instsnziert den UDP Client und sendet eine Nachricht. Mit einer einfachen While Schleife könnte
 * man das client.sendeNachricht immer wieder ausführen, um auf der Leitung konstant UDP Pakete zu sehen. (Delay von
 * mindestens 20 Millisekunden nötig -> Thread.sleep(20) sonst geht kein paket mehr durch
 *
 * @author Severin Goddon
 */
public class UDPClientTest {
    public static void main(String[] args) throws IOException {
        UDPClient client = new UDPClient(); //den client erstellen
        client.sendeNachricht("Hi, it's the UDP client! "); //eine Nachricht senden
    }
}
