import entity.Bill;
import entity.IBill;
import enums.EProvider;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Customer {
    private String customerId;
    private double availableBalance;

    private Map<Long, IBill> bills;

    private Map<EProvider, Set<Long>> billByProvider;

    private AtomicLong idBill;

    public Customer(String customerId, double availableBalance) {
        this.customerId = customerId;
        this.availableBalance = availableBalance;
        bills= new HashMap<>();
        billByProvider= new HashMap<>();
        idBill= new AtomicLong(0);
    }

    public void addBill(IBill bill)
    {
        if(bills.containsKey(bill.getBillId())) {
            System.out.println("create bill fail");
            return;
        }
        bills.put(bill.getBillId(), bill);
        billByProvider.putIfAbsent(bill.getProvider(), new HashSet<>());
        billByProvider.get(bill.getProvider()).add(bill.getBillId());
        this.idBill.incrementAndGet();
        System.out.println("create bill success");

    }

    public Map<EProvider, Set<Long>> getBillByProvider() {
        return billByProvider;
    }

    public void setBillByProvider(Map<EProvider, Set<Long>> billByProvider) {
        this.billByProvider = billByProvider;
    }

    public AtomicLong getIdBill() {
        return idBill;
    }

    public void setIdBill(AtomicLong idBill) {
        this.idBill = idBill;
    }

    public Map<Long, IBill> getBills() {
        return bills;
    }

    public Bill getBillById(long id)
    {
        if(!bills.containsKey(id))
            return null;
        return (Bill) bills.get(id);
    }

    public void setBills(Map<Long, IBill> bills) {
        this.bills = bills;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void cashIn(double amount)
    {
        this.availableBalance+=amount;
    }
}
