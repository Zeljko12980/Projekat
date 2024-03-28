package factory.data.write.interfaces;

import java.util.List;
import java.util.Scanner;

import factory.interfaces.DataInterface;

public interface WriteFileStrategyInterface<T extends DataInterface>
{
	public void writeToFile(Scanner scanner, List<T> data);
	public void writeToScreen(List<T> data);
}
