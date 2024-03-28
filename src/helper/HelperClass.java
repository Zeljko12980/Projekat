package helper;

public class HelperClass
{
	public static String headerLine(int size, String title, TitlePosition position)
	{
		String retVal = "";

		if (size > (title.length() + 1))
		{
			for (int i = 0; i < (size - title.length()
					- 1); i++)
			{
				retVal = retVal + "-";
			}

			switch (position)
			{
			case LEFT:
			{
				retVal = title + " " + retVal;
				break;
			}
			case RIGHT:
			{
				retVal = retVal + " " + title;
				break;
			}
			}

		}
		else
		{
			retVal = title;
		}

		return retVal;
	}

	public static String footerLine(int size)
	{
		String retVal = "";

		for (int i = 0; i < size; i++)
		{
			retVal = retVal + "-";
		}

		return retVal;
	}
}
