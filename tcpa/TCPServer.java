package tcpa;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer {

	public static void main(String args[]) {
		
		try {
			ServerSocket ss = new ServerSocket(9876);
			System.out.println("Waiting for client..");
			Socket s = ss.accept();
			System.out.println("Connection established!");
			
			Scanner sc = new Scanner(System.in);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			String data = "";
			while(!data.equalsIgnoreCase("Over")) {
				data = in.readUTF();
				System.out.println("Server received: "+data);
				if(data.equalsIgnoreCase("Over")) break;
				
				System.out.print("Server input: ");
				data = sc.nextLine();
				out.writeUTF(data);
				out.flush();
			}
			System.out.println("Connection closed.");
			ss.close();
			sc.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
