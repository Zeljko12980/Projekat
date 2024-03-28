package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A3Strategy;
import property.PropertyParser;

public class Potencije implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A3Strategy().readFile(PropertyParser.parseToHashMap().get("potencije").path().toString());
		List<Data> rez=new Potencije().calculate(data);
		for(Data d:rez)
		{
			d.printData();
		}

	}

	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		List<Data> lista=new Vector<Data>();
		Collections.sort(lista,new Comparator<Data>() {
			public int compare(Data data1,Data data2)
			{
				if(data1.getKey() instanceof Comparable && data2.getKey() instanceof Comparable)
				 return (((Comparable)data1.getKey()).compareTo(data2.getKey()));
				return 0;
			}
			
		});
		
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		int c=(int)zadnjeSlovo.charAt(0)-96;
		int n=Integer.parseInt(zadnjeSlovo.substring(1));
		for(int i=0;i<n;i++)
		{
			Double rext=pomocPotencije(data, i+1, c);
			lista.add(new Data("n"+(i+1),rext));
		}
		
		return lista;
	}
	
	private Double pomocPotencije(List<Data> data,int index,int c)
	{
		Double[] niz=new Double[c];
		int br=0;
		Double suma=0d;
		Double pom=1d;
		Double eksp=0d;
		for(Data d:data)
		{
			if(d.getKey().toString().contains(String.valueOf(index)))
			{
				niz[br]=Double.parseDouble(d.getValue().toString());
				br++;
			}
		}
		
		
		for(Double d:niz)
		{
			suma+=d;
		}
		Double rez=0d;
	
		for(int i=0;i<niz.length;i++)
		{
			eksp=suma-niz[i];
			
			pom*=Math.pow(niz[i], eksp);
			
		
			rez+=pom;
			pom=1d;
		}
		return rez;
		
	}

}
