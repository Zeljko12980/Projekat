package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A9Strategy;
import property.PropertyParser;

public class Sabiranje2 implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A9Strategy().readFile(PropertyParser.parseToHashMap().get("sabiranje2").path().toString());
		List<Data> rez=new Sabiranje2().calculate(data);
		for(Data d:rez)
			d.printData();

	}

	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		List<Data> rezultati=new Vector<Data>();
	
		
		int n=brojResenja(data);
		for(int i=0;i<n;i++)
		{
			Double pomocna=pomocSabiranja(data, i);
			
			rezultati.add(new Data("n"+(i+1),pomocna));
		}
		
		
		
		
		return rezultati;
	}
	
	private void sortirajListu(List<Data> data)
	{
		Collections.sort(data,new Comparator<Data>() {
			
			public int compare(Data data1,Data data2)
			{
				if(data1.getKey() instanceof Comparable && data2.getKey() instanceof Comparable)
					return (((Comparable)data1.getKey()).compareTo(data2.getKey()));
				
				return 0;
			}
		});
	}
	
	private int brojResenja(List<Data> data)
	{
		int brojac=0;
		sortirajListu(data);
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		int indexZagrade=zadnjeSlovo.lastIndexOf('[');
		char character=zadnjeSlovo.charAt(0);
		int broj=Integer.parseInt(zadnjeSlovo.substring(1,indexZagrade));
		
		for(Data d:data)
		{
			if(d.getKey().toString().contains(String.valueOf(character)+broj))
				brojac++;
		}
		
		return brojac;
	}
	
	private Double pomocSabiranja(List<Data> data,int index)
	{
		Double pom=0d;
		
		for(Data d:data)
		{
			if(d.getKey().toString().contains("["+index+"]"))
				pom+=Double.parseDouble(d.getValue().toString());
		}
		
		return pom;
	}

}
