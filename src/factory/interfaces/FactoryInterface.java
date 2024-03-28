package factory.interfaces;

import java.util.Scanner;

import property.data.PropertyDataStructure;

public interface FactoryInterface<T extends DataInterface, V extends DataInterface>
{
	CalculateInterface<T, V> createCalculate(PropertyDataStructure property);
	InputFileInterface<T> createInput();
	OutputFileInterface<V> createOutput(Scanner scanner, PropertyDataStructure property);
}
