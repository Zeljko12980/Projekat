package property;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import factory.data.Data;
import helper.HelperClass;
import helper.TitlePosition;
import property.data.PropertyDataEnum;
import property.data.PropertyDataStructure;

public class PropertyParser{
	private static String path = "files";
	
	public static ArrayList<PropertyDataStructure> parse()
	{
		ArrayList<PropertyDataStructure> pathList = new ArrayList<>();

		Path path = Paths.get(PropertyParser.path);

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{txt}"))
		{
			for (Path tmpPath : stream)
			{
				File file = new File(tmpPath.toString());

				if (file.exists() && file.canRead())
				{
					try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
					{
						// data
						PropertyDataEnum type = null;
						String name = null;
						String readStrategy = null;
						String writeStrategy = null;
						List<Data> testData = new Vector<>();
						
						Pattern pattern = Pattern.compile("(\\w+)/(\\w+)/(\\w\\d+)/(\\w\\d+)");
						Pattern testDataPattern = Pattern.compile("(r\\d+)\\((\\d+?\\.?\\d*)\\)");

						while (bufferedReader.ready())
						{
							String data = bufferedReader.readLine();
							
							Matcher matcher = pattern.matcher(data);
							Matcher testDataMatcher = testDataPattern.matcher(data);

							while (matcher.find())
							{
								type = PropertyDataEnum.valueOf(matcher.group(1).toUpperCase());
								name = matcher.group(2);
								readStrategy = matcher.group(3);
								writeStrategy = matcher.group(4);
							}
							
							while (testDataMatcher.find())
							{
								testData.add(new Data(testDataMatcher.group(1), Double.parseDouble(testDataMatcher.group(2)))); 
							}
						}
						
						if(type != null) pathList.add(new PropertyDataStructure(tmpPath, type, name, readStrategy, writeStrategy, testData));
					}
					catch (FileNotFoundException e)
					{
						System.out.println("Fajl ne postoji!");
					}
					catch (IOException e)
					{
						System.out.println("Fajl nije moguce procitati!");
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("DirectoryStream: Fajl ne postoji!");
			return null;
		}
		catch (IOException e1)
		{
			System.out.println("DirectoryStream: Nije moguce pristupiti fajlovima!");
			return null;
		}
		
		//testData(pathList);
		return pathList;
	}

	public static HashMap<String, PropertyDataStructure> parseToHashMap()
    {
		ArrayList<PropertyDataStructure> arrayList=parse();
        HashMap<String, PropertyDataStructure> hashMap = new HashMap<>();
  
        for (PropertyDataStructure structure : arrayList) {
  
            hashMap.put(structure.name().toLowerCase(), structure);
        }
  
        return hashMap;
    }
	
	private static void testData(ArrayList<PropertyDataStructure> pathList)
	{
		/********************************************/
		System.out.println(HelperClass.headerLine(40, "Dostupni fajlovi", TitlePosition.RIGHT));
		for (int i = 0; i < pathList.size(); i++)
		{
			// za testiranje i provjeru
			System.out.println("[" + i + "] "
					+ pathList.get(i).name() + "("
					+ pathList.get(i).path().getFileName()
					+ " | "
					+ pathList.get(i).readStrategy()
					+ " | "
					+ pathList.get(i).writeStrategy()
					+ " | "
					+ pathList.get(i).testData().toString()
					+ ")");
		}
		System.out.println(HelperClass.footerLine(40));
	}
}
