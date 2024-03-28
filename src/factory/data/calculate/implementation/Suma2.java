package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A10Strategy;
import factory.data.read.implementation.A7Strategy;
import property.PropertyParser;

public class Suma2 implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A10Strategy().readFile(PropertyParser.parseToHashMap().get("suma2").path().toString());
		List<Data> rez=new Suma2().calculate(data);

		for (Data data2 : rez) {
			data2.printData();
		}
	}

	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		List<Data> rezulati=new Vector<Data>();
		int n=brojResenja(data);
		char c=zadnjiKarakter(data);
				
for(int i=0;i<n;i++)
{
	Double pom=pomocnaSuma(data, i, c, n);
	rezulati.add(new Data("n"+(i+1),pom));
}
		
		
		return rezulati;
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
	
	private int brojResenja(List<Data> data)
	{
		sortiraj(data);
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		int indexZagrade=zadnjeSlovo.lastIndexOf('[');
		int broj=Integer.parseInt(zadnjeSlovo.substring(1, indexZagrade));
		return broj;
	}
	private char zadnjiKarakter(List<Data> data)
	{
		sortiraj(data);
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		char c=zadnjeSlovo.charAt(0);
		
		return c;
	}
	
	private Double pomocnaSuma(List<Data> data,int index,char c,int brojResenja)
	{
		List<Double> niz=new Vector<Double>();
		Double pom=0d;

		for(Data d:data)
		{
			if(d.getKey().toString().contains("["+index+"]") &&(d.getKey().toString().contains(String.valueOf(c))!=true))
				pom+=Double.parseDouble(d.getValue().toString());
			else if(d.getKey().toString().contains(String.valueOf(c)+(index+1)))
			{
				niz.add(Double.parseDouble(d.getValue().toString()));
			}
		}
		System.out.println("POM: "+pom);
		Double max=Collections.max(niz);
		pom*=max;
		
		return pom;
		
	}

}
