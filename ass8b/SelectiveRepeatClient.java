package ass8b;

import java.io.*;
import java.net.*;
import java.util.*;

public class SelectiveRepeatClient {
	public static void main(String[] args) {
		
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
				for(int i=0; i<n; i++) {
					out.write(sent);
					sent++;
					if(sent == p) 
						break;
				}
				
				int ack = in.read();
				System.out.println("Ack received: "+ack);
				if(ack >= p)
					break;
//				if(sent == p) 
//					break;
				
				if(ack < sent) {
					System.out.println("Retransmitting " + ack);
					out.write(ack);
				}
			}
			System.out.println("Transmitted all!");
			
			s.close();
			sc.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}

/*
Number of Packets: 12
Window size: 4
Frames Received: 
0 | 1 | 2 | 3 | 
Ack send 4
 Lost 4 | 5 | 6 | 7 | 
Ack send 4

Received 4
8 | 9 | 10 | 11 | 
Received all 12 frames

Ack send 12

 */

/*
/127.0.0.1
Number of Packets: 5
1
2
3
4
5
Received packet 1 with data: 1
Received packet 2 with data: 2
Received packet 3 with data: 3
Received packet 4 with data: -1
Received packet 5 with data: 5
Retransmit packet 4
Received packet 4 with data: 4
 */

/*
public static void main(String[] args) {

try {
	int v[] = new int[30];
	int n = 0;
	Random R = new Random();
	int rno = 0;
	
	Socket s = new Socket("127.0.0.1", 9876);
	
	DataInputStream in = new DataInputStream(s.getInputStream());
	DataOutputStream out = new DataOutputStream(s.getOutputStream());
	
	int p = in.read();
	System.out.println("Number of Packets: "+p);
	
	for(int i=0; i<p; i++){
		v[i] = in.read();
		System.out.println(i+1);
	}
	
	rno = R.nextInt(p);
	v[rno] = -1;
	for(int i=0; i<p; i++)
		System.out.println("Received packet "+(i+1)+" with data: "+v[i]);
	
	for(int i=0; i<p; i++) {
		if(v[i] == -1) {
			System.out.println("Retransmit packet "+(i+1));
			n = i;
			out.write(n);
			out.flush();
			v[n] = in.read();
			System.out.println("Received packet "+(n+1)+" with data: "+v[n]);
		}
	}
	
} catch (IOException e) {
	e.printStackTrace();
} 
}
*/