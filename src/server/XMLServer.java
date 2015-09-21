
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class XMLServer extends Thread
{
	private ServerSocket serverSocket;
	static String inputPath;

	public XMLServer(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
		//serverSocket.setSoTimeout(100000);
	}

	public void run()
	{
		try
		{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("xml-IR")));
			StringBuilder data=NativeToXML.main(XMLServer.inputPath);
			bw.write(data.toString());
			bw.close();
//			System.out.println(data);
			Socket server = serverSocket.accept();
			DataOutputStream out = new DataOutputStream(server.getOutputStream());
			out.writeUTF(data.toString());
			server.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public static void main(String [] args)
	{
		int port = 41415;
		inputPath=args[0];
		try
		{
			Thread t = new XMLServer(port);
			t.start();
		}
		catch(Exception e)
		{

		}
	}
}
