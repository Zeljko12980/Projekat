package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.print.event.PrintJobAttributeEvent;

import factory.data.Data;
import factory.data.calculate.Calculate;
import factory.data.read.implementation.A10Strategy;
import factory.data.read.implementation.A11Strategy;
import factory.data.read.implementation.A1Strategy;
import factory.data.read.implementation.A2Strategy;
import factory.data.read.implementation.A3Strategy;
import factory.data.read.implementation.A4Strategy;
import factory.data.read.implementation.A5Strategy;
import factory.data.read.implementation.A6Strategy;
import factory.data.read.implementation.A7Strategy;
import factory.data.read.implementation.A8Strategy;
import factory.data.read.implementation.A9Strategy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import property.PropertyParser;
import property.data.PropertyDataStructure;

class CalculateTest {
    private static PropertyDataStructure dijeljenjeProperty;
    private static PropertyDataStructure sabiranjeProperty;
    private static PropertyDataStructure racunProperty;
 private static PropertyDataStructure potencijeProperty;
 private static PropertyDataStructure oduzimanjeProperty;
 private static PropertyDataStructure sabiranje2Property;
 private static PropertyDataStructure sumaProperty;
 private static PropertyDataStructure mnozenjeProperty;
 private static PropertyDataStructure mnozenje2Property;
 private static PropertyDataStructure suma2Property;
 private static PropertyDataStructure potencije2Property;
 
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        dijeljenjeProperty = PropertyParser.parseToHashMap().get("dijeljenje");
        sabiranjeProperty = PropertyParser.parseToHashMap().get("sabiranje");
        racunProperty=PropertyParser.parseToHashMap().get("racun");
        potencijeProperty=PropertyParser.parseToHashMap().get("potencije");
        oduzimanjeProperty=PropertyParser.parseToHashMap().get("oduzimanje");
    sabiranje2Property=PropertyParser.parseToHashMap().get("sabiranje2");
    sumaProperty=PropertyParser.parseToHashMap().get("suma");
    mnozenjeProperty=PropertyParser.parseToHashMap().get("mnozenje");
    mnozenje2Property=PropertyParser.parseToHashMap().get("mnozenje2");
   suma2Property=PropertyParser.parseToHashMap().get("suma2");
    potencije2Property=PropertyParser.parseToHashMap().get("potencije2");
    }

    @Test
    void dijeljenjeTest() {
        Calculate calc = new Calculate(dijeljenjeProperty);
        List<Data> calculated = calc.calculate(new A4Strategy().readFile(dijeljenjeProperty.path().toString()));
        List<Data> testData = dijeljenjeProperty.testData();
        for (int i = 0; i < testData.size(); i++) {
            assertEquals((double) testData.get(i).getValue(), (double) calculated.get(i).getValue(), 0.000001);
        }
    }

    @Test
    void sabiranjeTest() {
        Calculate calc = new Calculate(sabiranjeProperty);
        List<Data> calculated = calc.calculate(new A1Strategy().readFile(sabiranjeProperty.path().toString()));
        List<Data> testData = sabiranjeProperty.testData();
        for (int i = 0; i < testData.size(); i++) {
            assertEquals((double) testData.get(i).getValue(), (double) calculated.get(i).getValue(), 0.01);
        }
    }
    @Test
    void racunTest()
    {
    	Calculate calc=new Calculate(racunProperty);
    	List<Data> calculatedData=calc.calculate(new A2Strategy().readFile(racunProperty.path().toString()));
    	List<Data> testData=racunProperty.testData();
    	for(int i=0;i<testData.size();i++)
    	{
    		assertEquals((double)testData.get(i).getValue(), (double)calculatedData.get(i).getValue(),0.001);
    	}
    }
    
    @Test
    void potencijeTest()
    {
    	Calculate calc=new Calculate(potencijeProperty);
    	List<Data> calculatedData=calc.calculate(new A3Strategy().readFile(potencijeProperty.path().toString()));
    	List<Data> testData=potencijeProperty.testData();
    	for(int i=0;i<testData.size();i++)
    	{
    		assertEquals((double)testData.get(i).getValue(), (double)calculatedData.get(i).getValue(),0.001);
    	}
    }
    
    @Test
    void oduzimanjeTest()
    {
    	Calculate calc=new Calculate(oduzimanjeProperty);
    	List<Data> calculatedData=calc.calculate(new A5Strategy().readFile(oduzimanjeProperty.path().toString()));
List<Data> testData=oduzimanjeProperty.testData();

for(int i=0;i<testData.size();i++)
{
	assertEquals((double)testData.get(i).getValue(), (double)calculatedData.get(i).getValue(),0.01);
}
    
    }
    @Test
    void sabiranje2Test()
    {
    	Calculate calc=new Calculate(sabiranje2Property);
    	List<Data> calculatedData=calc.calculate(new A9Strategy().readFile(sabiranje2Property.path().toString()));
    	List<Data> testData=sabiranje2Property.testData();
    	for(int i=0;i<testData.size();i++)
    	{
    		assertEquals((double)testData.get(i).getValue(), (double)calculatedData.get(i).getValue(),0.01);
    	}
    	
    }
    
    @Test
    void sumaTest()
    {

    	Calculate calc=new Calculate(sumaProperty);
    	List<Data> calculatedData=calc.calculate(new A7Strategy().readFile(sumaProperty.path().toString()));
    	List<Data> testData=sumaProperty.testData();
    	for(int i=0;i<testData.size();i++)
    	{
    		assertEquals((double)testData.get(i).getValue(), (double)calculatedData.get(i).getValue(),0.01);
    	}
    }
    
    @Test
    void mnozenjeTest()
    {
    	   Calculate calc = new Calculate(mnozenjeProperty);
           List<Data> calculated = calc.calculate(new A6Strategy().readFile(mnozenjeProperty.path().toString()));
           List<Data> testData = mnozenjeProperty.testData();
           for (int i = 0; i < testData.size(); i++) {
               assertEquals((double) testData.get(i).getValue(), (double) calculated.get(i).getValue(), 0.01);
           }
    }
    
    @Test
    void mnozenje2Test()
    {
    	   Calculate calc = new Calculate(mnozenje2Property);
           List<Data> calculated = calc.calculate(new A8Strategy().readFile(mnozenje2Property.path().toString()));
           List<Data> testData = mnozenje2Property.testData();
           for (int i = 0; i < testData.size(); i++) {
               assertEquals((double) testData.get(i).getValue(), (double) calculated.get(i).getValue(), 0.01);
           }
    }
    
    @Test
    void suma2Test()
    {
    	Calculate calc=new Calculate(suma2Property);
    	List<Data> calculated=calc.calculate(new A10Strategy().readFile(suma2Property.path().toString()));
    	List<Data> testData=suma2Property.testData();
    	for (int i = 0; i < testData.size(); i++) {
            assertEquals((double) testData.get(i).getValue(), (double) calculated.get(i).getValue(), 0.01);
        }
    	
    }
    @Test
    void potencije2Test()
    {
    	Calculate calc=new Calculate(potencije2Property);
    	List<Data> calculated=calc.calculate(new A11Strategy().readFile(potencije2Property.path().toString()));
    	List<Data> testData=potencije2Property.testData();
    	for (int i = 0; i < testData.size(); i++) {
            assertEquals((double) testData.get(i).getValue(), (double) calculated.get(i).getValue());
        }
    }
}
