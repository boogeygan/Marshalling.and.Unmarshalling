
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLtoNativeForm {
	public static String input,output;

	public static StringBuilder main(String[] args) 
	{
		XMLtoNativeForm.input="xml-IR";
		//XMLtoNativeForm.output= new String("/home/gagan/Desktop/Fio");
		try 
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			XMLParser handler = new XMLParser();
			saxParser.parse(XMLtoNativeForm.input, handler);
		  
		}
		catch (Exception e)
		{
		}
		return XMLParser.nativeData;
	}

}

class XMLParser extends DefaultHandler {
	BufferedReader br;
	BufferedWriter bw;
	File inputXML,outputNative;
	public static StringBuilder nativeData;
	StringBuilder currentData;

	public void startDocument() throws SAXException
	{
		//System.out.println("***Document Parsing Started***");
		nativeData=new StringBuilder();
		currentData=new StringBuilder();
		inputXML=new File(XMLtoNativeForm.input);
		//outputNative=new File(XMLtoNativeForm.output);
		
		try 
		{
			br=new BufferedReader(new FileReader(inputXML));
			//bw=new BufferedWriter(new FileWriter(outputNative));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void endDocument()
            throws SAXException{
		nativeData=new StringBuilder(nativeData.toString().replaceAll(" ", ""));
		
		try 
		{
			//bw.write(nativeData.toString());	
			br.close();
			//bw.close();
			//System.out.println();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		nativeData.delete(nativeData.length()-1, nativeData.length());
		System.out.println(nativeData.toString());
		//System.out.println("***Document Parsing Finished***");
	}

	public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException 
	{
		if(qName.equalsIgnoreCase("course")){
			int length=attributes.getLength();
			String attrName,attrValue;
			for(int i=0;i<length;i++)
			{
				attrName=attributes.getQName(i);
				attrValue=attributes.getValue(i);
				if(attrName.equalsIgnoreCase("name"))
				nativeData.append(":"+attrValue+",");
				else if(attrName.equalsIgnoreCase("marks"))
					nativeData.append(attrValue);
			}
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException 
	{
		if(qName.equalsIgnoreCase("student")){
			nativeData.append("\n");
		}
		else if(qName.equals("name")){
			nativeData.append(currentData);
		}
		currentData.delete(0, currentData.length());
	}
	public void characters(char ch[], int start, int length) throws SAXException 
	{
		for (int i=start; i<start+length; i++) 
		{
			currentData.append(ch[i]);
		}

	}
}
