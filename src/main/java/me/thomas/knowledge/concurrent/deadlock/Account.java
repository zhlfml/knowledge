package me.thomas.knowledge.concurrent.deadlock;

/**
 * @author zhaoxinsheng
 * @date 27/11/2017.
 */
public class Account {

    private final String id;
    private long balance;

    public Account(String id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public void debit(long amount) {
        balance -= amount;
    }

    public void credit(long amount) {
        balance += amount;
    }

    public void transferMoneyNotThreadSafe(Account another, long amount) {
        synchronized (this) {
            synchronized (another) {
                doTransfer(another, amount);
            }
        }
    }

    /**
     * 保持相同的加锁顺序，所以线程安全
     *
     * @param another 另一个账号
     * @param amount 转账金额
     */
    public void transferMoneyThreadSafe(Account another, long amount) {
        if (System.identityHashCode(this.id) < System.identityHashCode(another.id)) {
            synchronized (this) {
                synchronized (another) {
                    doTransfer(another, amount);
                }
            }
        } else {
            synchronized (another) {
                synchronized (this) {
                    doTransfer(another, amount);
                }
            }
        }
    }

    private void doTransfer(Account another, long amount) {
        System.out.println(this.id + " transfer $" + amount + " to " + another.id);
        this.debit(amount);
        another.credit(amount);
        System.out.println(this.id + " balance is $" + this.balance + ", " + another.id + " balance is $" + another.balance);
    }
}
