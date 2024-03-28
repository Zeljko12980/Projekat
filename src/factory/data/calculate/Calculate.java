package factory.data.calculate;

import java.util.List;

import factory.data.Data;
import factory.data.calculate.interfaces.CalculateStrategyInterface;
import factory.interfaces.CalculateInterface;
import property.data.PropertyDataStructure;
import factory.data.calculate.implementation.*;

public class Calculate implements CalculateInterface<Data, Data>
{

	PropertyDataStructure property = null;
	CalculateStrategyInterface<Data, Data> calculateStrategy = null;

	public Calculate(PropertyDataStructure property)
	{
		this.property = property;

		switch (property.name().toLowerCase())
		{
		case "sabiranje":
			// TODO: izvrsiti inicijalizaciju algoritma za sabiranje
			calculateStrategy=new Sabiranje();
			break;
		case "racun":
			calculateStrategy=new Racun();
			// TODO: izvrsiti inicijalizaciju algoritma za racun
			break;
		case "potencije":
			calculateStrategy=new Potencije();
			// TODO: izvrsiti inicijalizaciju algoritma za potencije
			
			break;
		case "dijeljenje":
			calculateStrategy=new Dijeljenje();
			// TODO: izvrsiti inicijalizaciju algoritma za djeljenje
			
			break;
		case "oduzimanje":
			calculateStrategy=new Oduzimanje();
			// TODO: izvrsiti inicijalizaciju algoritma za proracun
			break;
		case "mnozenje":
			calculateStrategy=new Mnozenje();
			// TODO: izvrsiti inicijalizaciju algoritma za proracun
			break;
		case "suma":
			calculateStrategy=new Suma();
			// TODO: izvrsiti inicijalizaciju algoritma za proracun
			break;
		case "mnozenje2":
			calculateStrategy=new Mnozenje2();
			// TODO: izvrsiti inicijalizaciju algoritma za proracun
			break;
		case "sabiranje2":
			calculateStrategy=new Sabiranje2();
			// TODO: izvrsiti inicijalizaciju algoritma za proracun
			break;
		case "potencije2":
			calculateStrategy=new Potencije2();
			// TODO: izvrsiti inicijalizaciju algoritma za proracun
			break;
		case "suma2":
			calculateStrategy=new Suma2();
			break;
		default:
			break;
		}
	}

	@Override
	public List<Data> calculate(List<Data> data)
	{
		return calculateStrategy.calculate(data);
	}

}
