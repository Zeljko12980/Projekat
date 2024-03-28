package factory.data.read.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import factory.data.Data;
import factory.data.read.interfaces.ReadFileStrategyInterface;
import property.PropertyParser;

public class A1Strategy implements ReadFileStrategyInterface<Data> {

    public static void main(String[] args) {
        // Kreiranje instance A1Strategy klase
        A1Strategy strategy = new A1Strategy();

        // Čitanje fajla i dobijanje liste objekata Data
        List<Data> lista = strategy.readFile(PropertyParser.parseToHashMap().get("sabiranje").path().toString());

     // Sortiranje liste objekata po ključu
        Collections.sort(lista, new Comparator<Data>() {
            @Override
            public int compare(Data data1, Data data2) {
                // Poređenje ključeva objekata data1 i data2
                return ((Comparable) data1.getKey()).compareTo(data2.getKey());
            }
        });



        // Prikaz sortirane liste
        for (Data data : lista) {
            System.out.println(data);
        }
    }

    @Override
    public List<Data> readFile(String path) {
        List<Data> lista = new Vector<>();
        File file = new File(path);
        if (file.exists() && file.canRead()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                bufferedReader.readLine(); // Preskačemo prve tri linije
                bufferedReader.readLine();
                bufferedReader.readLine();
                Pattern pattern = Pattern.compile("(\\w\\d)\\{\\[([\\d.]+)\\]\\;\\[([\\d.]+)\\]\\;\\[([\\d.]+)\\]\\}\\:?");
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        lista.add(new Data(matcher.group(1) + "[0]", Double.parseDouble(matcher.group(2))));
                        lista.add(new Data(matcher.group(1) + "[1]", Double.parseDouble(matcher.group(3))));
                        lista.add(new Data(matcher.group(1) + "[2]", Double.parseDouble(matcher.group(4))));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

}
