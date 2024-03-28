package factory.data.write.implementation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import factory.data.Data;
import factory.data.calculate.Calculate;
import factory.data.calculate.implementation.Dijeljenje;
import factory.data.read.implementation.A4Strategy;
import factory.data.write.interfaces.WriteFileStrategyInterface;
import property.PropertyParser;
/**Klasa ima za cilj da implementira writeFileStrategyInterFace 
 * @author Zeljko Ikanovic 2177 
 * 
 * **/
public class WriteB4Strategy implements WriteFileStrategyInterface<Data> {
/**
 * Metod ima za cilj testiranje ostalih metoda
 * 
 * **/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Data> rezultati=new Dijeljenje().calculate(new A4Strategy().readFile(PropertyParser.parseToHashMap().get("dijeljenje").path().toString()));
	WriteB4Strategy br=new WriteB4Strategy();
	br.writeToScreen(rezultati);
	
	}
/**
 * Metod ima za cilj upis rezultata u file koji se proslijedi
 * @author Zeljko Ikanovic 2177
 * @param scanner skener , lista objekata tipa Data
 * @return void nista
 * @version 1.0.0
 * 
 * **/
	@Override
	public void writeToFile(Scanner scanner, List<Data> data) {
		// TODO Auto-generated method stub
		
		System.out.println("Unesite putanju do novog fajla:");
		String newFile = "";
		if(scanner.hasNext())
			newFile = scanner.nextLine();
		Path path = Paths.get(newFile);
		
		BufferedWriter writer = null;
		try {
			if(Files.notExists(path.getParent()))
				Files.createDirectories(path.getParent());
			if(Files.notExists(path))
					Files.createFile(path);
			
			if(Files.exists(path) && Files.isWritable(path)) {
				writer = new BufferedWriter(new FileWriter(path.toFile()));
				writer.write(writeToString(data));
			}
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
/**
 * Metod ima za cilj ispis rezultata na konzoli
 * @author Zeljko Ikanovic 2177
 * @param lista objekata tipa Data
 * @return void
 * @version 1.0.0
 * 
 * **/
	@Override
	public void writeToScreen(List<Data> data) {
		// TODO Auto-generated method stub
		
		System.out.println(new WriteB4Strategy().writeToString(data));
		
	}
	
	/**
	 * Metod vraca string po zadatom sablonu
	 * @author Zeljko Ikanovic
	 * @param rezultati operacije
	 * @return string
	 * @version 1.0.0
	 * **/
	private String writeToString(List<Data> resultData)
	{
		String s="data\nb4\n";
		for(int i=0;i<resultData.size();i++)
		{
			s+="rez("+(i+1)+") = "+resultData.get(i).getValue()+";\n";
		}
		
		return s;
	}

}
