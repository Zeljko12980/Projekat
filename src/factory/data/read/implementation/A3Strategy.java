package factory.data.read.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import factory.data.Data;
import factory.data.read.interfaces.ReadFileStrategyInterface;
import property.PropertyParser;

public class A3Strategy implements ReadFileStrategyInterface<Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A3Strategy().readFile(PropertyParser.parseToHashMap().get("potencije").path().toString());
		
		for (Data data2 : data) {
			data2.printData();
		}

	}

	@Override
	public List<Data> readFile(String path) {
		// TODO Auto-generated method stub
		List<Data> vektori=new Vector<Data>();
		File file=new File(path);
		
		if(file.exists() && file.canRead())
		{
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
		bufferedReader.readLine();
		bufferedReader.readLine();
		bufferedReader.readLine();
		
			Pattern pattern=Pattern.compile("(\\w\\d)\\[([\\d.]+)\\]");
			while(bufferedReader.ready())
			{
				Matcher matcher=pattern.matcher(bufferedReader.readLine());
				while(matcher.find())
				{
					vektori.add(new Data(matcher.group(1),Double.parseDouble(matcher.group(2))));
					
				}
			}
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Nije moguce procitati ili otvoriti file");
		}
		
		return vektori;
	}

}
