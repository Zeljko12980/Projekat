package property.data;

import java.nio.file.Path;
import java.util.List;

import factory.data.Data;

/**
 * Records se ponasa kao klasa koja ima konstruktor i gettere (konstruktor sa
 * parametrima koji odredjuju tip podataka)
 * 
 * @author Wlado
 *
 */
public record PropertyDataStructure(Path path, PropertyDataEnum type, String name, String readStrategy, String writeStrategy, List<Data> testData)
{
	/**
	 * Moze da se vrsi obrada svih parametara (ili da se ispituje njihova
	 * vrijednost i pozivaju odgovarajuci Exception-i)
	 * 
	 * @param path
	 * @param type
	 * @param name
	 * @param readStrategy
	 * @param writeStrategy
	 */
	public PropertyDataStructure
	{
		if (path == null || type == null
				|| name == null
				|| readStrategy == null
				|| writeStrategy == null
				|| testData == null)
		{
			throw new IllegalArgumentException("Postavljeni parametri zapisa DataStructure nisu validni!");
		}
	}
}
