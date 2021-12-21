package tcpa;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPClient {

	public static void main(String args[]) {
		
		try {
			Socket c = new Socket("127.0.0.1", 9876);
			
			Scanner sc = new Scanner(System.in);
			DataInputStream in = new DataInputStream(c.getInputStream());
			DataOutputStream out = new DataOutputStream(c.getOutputStream());
			
			String data = "";
			while(!data.equalsIgnoreCase("Over")) {
				
				System.out.print("Client input: ");
				data = sc.nextLine();
				out.writeUTF(data);
				out.flush();
				if(data.equalsIgnoreCase("Over")) break;
				
				data = in.readUTF();
				System.out.println("Client received: "+data);
			}
			System.out.println("Client left...");
			c.close();
			sc.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
