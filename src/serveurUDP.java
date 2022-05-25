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
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
};



