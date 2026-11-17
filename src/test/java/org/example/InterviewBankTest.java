package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterviewBankTest {
    InterviewBankingService bank;

    @BeforeEach
    void initBank() {
        bank = new InterviewBank();
    }

    @Test
    void openAccountAndGetBalance() {
        long amount = 50;
        String id = "A";

        bank.openAccount(id, amount);

        // assertEquals(50, bank.getBalance(id));
    }

    @Test
    void openBlankIdAccount() {
        assertThrows(IllegalArgumentException.class, () -> bank.openAccount("", 50));
    }

    @Test
    void openNullIdAccount() {
        assertThrows(IllegalArgumentException.class, () -> bank.openAccount(null, 50));
    }

    @Test
    void doubleRequestOpenAccount() {
        long amount = 50;
        String id = "A";

        bank.openAccount(id, amount);

        assertThrows(IllegalArgumentException.class, () -> bank.openAccount(id, amount));
    }

    @Test
    void negativeAmountOpenAccount() {
        assertThrows(IllegalArgumentException.class, () -> bank.openAccount("a", -1));
    }

    @Test
    void getBalanceBlankAccount() {
        assertThrows(IllegalArgumentException.class, () -> bank.getBalance(""));
    }

    @Test
    void getBalanceNullAccount() {
        assertThrows(IllegalArgumentException.class, () -> bank.getBalance(null));
    }

    @Test
    void getBalanceNonExistingAccount() {
        assertThrows(RuntimeException.class, () -> bank.getBalance("A"));
    }
}