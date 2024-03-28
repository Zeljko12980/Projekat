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

public class A5Strategy implements ReadFileStrategyInterface<Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Data> data=new A5Strategy().readFile(PropertyParser.parseToHashMap().get("oduzimanje").path().toString());
		
		for(Data d:data)
		{
			d.printData();
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
			Pattern pattern=Pattern.compile("(\\w\\d)\\-(\\-?[\\d.]+)");
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
		else {
			System.out.println("Fajl se ne moze otvoriti ili ne postoji");
		}
		return vektori;
	}

}
