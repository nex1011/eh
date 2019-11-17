import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread{
	
	public static final int PORT = 9001;
	
	protected Socket socket;
	
	private static int counter = 1;
	
	private static ArrayList<OutputStream> clients; 
	
	private Server(Socket socket) {
		
		this.socket = socket;
		start();
	}
	
	public void run() {
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			clients.add(out);
			
			out.write(counter);
			counter++;
			out.flush();
 			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String request;
			while((request = br.readLine()) != null) {
				
				String[] input = request.split(",");
				
				System.out.println(request);
				byte[] output = request.getBytes();
				
				for(OutputStream stream : clients) {
					
					stream.write(output);
					stream.flush();
				}
			}
		} catch(Throwable e) { System.out.println(e.toString()); }
		finally {
			try {

				in.close();
				out.close();
				socket.close();
			} catch(Throwable e) { System.out.println(e.toString()); }
		}
	}
	
	public static void main(String[] args) {
	
		clients = new ArrayList<OutputStream>();
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(PORT);
			
			while(true) {
				
				new Server(server.accept());
			}
		} catch(Throwable e) { System.out.println(e.toString()); }
		
		finally {
		
			try {
			
				if(server != null) {
				
					server.close();
				}
			} catch(Throwable e) { System.out.println(e.toString()); }
		}
	}
}