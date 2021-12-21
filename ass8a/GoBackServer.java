package ass8a;

import java.io.*;
import java.net.*;
import java.util.*;

public class GoBackServer {
	
	public static void main(String []args) {
		
		try {
			ServerSocket ss = new ServerSocket(9876);
			System.out.println("Waiting for Client...");
			Socket s = ss.accept();
			System.out.println("Connection established.");
			
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			int n = in.read();
			int p = in.read();
			
			int ack = 0;
			int got = 0;
			Random R = new Random();
			int r = R.nextInt(p-1);    //packet to be lost
			boolean loop = true;
			
			while(loop) {
				if(got%n == 0)
					System.out.println("----------------------");
				
				for(int i=got%n; i<n; i++) {
					ack = in.read();
					
					if(ack == r) {
						System.out.println("Lost "+r);
						for(int j=i+1; j<n; j++) 
							System.out.println("Discarded "+in.read());
						ack = r-1;
						r=-1; 
						i=n;
						
					} else {
						System.out.print(ack + " | ");
						got++;
						
						if(got == p) {
							System.out.println("\nReceived all "+got+" frames");
							loop = false;
							break;
						}
					}
				}
				ack++;
				out.write(ack);
				System.out.println("\nACK "+ack+" sent");
			}
			
			ss.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}


