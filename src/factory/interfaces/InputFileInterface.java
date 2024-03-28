package factory.interfaces;

import java.util.List;

import property.data.PropertyDataStructure;

public interface InputFileInterface<T extends DataInterface>
{
	public List<T> readData(PropertyDataStructure property);
	public List<T> getData();
}
