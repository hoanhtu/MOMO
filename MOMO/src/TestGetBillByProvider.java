import enums.EBillType;
import enums.EProvider;
import enums.EState;

import java.util.Arrays;
import java.util.Date;

public class TestGetBillByProvider {
    static CustomerController  cu= CustomerController.getInstance();
    static PaymentService pa= PaymentService.getInstance();
    public static void main(String[] args) {
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

        pa.showListBill(customer1.getCustomerId());
        pa.searchBillByProvider(customer1.getCustomerId(),EProvider.EVN_HCMC);
        System.out.println();

    }
}
