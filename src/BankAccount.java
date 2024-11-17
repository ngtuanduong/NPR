public class BankAccount {

    long amount = 20000000;

    public boolean checkAccountBalance(long withDrawAmount) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (withDrawAmount <= amount) {
            return true;
        }


        return false;
    }

    public void withdraw(String threadName, long withdrawAmount) {
        System.out.println(threadName + " sees balance: " + amount );
        System.out.println(threadName + " wants to withdraw: " + withdrawAmount);
        synchronized (this){
            if (checkAccountBalance(withdrawAmount)) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                amount -= withdrawAmount;
                System.out.println(threadName + " withdrew successfully: " + withdrawAmount);
                System.out.println(threadName + " sees balance: " + amount);
            }
            else{
                System.out.println(threadName + " sees balance: " + amount);
            }
        }
    }
    public synchronized void withdrawWhenBalanceEnough(String threadName, long withdrawAmount) {
        System.out.println(threadName + " wants to withdraw: " + withdrawAmount);

        while (!checkAccountBalance(withdrawAmount)) {
            System.out.println(threadName + " waits for balance enough");
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        amount -= withdrawAmount;
        System.out.println(threadName + " withdrew successfully: " + withdrawAmount);
    }
    public synchronized void deposit(String threadName, long depositAmount) {
        System.out.println(threadName + " deposits: " + depositAmount);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        amount += depositAmount;

        notify();
    }
}