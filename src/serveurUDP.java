import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class serveurUDP {

    public static void main(String[] args){
        try {
            // connexion
            DatagramSocket server = new DatagramSocket(2345);

            while(true){

                // réception d'une requête
                byte[] buffer = new byte[8192];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                server.receive(packet);

                Com c = new Com(packet.getAddress(), packet.getPort());

                c.start();


                // traitement
                packet.setLength(buffer.length);

                String str = new String(packet.getData());
                str = str.split(" ")[0];
                System.out.println("Reçu de la part de " + packet.getAddress()
                        + " sur le port " + packet.getPort() + " : " + str);




                // envoi de la réponse
                byte[] buffer2 = new String("Réponse du serveur à " + str + "! ").getBytes();
                DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length,  packet.getAddress(),  packet.getPort());
                server.send(packet2);
                packet2.setLength(buffer2.length);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
};



