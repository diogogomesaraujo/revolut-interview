package org.example;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Long> balances;

    public Bank() {
        balances = new HashMap<String, Long>();
    }

    public synchronized void open(String account, long amount) {
        if (account == null)
            throw new IllegalArgumentException("the account can't be null.");

        if (account.isBlank())
            throw new IllegalArgumentException("the account can't be blank.");

        if (balances.get(account) != null)
            throw new IllegalArgumentException("the account is already open.");

        if (amount < 0)
            throw new IllegalArgumentException("the amount can't be negative.");

        balances.put(account, amount);
    }

    public synchronized void transfer(String sender, String receiver, long amount) {
        if (sender.equals(receiver))
            throw new IllegalArgumentException("the sender and receiver can't be the same");

        if (sender == null || receiver == null)
            throw new IllegalArgumentException("the accounts can't be null.");

        if (sender.isBlank() || receiver.isBlank())
            throw new IllegalArgumentException("the accounts can't be blank.");

        Long senderBalance = balances.get(sender);
        Long receiverBalance = balances.get(receiver);

        if (senderBalance == null || receiverBalance == null)
            throw new IllegalArgumentException("the accounts most exist");

        if (senderBalance < amount)
            throw new IllegalArgumentException("the sender doesn't have sufficient funds.");

        if (amount <= 0)
            throw new IllegalArgumentException("the amount can't be negative or zero.");

        senderBalance -= amount;
        receiverBalance += amount;

        balances.put(sender, senderBalance);
        balances.put(receiver, receiverBalance);
    }

    public synchronized long balance(String account) {
        Long balance = balances.get(account);

        if (balance == null)
            throw new IllegalArgumentException("account does not exist.");

        return balance;
    }
}
