package factory.interfaces;

import java.util.List;

public interface CalculateInterface<T extends DataInterface, V extends DataInterface>
{
	public List<V> calculate(List<T> data);
}
