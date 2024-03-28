package factory.data.read;

import java.util.List;

import factory.data.Data;
import factory.data.read.implementation.A10Strategy;
import factory.data.read.implementation.A11Strategy;
import factory.data.read.implementation.A1Strategy;
import factory.data.read.implementation.A2Strategy;
import factory.data.read.implementation.A3Strategy;
import factory.data.read.implementation.A4Strategy;
import factory.data.read.implementation.A5Strategy;
import factory.data.read.implementation.A6Strategy;
import factory.data.read.implementation.A7Strategy;
import factory.data.read.implementation.A8Strategy;
import factory.data.read.implementation.A9Strategy;
import factory.data.read.interfaces.ReadFileStrategyInterface;
import factory.interfaces.DataInterface;
import factory.interfaces.InputFileInterface;
import property.data.PropertyDataStructure;

public class InputFile implements InputFileInterface<Data>
{
	private List<Data> data = null;
	
	@Override
	public List<Data> readData(PropertyDataStructure property)
	{
		ReadFileStrategyInterface<Data> readFileStrategy = null;
		
		// TODO: Da li ovdje koristiti classLoader (dinamicko kreiranje objekata)?
		// Moze se koristi i HashMapa sa unaprijed definisanim objektima
		switch (property.readStrategy())
		{
		case "a1":
			readFileStrategy=new A1Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a1"
			break;
		case "a2":
			readFileStrategy=new A2Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a2"
			break;
		case "a3":
			readFileStrategy=new A3Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a3"
		
			break;
		case "a4":
			readFileStrategy=new A4Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a4"
				
			break;
		case "a5":
			readFileStrategy=new A5Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a5"
			break;
		case "a6":
			readFileStrategy=new A6Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a6"
			break;
		case "a7":
			readFileStrategy=new A7Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a7"
			break;
		case "a8":
			readFileStrategy=new A8Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a8"
			break;
		case "a9":
			readFileStrategy=new A9Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a9"
			break;
		case "a10":
			readFileStrategy=new A10Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a10"
			break;
		case "a11":
			readFileStrategy=new A11Strategy();
			//TODO: izvrsiti parsiranje/citanje eksternog fajla sa strategijom citanja "a10"
			break;
		default:
			break;
		}
		
		this.data = readFileStrategy.readFile(property.path().toString());
		
		return this.data;
	}

	@Override
	public List<Data> getData()
	{
		return data;
	}
}
