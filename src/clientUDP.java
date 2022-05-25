import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class clientUDP {

    static long sleepTime = 1000;
    public static void main(String[] args){

        int nbre = 0;
        String name = "client";
        while(true){
            String envoi = name + "-" + (++nbre) + " ";
            byte[] buffer = envoi.getBytes();

            try {
                // connexion
                DatagramSocket client = new DatagramSocket();

                // envoi de la requête
                InetAddress adresse = InetAddress.getByName("127.0.0.1");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, adresse, 2345);
                packet.setData(buffer);

                client.send(packet);

                // réception de la réponse
                byte[] buffer2 = new byte[8196];
                DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length, adresse, 2345);
                client.receive(packet2);

                // affichage
                System.out.print(envoi + " a reçu une réponse du serveur : ");
                System.out.println(new String(packet2.getData()));

                // Echo
                packet = new DatagramPacket(buffer, buffer.length, packet2.getAddress(), packet2.getPort());
                packet.setData(buffer);

                client.send(packet);

                buffer2 = new byte[8196];
                packet2 = new DatagramPacket(buffer2, buffer2.length, packet2.getAddress(), packet2.getPort());
                client.receive(packet2);

                System.out.print(envoi + " a reçu une réponse du serveur : ");
                System.out.println(new String(packet2.getData()));

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
