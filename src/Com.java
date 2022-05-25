import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Com extends Thread {

    private InetAddress IP_C;
    private int P_C;

    public InetAddress getIP_C() {
        return IP_C;
    }

    public void setIP_C(InetAddress IP_C) {
        this.IP_C = IP_C;
    }

    public int getP_C() {
        return P_C;
    }

    public void setP_C(int p_C) {
        P_C = p_C;
    }

    public Com(InetAddress IP_C, int p_C) {
        this.IP_C = IP_C;
        this.P_C = p_C;
    }

    @Override
    public void run() {
        try {
            // Connection
            DatagramSocket server = new DatagramSocket();

            byte[] buffer = new String("Changement d'adresse ! ").getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, IP_C, P_C);
            server.send(packet);
            packet.setLength(buffer.length);

            while (true) {
                // Reception request
                byte[] rBuffer = new byte[8196];
                DatagramPacket rPacket = new DatagramPacket(rBuffer, rBuffer.length);
                server.receive(rPacket);

                // Traitement
                rPacket.setLength(rBuffer.length);

                String str = new String(rPacket.getData());
                str = str.split(" ")[0];
                System.out.println("Reçu de la part de " + rPacket.getAddress()
                        + " sur le port " + rPacket.getPort() + " : " + str);

                // Envoie réponse
                byte[] sBuffer = new String("Réponse du com à " + IP_C.toString() + "! ").getBytes();
                DatagramPacket sPacket = new DatagramPacket(sBuffer, sBuffer.length, IP_C, P_C);
                server.send(sPacket);
                sPacket.setLength(sBuffer.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
