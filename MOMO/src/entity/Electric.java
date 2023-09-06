package entity;

import enums.EBillType;
import enums.EProvider;
import enums.EState;

import java.util.Date;

public class Electric extends Bill{
    public Electric(long billId, EBillType billType, double amount, Date dueDate, EState state, EProvider provider) {
        super(billId, billType, amount, dueDate, state, provider);
    }

    @Override
    public void payBill() {

    }
}
