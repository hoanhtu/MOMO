import entity.Bill;
import entity.IBill;
import enums.EBillType;
import enums.EProvider;
import enums.EState;

import java.util.*;
import java.util.logging.Logger;

public class PaymentService implements  IPaymentService{

    public static PaymentService instance =null;

    public static PaymentService getInstance()
    {
        if(instance==null)
        {
            synchronized (PaymentService.class)
            {
                if(instance==null)
                    instance= new PaymentService();
            }
        }
        return instance;
    }

    public PaymentService() {
    }

    @Override
    public void addBill(String customerID, EBillType typeBill, double amount, Date dueDate, EState state, EProvider provider) {
        Customer customer= CustomerController.getInstance().getCustomerById(customerID);
        if(customer==null) {
            System.out.println("customer null "+customerID);
            return;
        }
        Bill bill = createBill(customer.getIdBill().get(), typeBill, amount, dueDate, state, provider);
        customer.addBill(bill);
    }

    public Bill createBill(long id , EBillType typeBill, double amount, Date dueDate, EState state, EProvider provider)
    {
        return  new Bill(id, typeBill, amount, dueDate, state, provider);
    }

    public boolean pay(String customerId, List<Long>listId)
    {
        Customer customer =CustomerController.getInstance().getCustomerById(customerId);
        if(customer==null)
        {
            System.out.println("Action Pay Error customer is null "+ customerId);
            return false;
        }
        double totalCash=0;
        PriorityQueue<Bill>q = new PriorityQueue<>((a,b)->a.getDueDate().compareTo(b.getDueDate()));
        for(int i =0;i<listId.size();i++) {
            Bill bill = customer.getBillById(listId.get(i));
            if (bill == null) {
                System.out.println("Sorry! Not found a bill with such id " + listId.get(i));
                continue;
            }
            if(bill.getState()==EState.PROCESSED)
            {
                System.out.println("bill processed " + listId.get(i));
                continue;
            }
            q.add(bill);
        }
        while (!q.isEmpty()){
            Bill bill = q.poll();
            totalCash=bill.getAmount();
            double balance = customer.getAvailableBalance();
            if(balance<totalCash)
            {
                System.out.println("Sorry! Not enough fund to proceed with payment id bill is "+bill.getBillId());
                return false;
            }
            updateState(customer, bill.getBillId());
            customer.cashIn(-totalCash);
        }

        System.out.println("Your current balance is: "+customer.getAvailableBalance());

        return true;


    }

    public void updateState(Customer customer,long id )
    {
            Bill bill = customer.getBillById(id);
            if(bill.getState()==EState.PENDING)
            {
                System.out.println("bill is pending and completed immediately " +id);
                bill.getTask().cancel();
            }
            bill.setState(EState.PROCESSED);
            System.out.println("Payment has been completed for Bill with id " + id);

    }


    public boolean scheduleBillPayment(String customerId,long id,  Date paymentDate)
    {
        Customer customer =CustomerController.getInstance().getCustomerById(customerId);
        if(customer==null)
        {
            System.out.println("Action Pay Error customer is null "+ customerId);
            return false;
        }
        Bill bill = customer.getBillById(id);
        if(bill==null)
        {
            System.out.println("Sorry! Not found a bill with such id "+ id);
            return false;
        }
        bill.setState(EState.PENDING);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                pay(customerId, Arrays.asList(id));
            }
        };
        timer.schedule(task, paymentDate);

        return true;
    }

    public void showListBill(String customerId) {
        Customer customer = CustomerController.getInstance().getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Action Pay Error customer is null " + customerId);
            return;
        }
        for (long id : customer.getBills().keySet())
        {
            customer.getBills().get(id).show();
        }
    }



    public void searchBillByProvider(String customerId, EProvider provider)
    {
        Customer customer = CustomerController.getInstance().getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Action Pay Error customer is null " + customerId);
            return;
        }
        Set<Long> ids =customer.getBillByProvider().get(provider);
        System.out.println("Bills by Provider are :");
        for(long i: ids)
        {
            customer.getBillById(i).show();
        }
    }


}
