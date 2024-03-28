package factory.data;

import java.util.List;

import factory.interfaces.DataInterface;

public class Data implements DataInterface
{
	private Object key = null;
	private Object value = null;

	public Data(Object value)
	{
		setValue(value);
	}

	public Data(Object key, Object value)
	{
		this(value);
		setKey(key);
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public Object getValue()
	{
		return value;
	}

	public void setKey(Object key)
	{
		this.key = key;
	}

	public Object getKey()
	{
		return key;
	}

	@Override
	public void printData()
	{
		System.out.println(toString());	
	}
	
	@Override
	public String toString()
	{
		String retVal = (String) this.key + " (";
		
		if(value instanceof List<?> list)
		{
			for(int i=0; i<list.size(); i++)
			{
				retVal = retVal + list.get(i);
				
				if(i<list.size()-1)
				{
					retVal = retVal + " "; 
				}
			}
		}
		else
		{
			retVal = retVal + value.toString();
		}
		
		retVal = retVal + ")";
				
		return retVal;
	}
}
