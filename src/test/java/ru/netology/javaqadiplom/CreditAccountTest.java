package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Locale;

public class CreditAccountTest {

    @ParameterizedTest
    @CsvSource({
            "1,5000,5,-4999,5000", //оплата только кредитным лимитом, ожидаемый баланс -5000
            "1000,5000,5,-4000,5000", //смешанная оплата, ожидаемый баланс -4000
            "5000,5000,5,0,5000", //оплата за счет баланса, ожидаемый баланс - 0
            "10000,5000,5,5000,5000", //оплата за счет баланса, ожидаемый баланс 500
    })
    public void payTest(int initialBalance, int creditLimit, int rate, int expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(amount);

        Assertions.assertEquals(expected, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "10000,5000,5,false,-5000" //amount <= 0
    })
    public void payZeroAmountTest(int initialBalance, int creditLimit, int rate, boolean expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(amount);

        Assertions.assertFalse(expected);
    }


    @Test
    public void negativeRateTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(5000, 5000, -1);
        });
    }

    @Test
    public void zeroRateTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(5000, 5000, 0);
        });
    }

    @Test
    public void negativeInitialBalanceTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(-1, 5000, 5);
        });
    }

    @Test
    public void negativeCreditLiminTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(1000, -1, 5);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "10000,5000,5,false,-1",
            "10000,5000,5,false,0",
    })
    public void addZeroAndNegativeAmountTest(int initialBalance, int creditLimit, int rate, boolean expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.add(amount);

        Assertions.assertFalse(expected);
    }
    @ParameterizedTest
    @CsvSource({
            "10000,5000,5,true,1"
    })
    public void addPositiveAmountTest(int initialBalance, int creditLimit, int rate, boolean expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.add(amount);

        Assertions.assertTrue(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "-200,5000,15,-30",
            "200,5000,15,0"
    })
    public void yearChangeTest(int initialBalance, int creditLimit, int rate, int expected) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.yearChange();

        Assertions.assertEquals(expected, account.yearChange());
    }
}
