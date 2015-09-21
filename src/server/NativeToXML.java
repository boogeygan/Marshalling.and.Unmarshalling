
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class NativeToXML {
	public static StringBuilder main(String args) {
		BufferedReader br;
		//BufferedWriter bw;
		StringBuilder xmlData=null;

		try
		{
	//		inputFile=new File(args[0]);
			br = new BufferedReader(new FileReader(args));
//			xmlOutput=new File(args[1]);
			//bw=new BufferedWriter(new FileWriter("/home/gagan/Desktop/o"));
			String line;
			String studentRecord[],studentMarks[];
			xmlData=new StringBuilder("<?xml version=\"1.0\" ?><student-record>");

			while ((line = br.readLine()) != null)
			{
				//if(line.length()==0)
					//continue;
				studentRecord=line.split(":");
				xmlData.append("<student><name>");
				xmlData.append(studentRecord[0]);
				xmlData.append("</name>");
				
				for(int i=1;i<studentRecord.length;i++){					
					//System.out.println(studentRecord[i]);
					studentMarks=studentRecord[i].split(",");
					for(int j=0;j<studentMarks.length;j=j+2){
						//System.out.println(studentMarks[j]);
						xmlData.append("<course name=\""+studentMarks[j]+"\" marks=\""+Integer.parseInt(studentMarks[j+1])+"\"/>");
					}
				}
				xmlData.append("</student>");
			}
			xmlData.append("</student-record>");
			//System.out.println(xmlData);
			//bw.write(xmlData.toString());
			//bw.append(xmlData);
			
			if(br!=null)
				br.close();
			//if(bw!=null)
			//bw.close();
		}
		catch(Exception e)
		{

		}
		return xmlData;

	}

}
