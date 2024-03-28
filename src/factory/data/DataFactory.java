package factory.data;
/**
 * ovo su sabloni na osnovu kojih je lakse iskoristiti kod ponovo
 */

import java.util.Scanner;

import factory.data.calculate.Calculate;
import factory.data.read.InputFile;
import factory.data.write.OutputFile;
import factory.interfaces.CalculateInterface;
import factory.interfaces.FactoryInterface;
import factory.interfaces.InputFileInterface;
import factory.interfaces.OutputFileInterface;
import property.data.PropertyDataStructure;

public class DataFactory implements FactoryInterface<Data, Data>
{

	@Override
	public CalculateInterface<Data, Data> createCalculate(PropertyDataStructure property)
	{
		return new Calculate(property); //na osnovu ovog propertija vracaju se kalkulacija moja implementacija
	}
	/**
	 * calculate je klasa koja je zadata i u kojoj je kreiran property i gdje na osnovu proslijedjenog
	 * propertija u gornjem redu mozemo pozvati odagovarajucu operaciju
	 * tako sto vec imam inicijalizovanu promjenljivu koja ce predstavljati pokazivac na instancu klase Djeljenj/ Mnozenje...
	 * 
	 */
	

	@Override
	public InputFileInterface<Data> createInput()
	{
		return new InputFile();
	}

	@Override
	public OutputFileInterface<Data> createOutput(Scanner scanner, PropertyDataStructure property)
	{
		return new OutputFile(scanner, property);
	}


}
