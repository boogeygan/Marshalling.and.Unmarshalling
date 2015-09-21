import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BitsToNative {
	public static void main(String[] args) throws IOException 
	{
		//BitsToNative.call("byte-IR","byte-Output");
	}


	public static void call(String src, String dest)
	{

		String name,course, line;
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(src)); 
			BufferedWriter wr = new BufferedWriter(new FileWriter(dest));

			int current;
			int coursesCount, nameLength, courseNameLength;
			boolean flag=false;
			ArrayList<String> coursesList = new ArrayList<String>();
			ArrayList<String> marksList = new ArrayList<String>();


			while((current=br.read())!=-1)
			{
				//to avoid writing extra \n in file but it still writes :P
				if(flag)
					wr.write('\n'); 
				flag=true;

				String [] courseNameArr=new  String[current];
				String [] markArr=new  String[current];

				nameLength = br.read();
				name=new String();
				line=new String(); 
				//read the name, the next "nameLength" count 
				for(int i=0;i<nameLength;i++) 
					name=name+(char)br.read(); 
				
				for(int i=0;i<current;i++)
				{ 
					courseNameLength = br.read(); 
					course = new String();
					for(int j=0;j<courseNameLength;j++)
						course = course+(char)br.read(); 
					coursesList.add(course);
				}
				String marks = "";
				String b;
				int iMarks;

				for(int i=0;i<(current*7+7)/8;i++)
				{ 
					b = Integer.toBinaryString(br.read()); 
					for(int j=b.length();j<8;j++)
					{ 
						b = "0"+b; 
					} 
					marks=marks+b;
				} 
				String s = Integer.toBinaryString(current); 
				for(int i=0;i<current;i++)
				{ 
					iMarks = Integer.parseInt(marks.substring(0, 7), 2); 
					marks = marks.substring(7); 
					marksList.add(Integer.toString(iMarks)); 
				} 
				line = name; 
				for(int i=0;i<current;i++)
				{ 
					line=line+":"+coursesList.get(i)+","+marksList.get(i);
				}

				wr.write(line);
				coursesList.clear();
				marksList.clear();
				//System.out.println(line);
			} 
			if(wr!=null && br!=null)
			{ 
				wr.close();
				br.close();
			}
		} 
		catch (Exception e){}
	}

}
