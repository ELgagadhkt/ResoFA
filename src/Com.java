import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Com extends Thread {

    public Com(InetAddress IP_C, int P_C) {
        try {
            DatagramSocket server = new DatagramSocket(P_C);

            byte[] buffer = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            server.receive(packet);

            byte[] buffer2 = new String("Réponse du serveur à " + "! ").getBytes();
            DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length,  IP_C,  P_C);
            server.send(packet2);
            packet2.setLength(buffer2.length);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
