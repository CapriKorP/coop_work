package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Locale;

public class CreditAccountTest {

    @Test
    public void negativeRateTest() {  //тест исключения - отрицательная ставки
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(5000, 5000, -1);
        });
    }

    @Test
    public void negativeInitialBalanceTest() { //тест исключения - начальный баланс
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(-1, 5000, 5);
        });
    }

    @Test
    public void negativeCreditLimitTest() { //тест исключения - кредитный лимит
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(1000, -1, 5);
        });
    }

    @ParameterizedTest //тест оплаты - калькуляция
    @CsvSource({
            "10000,5000,5,0,10000",
            "10000,5000,5,-5000,15000",
            "10000,5000,5,-4999,14999",
            "10000,5000,5,10000,15001",
            "10000,5000,5,10000,-5000",
            "10000,5000,5,10000,-1",
            "10000,5000,5,10000,0",
            "10000,5000,5,9999,1",
            "0,5000,5,-4999,4999",
            "0,5000,5,-5000,5000",
            "0,5000,5,0,5001",
            "5000,0,5,1,4999",
            "5000,0,5,0,5000",
            "5000,0,5,5000,5001"
    })
    public void payCalculateTest(int initialBalance, int creditLimit, int rate, int expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(amount);
        Assertions.assertEquals(expected, account.getBalance());
    }

    @ParameterizedTest //тест оплаты - булиновые значения
    @CsvSource({
            "10000,5000,5,true,10000",
            "10000,5000,5,true,15000",
            "10000,5000,5,true,14999",
            "10000,5000,5,false,15001",
            "10000,5000,5,false,-5000",
            "10000,5000,5,false,-1",
            "10000,5000,5,false,0",
            "10000,5000,5,true,1",
            "0,5000,5,true,4999",
            "0,5000,5,true,5000",
            "0,5000,5,false,5001",
            "5000,0,5,true,4999",
            "5000,0,5,true,5000",
            "5000,0,5,false,5001",
    })
    public void payBooleanTest(int initialBalance, int creditLimit, int rate, boolean expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        Assertions.assertEquals(expected, account.pay(amount));
    }

    @ParameterizedTest //тест пополнения - булиновые значения
    @CsvSource({
            "10000,5000,5,false,-1",
            "10000,5000,5,false,0",
            "10000,5000,5,true,1"
    })
    public void addBooleanTest(int initialBalance, int creditLimit, int rate, boolean expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.add(amount);
        Assertions.assertEquals(expected, account.add(amount));
    }

    @ParameterizedTest //тест пополнения - калькуляция
    @CsvSource({
            "10000,5000,5,10000,-1",
            "10000,5000,5,10000,0",
            "10000,5000,5,10001,1",
            "0,5000,5,1000,1000",
            "1000,5000,5,11001,10001",
            "5000,5000,5,7500,2500",
    })
    public void addCalculateTest(int initialBalance, int creditLimit, int rate, int expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.add(amount);
        Assertions.assertEquals(expected, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "200,5000,15,-30,400",
            "200,5000,15,0,100",
            "200,5000,15,0,200",
            "200,5000,15,0,206",
            "200,5000,15,-1,207",
    })
    public void yearChangeTest(int initialBalance, int creditLimit, int rate, int expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(amount);
        Assertions.assertEquals(expected, account.yearChange());
    }
}

