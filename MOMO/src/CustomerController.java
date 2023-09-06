import java.util.HashMap;
import java.util.Map;

public class CustomerController {
    public static CustomerController instance =null;
    Map<String, Customer>map;
    public static CustomerController getInstance()
    {
        if(instance== null)
        {
            synchronized (CustomerController.class)
            {
                if(instance== null)
                    instance= new CustomerController();
            }
        }
        return instance;
    }



    public CustomerController() {
        this.map = new HashMap<>();
    }

    public Map<String, Customer> getMap() {
        return map;
    }

    public void setMap(Map<String, Customer> map) {
        this.map = map;
    }

    public Customer getCustomerById(String id)
    {
        return map.get(id);
    }
    public void addCustomer(Customer customer)
    {
        if(map.containsKey(customer.getCustomerId())) {
            System.out.println("Add Fail ");
            return;
        }
        map.put(customer.getCustomerId(),customer);
        System.out.println("Add success customer id "+ customer.getCustomerId()+" with balance "+ customer.getAvailableBalance());
    }

    public Customer createCustomer(String id, double availableBalance)
    {
        return new Customer(id, availableBalance);
    }
}
