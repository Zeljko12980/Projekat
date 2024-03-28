package factory.data.calculate.interfaces;

import java.util.List;

import factory.interfaces.DataInterface;

public interface CalculateStrategyInterface<T extends DataInterface, V extends DataInterface>
{
	public List<V> calculate(List<T> data);
}
