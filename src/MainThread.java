public class MainThread {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        WithdrawThread wifeThread = new WithdrawThread("Wife", bankAccount, 10000000);
        wifeThread.start();
        DepositThread husbandThread = new DepositThread("Husband", bankAccount, 5000000);
        husbandThread.start();

        System.out.println("Main Thread Ends.");
    }
}