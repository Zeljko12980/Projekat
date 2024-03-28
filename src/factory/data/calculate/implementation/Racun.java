package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A2Strategy;
import property.PropertyParser;

public class Racun implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A2Strategy().readFile(PropertyParser.parseToHashMap().get("racun").path().toString());
		List<Data> rez=new Racun().calculate(data);
		
		for(Data d:rez)
		{
			d.printData();
		}

	}

	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		Collections.sort(data, new Comparator<Data>() {
			@Override
			public int compare(Data data1, Data data2) {
				if (data1.getKey() instanceof Comparable && data2.getKey() instanceof Comparable) {
					return ((Comparable) data1.getKey()).compareTo(data2.getKey());
				}
				return 0; // Dodajte odgovarajući slučaj ako ključevi nisu poredivi
			}
		});
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		char c=zadnjeSlovo.charAt(0);
		int razlicithSlova=(int)c-96;
		List<Data> rezultati= new Vector<Data>();
		int index=zadnjeSlovo.lastIndexOf('[');
		int brojeva=Integer.parseInt(zadnjeSlovo.substring(1, index));
		int n=brojRjesenjaAlgoritma(c, data,brojeva);
		Double pom1;
		System.out.println("N: "+n+" BR: "+brojeva);
		
		for(int i=0;i<n;i++)
		{
			pom1=new Racun().pomocRacuna(data, i, brojeva, razlicithSlova);
			
			rezultati.add(new Data("n"+(i+1),pom1));
		}
		
		
		
		return rezultati;
	}
	private int brojRjesenjaAlgoritma(char c,List<Data> data,int brojeva)
	{
		int n=0;
		for(Data d:data)
		{
			if(d.getKey().toString().contains(String.valueOf(c)+brojeva))
				n++;
		}
		return n;
	}
	
	private Double pomocRacuna(List<Data> data,int index,int brojeva,int n)
	{
		Double[] niz=new Double[n*brojeva];
		Double[] niz2=new Double[n];
		int br=0;
		Double pom=0d;
		for(Data d:data)
		{
			if(d.getKey().toString().contains("["+index+"]"))
				{niz[br]=Double.parseDouble(d.getValue().toString());
				br++;
		}}
		
		for(Double d:niz)
		{
			System.out.println(d);
		}
		br=0;
		int k=0;
		int temp=0;
		while(br<n)
		{
			for(int i=k;i<niz.length;i++)
			{
				if(temp<brojeva)
				{
					pom+=niz[i];
					k++;
					temp++;
				}
			}
			temp=0;
			System.out.println("Brojeva: "+brojeva);
			niz2[br]=pom;
			br++;
			pom=0d;
		}
		for (Double double1 : niz2) {
			System.out.println(double1);
		}
		
		pom=1d;
	
		for(int i=0;i<niz2.length-1;i++)
		{
			pom*=niz2[i];
		}
		System.out.println("Double : "+pom);
		pom-=niz2[niz2.length-1];
		
		return pom;
		
		
	}

}
