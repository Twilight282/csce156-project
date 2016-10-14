import java.util.ArrayList;

public class Cineclark {
	public static void main(String[] args){
		FlatConverter fc = new FlatConverter();
		ArrayList<Product> products = fc.loadProducts();
		ArrayList<Customer> customers = fc.loadCustomers();
		ArrayList<Person> persons = fc.loadPersons();
		//and over to you
		
		Writer.personWriter(persons);
		Writer.customerWriter(customers);
		Writer.productWriter(products);
	}
}
