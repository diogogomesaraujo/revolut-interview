package org.example;

import java.util.HashMap;
import java.util.Map;

public class InterviewBank implements InterviewBankingService {
    private Map<String, Long> balances;

    public InterviewBank() {
        balances = new HashMap<String, Long>();
    }

    public synchronized void openAccount(String accountId, long amount) {
        if (accountId == null)
            throw new IllegalArgumentException("the account id should not be null.");

        if (accountId.isBlank())
            throw new IllegalArgumentException("the account id should not be blank.");

        if (balances.get(accountId) != null)
            throw new IllegalArgumentException("there is already an account with this id");

        if (amount < 0)
            throw new IllegalArgumentException("the amount should not be negative.");

        balances.put(accountId, amount);
    }

    public synchronized long getBalance(String accountId) {
        if (accountId == null)
            throw new IllegalArgumentException("the account id should not be null.");

        if (accountId.isBlank())
            throw new IllegalArgumentException("the account id should not be blank.");

        Long balance = balances.get(accountId);

        if (balance == null)
            throw new RuntimeException("the account does not exist.");

        return balance;
    }
}
