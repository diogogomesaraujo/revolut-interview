package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;

    @BeforeEach
    void initBank() {
        bank = new Bank();
    }

    @Test
    void transferMoney() {
        String a = "A";
        String b = "B";

        bank.open(a, 100);
        bank.open(b, 50);

        bank.transfer(a, b, 50);

        assertEquals(50, bank.balance(a));
        assertEquals(100, bank.balance(b));
    }

    @Test
    void openAccountNull() {
        String a = null;

        assertThrows(NullPointerException.class, () -> bank.open(a, 10));
    }

    @Test
    void transferAccountNull() {
        String a = "A";
        String b = "B";

        bank.open(a, 100);
        bank.open(b, 50);

        assertThrows(NullPointerException.class, () -> bank.transfer(a, null, 50));
        assertThrows(NullPointerException.class, () -> bank.transfer(null, b, 50));
        assertThrows(NullPointerException.class, () -> bank.transfer(null, null, 50));
    }

    @Test
    void invalidAccountBalance() {
        String a = "A";

        assertThrows(NullPointerException.class, () -> bank.balance(null));
        assertThrows(NullPointerException.class, () -> bank.balance(a));
    }

    @Test
    void rejectOverDraft() {
        String a = "A";
        String b = "B";

        bank.open(a, 100);
        bank.open(b, 50);

        assertThrows(IllegalArgumentException.class, () -> bank.transfer(b, a, 100));
    }

    @Test
    void transferInvalidAmount() {
        String a = "A";
        String b = "B";

        bank.open(a, 100);
        bank.open(b, 50);

        assertThrows(IllegalArgumentException.class, () -> bank.transfer(b, a, 0));
        assertThrows(IllegalArgumentException.class, () -> bank.transfer(b, a, -1));
    }

    @Test
    void rejectSameAccountTransfer() {
        String a = "A";

        bank.open(a, 100);

        assertThrows(IllegalArgumentException.class, () -> bank.transfer(a, a, 10));
    }

    @Test
    void rejectUnknownAccountTransfer() {
        String a = "A";
        String b = "B";

        bank.open(a, 100);

        assertThrows(IllegalArgumentException.class, () -> bank.transfer(a, b, 10));
        assertThrows(IllegalArgumentException.class, () -> bank.transfer(b, a, 10));
    }
}