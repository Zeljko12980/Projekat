package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A11Strategy;
import property.PropertyParser;

public class Potencije2 implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A11Strategy().readFile(PropertyParser.parseToHashMap().get("potencije2").path().toString());
		List<Data> rez=new Potencije2().calculate(data);
		
		for (Data data2 : rez) {
			data2.printData();
		}

	}

	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		List<Data> rez=new Vector<Data>();
	int n=brojRjesenja(data);
	
	for(int i=0;i<n;i++)
	{
	Double pomocna=pomocnaPotencije(data, i+1);
	
	rez.add(new Data("n"+(i+1),pomocna));
	}
		
		
		return rez;
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
	
	private int brojRjesenja(List<Data> data)
	{
		sortiraj(data);
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		int broj=Integer.parseInt(zadnjeSlovo.substring(1));
		
		return broj;
	}
	private Double pomocnaPotencije(List<Data> data,int index )
	{
		List<Double> brojevi=new Vector<Double>();
		Double suma=0d;
		for(Data d:data)
		{
			if(d.getKey().toString().contains(String.valueOf(index)))
			{
				suma+=Double.parseDouble(d.getValue().toString());
				brojevi.add(Double.parseDouble(d.getValue().toString()));
			}
		}
		Double pom;
		Double rez=0d;
		for(Double d:brojevi)
		{
			pom=Math.pow(d, (suma-d));
			rez+=pom;
		}
		
		return rez;
		
	}
	

}
