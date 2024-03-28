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
import property.data.PropertyDataStructure;

public class A8Strategy implements ReadFileStrategyInterface<Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<Data> lista=new A8Strategy().readFile(PropertyParser.parseToHashMap().get("mnozenje2").path().toString());

for(Data d:lista)
	d.printData();
		
	}

	@Override
	public List<Data> readFile(String path) {
		// TODO Auto-generated method stub
		List<Data> lista=new Vector<Data>();
		File file=new File(path);
		
		if(file.exists() && file.canRead())
		{
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			bufferedReader.readLine();
			bufferedReader.readLine();
			bufferedReader.readLine();
			Pattern pattern=Pattern.compile("(\\w\\d)\\{\\((\\-?[\\d.]+)\\)\\/\\((\\-?[\\d.]+)\\)\\/\\[([\\d.]+)\\]\\}\\;?");
			int i=0;
			while(i<2)
			{
				Matcher matcher=pattern.matcher(bufferedReader.readLine());
				while(matcher.find())
				{
					lista.add(new Data(matcher.group(1)+"[0]",Double.parseDouble(matcher.group(2))));
					lista.add(new Data(matcher.group(1)+"[1]",Double.parseDouble(matcher.group(3))));
					lista.add(new Data(matcher.group(1)+"[2]",Double.parseDouble(matcher.group(4))));
				}
				i++;
			}
			pattern=Pattern.compile("(\\w\\d)\\{\\(([\\d.]+)\\)\\}\\;?");
			while(bufferedReader.ready())
			{
				Matcher matcher=pattern.matcher(bufferedReader.readLine());
				while(matcher.find())
				{
					lista.add(new Data(matcher.group(1)+"[0]",Double.parseDouble(matcher.group(2))));
					
				}
				
			}
			
			
			bufferedReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

}
