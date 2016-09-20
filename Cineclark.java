public class Cineclark {
	public static void main(String[] args){
		FlatConverter fc = new FlatConverter();
		ArrayList<Product> products = fc.loadProducts();
		ArrayList<Customer> customers = fc.loadCustomers();
		ArrayList<Employee> employees = fc.loadEmployees();
		//and over to you
	}
}
