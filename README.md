# MOMO

MOMO TEST

-i create some file to test with data is available(TestAddBill, TestGetBillByProvide, TestPay, TestShowList)

-Use the main class to do what you want, you need to follow the following rules
CREATE_CUSTOMER <customerId> <balanceAvailable>
ADD_BILL <customerId> <BillType-contains(ELECTRIC,WATER,INTERNET)> <amount> <dueDate> <State-contains(NOT_PAID,PROCESSED,PENDING)> <Provide-contains(EVN_HCMC,SAVACO_HCMC,VNPT)>
LIST_BILLS <customerId>
PAY <customerId> <listBillID- ex: 1-2-3-4>
SCHEDULE <customerId> <BillID> <paymentDate>
SEARCH_BILL_BY_PROVIDER <customerId> <Provide-contains(EVN_HCMC,SAVACO_HCMC,VNPT)>
EXIT exit shell

- you can use fakeData function to create date faster 

