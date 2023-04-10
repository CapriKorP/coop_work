package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );

        account.add(4_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

}
