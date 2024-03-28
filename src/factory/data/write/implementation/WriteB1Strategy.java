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
import factory.data.calculate.implementation.Sabiranje;
import factory.data.read.implementation.A1Strategy;
import factory.data.write.interfaces.WriteFileStrategyInterface;
import property.PropertyParser;

public class WriteB1Strategy implements WriteFileStrategyInterface<Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A1Strategy().readFile(PropertyParser.parseToHashMap().get("sabiranje").path().toString());
		List<Data> rezultat=new Sabiranje().calculate(data);
		
		new WriteB1Strategy().writeToScreen(rezultat);

	}

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

	@Override
	public void writeToScreen(List<Data> data) {
		// TODO Auto-generated method stub
		System.out.println(new WriteB1Strategy().writeToString(data));
		
	}
	
	private String writeToString(List<Data> resultData)
	{
		String s="\ndata\nb1\n";
		for(int i=0;i<resultData.size();i++)
		{
			s+="rezultat_"+(i+1)+" = "+resultData.get(i).getValue()+";\n";
		}
		
		return s;
	}

}
