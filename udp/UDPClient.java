package udp;

import java.net.*;
import java.io.*;

public class UDPClient {

	public static void main(String args[]) {
		
		try {
			DatagramSocket c = new DatagramSocket();
			InetAddress ia = InetAddress.getLocalHost();
			
			File f = new File("C:\\Users\\sejal\\Downloads\\chatapp_logo.png");
			byte bytes[] = new byte[(int) f.length()];
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(bytes, 0, bytes.length);
			
			DatagramPacket p = new DatagramPacket(bytes, bytes.length, ia, 9876);
			c.send(p);
			System.out.println("File sent successfully!");
			
			c.close();
			bis.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
