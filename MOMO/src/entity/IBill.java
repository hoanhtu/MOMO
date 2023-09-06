package entity;

import enums.EProvider;

public interface IBill {
    public void payBill();
    public long getBillId();
    public void show();
    public EProvider getProvider();
}
