package main;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import factory.data.Data;
import factory.data.DataFactory;
import factory.interfaces.CalculateInterface;
import factory.interfaces.InputFileInterface;
import helper.HelperClass;
import helper.TitlePosition;
import property.PropertyParser;
import property.data.PropertyDataStructure;

public class MainClass
{
	public MainClass()
	{
		// parsiranje property fajlova	ovaj kod prolazi kroz strukturu ovih fajlova
		
		ArrayList<PropertyDataStructure> pathList = PropertyParser.parse();

		// ispisivanje menija
		try (Scanner scanner = new Scanner(new BufferedInputStream(System.in)))
		{
			scanner.useLocale(new Locale("sr", "BA"));
			scanner.useDelimiter("\r\n");

			menu: while (true)
			{
				System.out.println(HelperClass.headerLine(40, "Command-line ToolBox", TitlePosition.LEFT));
				for (int i = 0; i < pathList.size(); i++)
				{
					System.out.println("[" + (i + 1)
							+ "] "
							+ pathList.get(i).name());
				}
				System.out.println("[0] Izlazak iz programa");
				System.out.println(HelperClass.footerLine(40));
				System.out.print("Unesite vrijednost: ");
				String s = scanner.nextLine();

				switch (s)
				{
				case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10":
					System.out.println();

					int choice = Integer.valueOf(s)
							- 1;

					// Kreiranje fabrike
					DataFactory factory = new DataFactory();

					// Kreiranje inputFile elementa
					InputFileInterface<Data> input = factory.createInput();
					List<Data> data = input.readData(pathList.get(choice));
					// Pozivanje metode za izracunavanje
					CalculateInterface<Data, Data> calculate = factory.createCalculate(pathList.get(choice));
					// Pozivanje metode za ispis podataka (na ektran i u fajl)
					factory.createOutput(scanner, pathList.get(choice)).writeData(calculate.calculate(data));

					break;
				case "0":
					break menu;
				default:
					System.out.println("Izabrana vrijednost ne postoji!");
					break;
				}

				System.out.println();
			}
		}
		catch (Exception e)
		{
			System.out.println("Dogodila se greska!! OPIS:"
					+ e.getMessage());
		}

		System.out.println("Izlazak iz programa!");
	}

	public static void main(String[] args)
	{
		new MainClass();
	}
}
