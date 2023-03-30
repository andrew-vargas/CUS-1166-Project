import java.awt.Component;
import java.io.BufferedReader; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class CCServer {

	static ServerSocket serverSocket;
	static Socket ServerSocket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket (4999);
		
		JFrame frame = new HomePage().new CloudControllerHome().getCcHomeFrame();
		frame.setVisible(false);
		// frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		// not sure why this won't work ^
		
		JFrame emptyframe = new JFrame();
		emptyframe.setSize(1, 1);
		emptyframe.setVisible(false);
		emptyframe.setLocationRelativeTo(null);
		emptyframe.setResizable(false);
		
		while(true) {
			ServerSocket = ss.accept();
			
			System.out.println("Client connected");
			InputStreamReader inreader = new InputStreamReader(ServerSocket.getInputStream());
			BufferedReader br = new BufferedReader(inreader);
			
			String tempStr = br.readLine();
			
			PrintWriter ccwriter = new PrintWriter(ServerSocket.getOutputStream());
			
			if (tempStr != null){
				emptyframe.setVisible(true);
				int result = JOptionPane.showConfirmDialog(emptyframe,"Accept new request?", "Incoming request",JOptionPane.YES_NO_OPTION);
				emptyframe.setVisible(false);
				if (result == 0) {
					if (!frame.isVisible())
					{
						frame.setVisible(true);
					}
					tempStr = "yes";
					ccwriter.println(tempStr);
				}
				else if (result == 1){
					tempStr = "no";
					ccwriter.println(tempStr);
				}
			}
			else {
				System.out.println("Server malfunction");
			}
			
			System.out.println("Client: "+tempStr);
			
			ccwriter.flush();
		}
	}
	
}
