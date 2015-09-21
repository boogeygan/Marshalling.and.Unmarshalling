import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class CustomByteServer 
{
	private ServerSocket serverSocket;
	public static String fileName;
	public CustomByteServer(int port) throws Exception 
	{
		serverSocket = new ServerSocket(port);
	}

	public void run()
	{
			try
			{
				Socket server = serverSocket.accept();
				BufferedWriter bw=new BufferedWriter(new FileWriter(new File("byte-IR")));
				StringBuilder data=NativeToBits.call(CustomByteServer.fileName);
				bw.write(data.toString());
				bw.close();
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF(data.toString());
				out.writeUTF("KILLCOMM");
				server.close();
			}
			catch(Exception e)
			{
			}
	}
	public static void main(String [] args)
	{
		int port =41414;//Integer.parseInt(args[0]);
		CustomByteServer.fileName=args[0];
		try
		{
			CustomByteServer t = new CustomByteServer(port);
			t.run();
		}
		catch(Exception e)
		{
			
		}
	}
}
