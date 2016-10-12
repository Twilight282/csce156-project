import java.util.ArrayList;

public class DataConverter {
	public static void main(String[] args){
		FlatConverter fc = new FlatConverter();        //creates converter
		ArrayList<Product> products = fc.loadProducts();   //creates Product list
		ArrayList<Customer> customers = fc.loadCustomers(); //creates Customer list
		ArrayList<Employee> employees = fc.loadEmployees(); //creates Employee list
		//and over to you
		
		Writer.employeeWriter(employees);  //writes xml file
		Writer.customerWriter(customers);  //writes xml file
		Writer.productWriter(products);    //writes xml
	}
}
