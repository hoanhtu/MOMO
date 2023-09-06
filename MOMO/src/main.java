import entity.Bill;
import enums.EBillType;
import enums.EProvider;
import enums.EState;

import java.util.*;

public class main {
    static CustomerController  cu= CustomerController.getInstance();
    static PaymentService pa= PaymentService.getInstance();
    public static void main(String[] args) {

        fakeData();

        System.out.println("Welcome to the Bill Payment Shell!");


        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            String[] commandParts = input.split(" ");
            String command = commandParts[0].toUpperCase();

            switch (command) {
                case "CREATE_CUSTOMER":
                    String customerId=commandParts[1];
                    double balance =  Double.parseDouble(commandParts[2]);
                    cu.getInstance().addCustomer(new Customer(customerId,balance));
                    break;
                case "ADD_BILL":
                    String id = commandParts[1];
                    EBillType billType = EBillType.valueOf(commandParts[2]);
                    double amount = Double.parseDouble(commandParts[3]);
                    Date dueDate = new Date(Long.parseLong(commandParts[4]));
                    EState state = EState.valueOf(commandParts[5]);
                    EProvider provider = EProvider.valueOf(commandParts[6]);
                    pa.getInstance().addBill(id, billType,amount, dueDate, state, provider);
                    break;
                case "LIST_BILLS":
                    pa.getInstance().showListBill(commandParts[1]);
                    break;
                case "PAY":
                    String ids = commandParts[2];
                    String[]id_split = ids.split("-");
                    List<Long> listID = new ArrayList<>();
                    for(int i =0;i<id_split.length;i++)
                    {
                        listID.add(Long.parseLong(id_split[i]));
                    }
                    pa.getInstance().pay(commandParts[1], listID);
                    break;
                case "SCHEDULE":
                    pa.getInstance().scheduleBillPayment(commandParts[1], Long.parseLong(commandParts[2]),new Date(Long.parseLong(commandParts[3])));
                    break;
                case "SEARCH_BILL_BY_PROVIDER":
                    pa.getInstance().searchBillByProvider(commandParts[1], EProvider.valueOf(commandParts[2]));
                    break;
                case "EXIT":
                    running = false;
                    System.out.println("Exiting the Bill Payment Shell. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }

    public static void fakeData()
    {
        Customer customer1= cu.createCustomer("user1", 10000);
        Customer customer2= cu.createCustomer("user2", 10000);
        Customer customer3= cu.createCustomer("user3", 10000);
        Customer customer4= cu.createCustomer("user4", 10000);
        cu.addCustomer(customer1);
        cu.addCustomer(customer2);
        cu.addCustomer(customer3);
        cu.addCustomer(customer4);
        pa.addBill(customer1.getCustomerId(), EBillType.ELECTRIC,100,new Date(118,12,19 ), EState.NOT_PAID, EProvider.VNPT);
        pa.addBill(customer1.getCustomerId(), EBillType.INTERNET,120,new Date(), EState.NOT_PAID, EProvider.EVN_HCMC);
        pa.addBill(customer1.getCustomerId(), EBillType.WATER,140,new Date(117,12,19), EState.NOT_PAID, EProvider.SAVACO_HCMC);
        pa.addBill(customer1.getCustomerId(), EBillType.INTERNET,150,new Date(), EState.NOT_PAID, EProvider.EVN_HCMC);
        pa.pay(customer1.getCustomerId(), Arrays.asList(0l,1l,2l));
        pa.scheduleBillPayment(customer1.getCustomerId(), 3l, new Date(203, 11,2));
    }
}
