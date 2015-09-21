
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class XMLClient
{
   public static void main(String [] args)
   {
      String serverName = "localhost";
      int port = 41415;
      try
      {
		 TimeUnit.SECONDS.sleep(3);
 		 StringBuilder data=XMLtoNativeForm.main(null);
		 File out=new File("xml-Output");
		 BufferedWriter bw=new BufferedWriter(new FileWriter(out));
    		 Socket client = new Socket(serverName, port);
		 InputStream inFromServer = client.getInputStream();
		 DataInputStream in = new DataInputStream(inFromServer);
		 String recv=in.readUTF();
		 bw.write(data.toString());
		 bw.close();
		 client.close();
      }
      catch(Exception e)
      {

      }
   }
}
