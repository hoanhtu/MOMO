package entity;

import enums.EBillType;
import enums.EProvider;
import enums.EState;

import java.util.Date;
import java.util.TimerTask;

public class Bill implements IBill{
    private long billId;
    private EBillType billType;
    private double amount;
    private Date dueDate;
    private EState state;
    private EProvider provider;

    private TimerTask task;

    public Bill(long billId, EBillType billType, double amount, Date dueDate, EState state, EProvider provider) {
        this.billId = billId;
        this.billType = billType;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = state;
        this.provider = provider;
    }

    public TimerTask getTask() {
        return task;
    }

    public void setTask(TimerTask task) {
        this.task = task;
    }

    @Override
    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public EBillType getBillType() {
        return billType;
    }

    public void setBillType(EBillType billType) {
        this.billType = billType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public EState getState() {
        return state;
    }

    public void setState(EState state) {
        this.state = state;
    }

    public EProvider getProvider() {
        return provider;
    }

    public void setProvider(EProvider provider) {
        this.provider = provider;
    }

    @Override
    public void payBill() {

    }

    public void show()
    {
        System.out.println("Id: "+ getBillId()+" Type: "+ getBillType()+" Amount: "+getAmount()+" Due date: "+getDueDate()+" State: "+getState()+" Provider: "+getProvider());
    }
}
