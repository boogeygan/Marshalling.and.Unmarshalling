import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.Socket;
public class CustomByteClient
{
   public static void main(String [] args)
   {
      String serverName = "localhost";
      File out;
      BufferedWriter bw;
      int port = 41414;
      try
      {
    	  String read;
		  Socket client = new Socket(serverName, port);
		  InputStream inFromServer = client.getInputStream();
		  DataInputStream in = new DataInputStream(inFromServer);
		  read=in.readUTF();
		  //System.out.println(read);
		  read=in.readUTF();
		  BitsToNative.call("byte-IR","byte-Output");
		  if(read.equalsIgnoreCase("KILLCOMM"))
			  client.close();
      }
      catch(Exception e)
      {
    	  
      }
   }
}
