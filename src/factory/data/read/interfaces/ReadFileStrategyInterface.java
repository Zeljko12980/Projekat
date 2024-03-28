package factory.data.read.interfaces;

import java.util.List;

import factory.interfaces.DataInterface;

public interface ReadFileStrategyInterface<T extends DataInterface>
{
	public List<T> readFile(String path);
}
