package tcpb;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {

	public static void main(String args[]) {
		
		try {
			ServerSocket ss = new ServerSocket(9876);
			System.out.println("Waiting for client..");
			Socket s = ss.accept();
			System.out.println("Connection established!");
		
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter filename to save the file: ");
			String fileName = sc.next();
			
			String dest = "C:\\Users\\sejal\\Downloads\\"+fileName;
			InputStream in = s.getInputStream();
			OutputStream out = new FileOutputStream(dest);
			
			int bytesRead;
			byte bytes[] = new byte[1024];
			while((bytesRead = in.read(bytes))!=-1)
				out.write(bytes, 0, bytesRead);
			
			System.out.println("File received successfully!");
			ss.close();
			sc.close();
			in.close();
			out.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
