public class DepositThread extends Thread {

    String threadName = "";
    long depositAmount = 0;
    BankAccount bankAccount;

    public DepositThread(String threadName, BankAccount bankAccount, long depositAmount) {
        this.threadName = threadName;
        this.bankAccount = bankAccount;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        bankAccount.deposit(threadName, depositAmount);
    }
}
