import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;


public class Writer {
	public static void employeeWriter(ArrayList<Employee> employee) {
		XStream xstream = new XStream();
	
		File xmlOut = new File("data/People.xml");
	
		PrintWriter xmlPrinter = null;
		try {
			xmlPrinter = new PrintWriter(xmlOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrinter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("person", Employee.class);
		for (Employee a : employee) {
			String personOut = xstream.toXML(a);
			xmlPrinter.write(personOut);
		}
	}
	
	public static void productWriter(ArrayList<Product> product) {
		XStream xstream = new XStream();
	
		File xmlOut = new File("data/Products.xml");
	
		PrintWriter xmlPrinter = null;
		try {
			xmlPrinter = new PrintWriter(xmlOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrinter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("product", Product.class);
		for (Product a : product) {
			String productOut = xstream.toXML(a);
			xmlPrinter.write(productOut);
		}
	}
	
	public static void customerWriter(ArrayList<Customer> customer) {
		XStream xstream = new XStream();
	
		File xmlOut = new File("data/Customer.xml");
	
		PrintWriter xmlPrinter = null;
		try {
			xmlPrinter = new PrintWriter(xmlOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrinter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("customer", Customer.class);
		for (Customer a : customer) {
			String customerOut = xstream.toXML(a);
			xmlPrinter.write(customerOut);
		}
	}
}
