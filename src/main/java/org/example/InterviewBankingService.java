package org.example;

public interface InterviewBankingService {
    void openAccount(String accountId, long amount);
    long getBalance(String accountId);
}
