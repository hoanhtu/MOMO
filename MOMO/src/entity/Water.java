package entity;

import enums.EBillType;
import enums.EProvider;
import enums.EState;

import java.util.Date;

public class Water extends Bill{
    public Water(long billId, EBillType billType, double amount, Date dueDate, EState state, EProvider provider) {
        super(billId, billType, amount, dueDate, state, provider);
    }

    @Override
    public void payBill() {

    }
}
