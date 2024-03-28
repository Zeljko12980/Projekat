package factory.data.calculate.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A4Strategy;
import property.PropertyParser;
/**
 * Klasa implementira CalculateStrategyInterface
 * @author Zeljko Ikanovic 2177
 * **/
public class Dijeljenje implements CalculateStrategyInterface<Data, Data> {

	/**
	 * Pomocna metoda koja ima za cilj testiranje implementacije metode calculate
	 *@author Zeljko Ikanovic 2177
	 * 
	 * **/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> data=new A4Strategy().readFile(PropertyParser.parseToHashMap().get("dijeljenje").path().toString());
		
		List<Data> rez=new Dijeljenje().calculate(data);
		
		for(Data d:rez)
		{
			d.printData();
		}

	}
/**
 * 
 * Metoda calcuate ima za cilj izracunavanje po zadatom algoritmu
 * @author Zeljko Ikanovic 2177
 * @param Lista objekata tipa Data
 * @return listu rezultata
 * **/
	@Override
	public List<Data> calculate(List<Data> data) {
		// TODO Auto-generated method stub
		List<Data> rezultati=new Vector<Data>();
		
		
		int index = Integer.parseInt( data.get(0).getKey().toString().substring(1));
		for(int i=0;i<data.size();i++)
		{
			if(Integer.parseInt(data.get(i).getKey().toString().substring(1))>index)
				index=Integer.parseInt(data.get(i).getKey().toString().substring(1));
		}
		System.out.println(index);
		for(int i=0;i<index;i++)
		{
			Double pom=dijeljenje(data,i+1);
			rezultati.add(new Data("n"+(i+1),pom));
		}
		
		
		return rezultati;
	}
	/**
	 * Pomocna metoda koja vraca rezultate dijeljenje pojedinacno za svaku iteraciju
	 * @author Zeljko Ikanovic 2177
	 * @param Lista objekata tipa Data,index iteracije
	 * @return rezultat iteracije
	 * @version 1.0.0
	 * 
	 * **/
	
	private Double dijeljenje(List<Data> data,int index)
	{
List<Double> niz=new Vector<Double>();
// Sortiranje liste objekata po ključu
Collections.sort(data, new Comparator<Data>() {
    @Override
    public int compare(Data data1, Data data2) {
        // Poređenje ključeva objekata data1 i data2
        return ((Comparable) data1.getKey()).compareTo(data2.getKey());
    }
});


	        for (Data element : data) {
	            if (element.getKey().toString().contains(String.valueOf(index))) {
	                niz.add(Double.parseDouble(element.getValue().toString()));
	            }
	        }

	        if (niz.isEmpty()) {
	            throw new IllegalArgumentException("Nema podataka za dijeljenje sa indeksom " + index);
	        }

	      System.out.println();
	        Double rezultat = niz.get(0).doubleValue();
	        for (int i = 1; i <niz.size(); i++) {
	            rezultat /= niz.get(i);
	        }
	      
niz.clear();
	        return rezultat;
		
		
	}

}