package udp;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPServer {

	public static void main(String args[]) {
		
		try {
			DatagramSocket s = new DatagramSocket(9876);
			
			byte bytes[] = new byte[10000000];
			DatagramPacket p = new DatagramPacket(bytes, bytes.length);
			s.receive(p);
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter file name to save file: ");
			String fileName = sc.next();
			String dest = "C:\\Users\\sejal\\Downloads\\" + fileName;
			OutputStream out = new FileOutputStream(dest);
			out.write(bytes);
			System.out.println("File saved successfully!");
			
			s.close();
			sc.close();
			out.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
