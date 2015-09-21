import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Record{
	int numberOfCourses;
	int nameLength;
	int [] courseNameLength;
	
	// data stores
	String name;
	String []courses;
	int [] marks;
	
	public int getNumberOfCourses() {
		return numberOfCourses;
	}
	public void setNumberOfCourses(int numberOfCourses) {
		this.numberOfCourses = numberOfCourses;
	}
	public int getNameLength() {
		return nameLength;
	}
	public void setNameLength(int nameLength) {
		this.nameLength = nameLength;
	}
	public int[] getCourseNameLength() {
		return courseNameLength;
	}
	public void setCourseNameLength(int[] courseNameLength) {
		this.courseNameLength = courseNameLength;
	}

}


public class NativeToBits {
	
	public static StringBuilder call(String args)
	{
		File input=new File(args);
		BufferedReader br;
		String line;
		Record record=new Record();
		String []studentSplit;
		String []internal;

		StringBuilder finalStdRecordBinSnd=new StringBuilder();

		try
		{
			finalStdRecordBinSnd=new StringBuilder();
			br=new BufferedReader(new FileReader(input));
			while((line=br.readLine())!=null)
			{
				studentSplit=line.split(":");
				record.setNumberOfCourses(studentSplit.length-1);
				record.setNameLength(studentSplit[0].length());

				record.courseNameLength=new int[record.getNumberOfCourses()];
				record.courses=new String[record.getNumberOfCourses()];
				record.marks=new int[record.getNumberOfCourses()];

				finalStdRecordBinSnd.append((char)record.getNumberOfCourses());
				finalStdRecordBinSnd.append((char)studentSplit[0].length());
				finalStdRecordBinSnd.append(studentSplit[0]);			
				
				for(int j=1;j<studentSplit.length;j++)
				{
					internal=studentSplit[j].split(",");
					record.courseNameLength[j-1]=internal[0].length();
					record.courses[j-1]=internal[0];
					record.marks[j-1]=Integer.parseInt(internal[1]);
				}
				for(int j=0;j<record.courseNameLength.length;j++)
				{
					finalStdRecordBinSnd.append((char)record.courseNameLength[j]);
					finalStdRecordBinSnd.append(record.courses[j]);
				}
				
				StringBuilder allMarksBinString=new StringBuilder();
				for(int j=0;j<record.marks.length;j++)
				{
					String binStr=Integer.toBinaryString(record.marks[j]);
					while(binStr.length()<7)
						binStr="0"+binStr;
					allMarksBinString.append(binStr);
				}

				while(allMarksBinString.length()%8!=0)
					allMarksBinString.append("0");
				
				for(int i=0;i<allMarksBinString.length();i=i+8){
					finalStdRecordBinSnd.append((char)Integer.parseInt(allMarksBinString.substring(i,i+8), 2));
				}
			}
			if(br!=null)
				br.close();
		}
		catch (Exception e)
		{
		}

		return finalStdRecordBinSnd;
	}

}

