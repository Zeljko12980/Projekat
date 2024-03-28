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
/**
 * Klasa A4Strategy implementira interfejs ReadFileStrategyInterface
 * @author Zeljko Ikanovic 2177
 * @version 1.0.0
 * 
 * **/
public class A4Strategy implements ReadFileStrategyInterface<Data> {
/**
 * Main metod ima za cilj testiranje pravilne implementacije readFile metode
 * @author Zeljko Ikanovic 2177
 * 
 * **/
	public static void main(String[] args) {
		A4Strategy strat = new A4Strategy();
		for (Data data : strat.readFile(PropertyParser.parseToHashMap().get("dijeljenje").path().toString())) {
			data.printData();
		}
	}
	/**
	 * 
	 * Metoda readFile ima za cilj da procita sadrzaj fajla cija mu se lokacija proslijedi
	 * @author Zeljko Ikanovic 2177
	 * @param path string putanja do file
	 * @return Listu objekata tipa data
	 * @version 1.0
	 * **/
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
			Pattern pattern=Pattern.compile("(\\w\\d+)\\(([\\d,]+)\\)\\;?");
			while(bufferedReader.ready())
			{
				Matcher matcher=pattern.matcher(bufferedReader.readLine());
				while(matcher.find())
				{
					lista.add(new Data(matcher.group(1),Double.parseDouble(matcher.group(2).replace(',', '.'))));
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
		else {
			System.out.println("Nema file na toj putanji");
		}
		
		return lista;
	}

}
