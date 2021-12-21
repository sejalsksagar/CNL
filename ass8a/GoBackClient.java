package ass8a;

import java.io.*;
import java.net.*;
import java.util.*;

public class GoBackClient {
	
	
	public static void main(String args[]) throws IOException
	{
		try {
			Socket s = new Socket("127.0.0.1", 9876);
			
			Scanner sc = new Scanner(System.in);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			System.out.print("\nEnter window size: ");
			int n = sc.nextInt();
			out.write(n);
			out.flush();
			
			System.out.print("No. of frames to transmit: ");
			int p = sc.nextInt();
			out.write(p);
			out.flush();
			
			boolean loop = true;
			int sent = 0;
			
			System.out.println("\nStarting transmission...");
			while(loop) {
				for(int i=sent%n; i<n; i++) {
					out.write(sent);
					sent++;
					if(sent == p)
						break;
				}
				
				int ack = in.read();
				System.out.println("Ack received: "+ack);
				if(ack >= p)
					break;
				
				sent = ack;
				System.out.println("Transmitting from " + sent + "...");
				
			}
			System.out.println("Transmitted all!");
			
			s.close();
			sc.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}


