package ass8b;


import java.io.*;
import java.net.*;
import java.util.*;

public class SelectiveRepeatServer {

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
			boolean loop = true, lost = false;
			
			while(loop) {
				for(int i=0; i<n; i++) {
					int data = in.read();
					if(data == r) {
						System.out.print(" Lost "+r+" | ");
						lost = true;
						ack = r;
						r = -1;
					} else {
						System.out.print(data + " | ");
						got++;
						if(!lost) 
							ack = data + 1;
						
						if(got == p) {
							System.out.println("\nReceived all "+got+" frames");
							loop = false;
							break;
						}
					}
				}
				out.write(ack);
				System.out.println("\nACK "+ack+" sent");
				if(lost) {
					lost = false;
					ack = in.read();
					System.out.println("Received "+ack);
					ack++;
					System.out.println("\nACK "+ack+" sent");
					got++;
				}
			}
			
			ss.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}

/*
Waiting for Client...
Connection established.
Enter no. of packets to send: 12
Window size: 4
Sending frames...
Ack received: 4
Ack received: 4
Retransmit 4
Ack received: 12
Transmitted all!

 */

/*
Waiting for Client...
Connection established.
Enter no. of packets to send: 5

Enter data for packet 1: 1

Enter data for packet 2: 2

Enter data for packet 3: 3

Enter data for packet 4: 4

Enter data for packet 5: 5
No. of packets to send: 5
 */


/*
public static void main(String []args) {

int n;
int a[];

ServerSocket ss = null;
DataInputStream in = null;
DataOutputStream out = null;

Scanner sc = new Scanner(System.in);

try {
	ss = new ServerSocket(9876);
	System.out.println("Waiting for Client...");
	Socket c = ss.accept();
	System.out.println("Connection established.");
	
	in = new DataInputStream(c.getInputStream());
	out = new DataOutputStream(c.getOutputStream());
	
	System.out.print("Enter no. of packets to send: ");
	n = sc.nextInt();
	
	a = new int[n];
	for(int i=0; i<n; i++) {
		System.out.print("\nEnter data for packet "+(i+1)+": ");
		a[i] = sc.nextInt();
	}
	
	System.out.println("No. of packets to send: "+a.length);
	int y = a.length;
	out.write(y);
	out.flush();
	
	for(int i=0; i<y; i++) {
		out.write(a[i]);
		out.flush();
	}
	
	int k = in.read();
	out.write(a[k]);
	out.flush();
	
} catch(Exception e) {
	e.printStackTrace();
} finally {
	try {
		out.close();
		in.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}
*/