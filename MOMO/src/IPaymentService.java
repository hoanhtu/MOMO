import entity.IBill;
import enums.EBillType;
import enums.EProvider;
import enums.EState;

import java.util.Date;

public interface IPaymentService {
    public void addBill(String customerID, EBillType typeBill, double amount, Date dueDate, EState state, EProvider provider);
}
