package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A5Strategy;
import property.PropertyParser;

public class Oduzimanje implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A5Strategy().readFile(PropertyParser.parseToHashMap().get("oduzimanje").path().toString());
		
		List<Data> rez=new Oduzimanje().calculate(data);
				
		for (Data data2 : rez) {
			data2.printData();
		}

	}

	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		List<Data> rezultati=new Vector<Data>();
		
		sortiraj(data);
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		int n=Integer.parseInt(zadnjeSlovo.substring(1));
		int c=(int)zadnjeSlovo.charAt(0)-96;
		for(int i=0;i<n;i++)
		{
			Double pom=pomocnaOduzimanje(data, i+1, c);
			
			rezultati.add(new Data("n"+(i+1),pom));
		}
		
		return rezultati;
	}
	
	private void sortiraj(List<Data> data)
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
	
	private Double pomocnaOduzimanje(List<Data> data,int index,int c)
	{
		Double[] niz=new Double[c];
		int br=0;
		Double pom=0d;
		
		for(Data d:data) {
			if(d.getKey().toString().contains(String.valueOf(index)))
			{
				niz[br]=Double.parseDouble(d.getValue().toString());
				br++;
			}
		}
		
		pom=niz[0];
		for(int i=1;i<niz.length;i++)
		{
			pom-=niz[i];
		}
		
		return pom;
	}

}
