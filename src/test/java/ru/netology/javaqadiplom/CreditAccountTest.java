package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreditAccountTest {

    @ParameterizedTest
    @CsvSource({
            "0,5000,5,-5000,5000", //оплата только кредитным лимитом, ожидаемый баланс -5000
            "1000,5000,5,-4000,5000", //смешанная оплата, ожидаемый баланс -4000
            "5000,5000,5,0,5000", //оплата за счет баланса, ожидаемый баланс - 0
            "10000,5000,5,5000,5000", //оплата за счет баланса, ожидаемый баланс 5000
    })
    public void pay(int initialBalance, int creditLimit, int rate, int expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(amount);

        Assertions.assertEquals(expected, account.getBalance());
    }


    @Test
    public void negativeRateTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(5000,5000,-1);
        });
    }
    @Test
    public void zeroRateTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(5000,5000,0);
        });
    }

    @Test
    public void negativeInitialBalanceTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(-1,5000,5);
        });
    }

    @Test
    public void negativeCreditLiminTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(1000,-1,5);
        });
    }


}
