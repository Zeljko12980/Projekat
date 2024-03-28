package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A6Strategy;
import factory.data.read.implementation.A8Strategy;
import property.PropertyParser;

public class Mnozenje2 implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A8Strategy().readFile(PropertyParser.parseToHashMap().get("mnozenje2").path().toString());
		
		List<Data> rez=new Mnozenje2().calculate(data);
		
		for (Data data2 : rez) {
			data2.printData();
		}

	}

	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		List<Data> rez=new Vector<Data>();
		int n=BrojRjesenjaAlgoritma(data);
		char c=zadnjiKarakter(data);
		

		for(int i=0;i<n;i++)
		{
			Double pom=pomocnaMnozenje(data, i+1, c, n);
			rez.add(new Data("n"+(i+1),pom));
		}
		
		return rez;
	}
	private char zadnjiKarakter(List<Data> data)
	{
		sortirajListu(data);
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		char c=zadnjeSlovo.charAt(0);
		
		return c;
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
	private int brojElemenataUnutarZagrade(List<Data> data)
	{
		int br=0;
		sortirajListu(data);
		char c=data.get(0).getKey().toString().charAt(0);
		for(Data d:data)
		{
			if(d.getKey().toString().contains(String.valueOf(c)+1))
				br++;
		}
		
		return br;
	}
	
	private int BrojRjesenjaAlgoritma(List<Data> data)
	{
		sortirajListu(data);
		String zadnjeSlovo=data.get(data.size()-1).getKey().toString();
		int indexZagrade=zadnjeSlovo.lastIndexOf('[');
		int broj=Integer.parseInt(zadnjeSlovo.substring(1,indexZagrade));
		
		return broj;
	}
	
	private Double pomocnaMnozenje(List<Data> data,int index,char c,int n)
	{
		Double pom=0d;
		int br=0;
		Double eksponent=0d;
		Double k=1d;
int brojKaraktera=(int)c-96;
		n=brojElemenataUnutarZagrade(data);
		System.out.println("N: "+n);
		char m=(char)(c-(brojKaraktera-1));
		Double rez=0d;
		Double proizvod=1d;
		sortirajListu(data);
		//System.out.println("M: "+m);
		for(Data d:data)
		{
			if(d.getKey().toString().contains(String.valueOf(c)+index))
				eksponent=Double.parseDouble(d.getValue().toString());
			if(d.getKey().toString().contains(String.valueOf(m)+index))
			{
				if(br<n-1)
				{
				//System.out.println("Vrijednost - "+d.getValue());
				pom+=Double.parseDouble(d.getValue().toString());
				br++;
				}
				
			}
			if(d.getKey().toString().contains(String.valueOf(m)+index+"["+(n-1)+"]"))
			{
			
					
			//	System.out.println("POM: "+pom);
				k=Math.pow(pom,Double.parseDouble(d.getValue().toString()));
				//	System.out.println("K: "+k); 			
					proizvod*=k;
					//System.out.println("Proizvod: "+proizvod);
					//System.out.println("-----------------------------------------");
					m+=1;
					pom=0d;
				//	System.out.println("M: "+m);
					br=0;
				
			}
		}
		
		
		
		rez=(proizvod* eksponent);
		proizvod=1d;
		return rez;
}
}
