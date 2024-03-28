package factory.data.calculate.implementation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.data.read.implementation.A1Strategy;
import property.PropertyParser;

public class Sabiranje implements CalculateStrategyInterface<Data, Data> {

	public static void main(String[] args) {
		List<Data> data = new A1Strategy().readFile(PropertyParser.parseToHashMap().get("sabiranje").path().toString());
		List<Data> rez = new Sabiranje().calculate(data);
		
		for (Data d : rez) {
			d.printData();
		}
	}

	@Override
	public List<Data> calculate(List<Data> data) {
		Double pom = 0d;
		List<Data> rezultati = new Vector<>();
		Collections.sort(data, new Comparator<Data>() {
			@Override
			public int compare(Data data1, Data data2) {
				if (data1.getKey() instanceof Comparable && data2.getKey() instanceof Comparable) {
					return ((Comparable) data1.getKey()).compareTo(data2.getKey());
				}
				return 0; // Dodajte odgovarajući slučaj ako ključevi nisu poredivi
			}
		});

		String zadnjeSlovo = data.get(data.size() - 1).getKey().toString();
		char karakter = zadnjeSlovo.charAt(0);
		int n = (int) karakter - 96;
		for (int i = 0; i < n; i++) {
			Double pom1 = rezultatSabiranja(data, i);
			rezultati.add(new Data("n" + (i + 1), pom1)); // Ispravljena formacija ključa
		}

		return rezultati;
	}

	private Double rezultatSabiranja(List<Data> data, int index) {
		Double pom = 0d;

		for (Data d : data) {
			if (d.getKey().toString().contains("[" + index + "]")) {
				pom += Double.parseDouble(d.getValue().toString());
			}
		}

		return pom;
	}

}
