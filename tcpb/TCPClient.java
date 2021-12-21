package tcpb;

import java.net.*;
import java.io.*;

public class TCPClient {

	public static void main(String args[]) {
		
		try {
			Socket c = new Socket("127.0.0.1", 9876);
			
			File f = new File("C:\\Users\\sejal\\Downloads\\mysong.mp3");
			byte bytes[] = new byte[(int)f.length()];
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(bytes, 0, bytes.length);
			
			OutputStream out = c.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();
			
			System.out.println("File transferred!");
			
			c.close();
			bis.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
