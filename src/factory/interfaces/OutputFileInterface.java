package factory.interfaces;

import java.util.List;

public interface OutputFileInterface<T extends DataInterface>
{
	public void writeData(List<T> data);
}
