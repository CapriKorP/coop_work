package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CreditAccountTest {

    @ParameterizedTest
    @CsvSource({
            "0,5000,5,-5000,5000",
            "1000,5000,5,-4000,5000",
            "5000,5000,5,0,5000",
            "10000,5000,5,5000,5000",
            "0,3000,5,error,5000", //не вписывается в кредитный лимит
            "2000,3000,5,4000,-1000" //покупка суммой бплпнса и кредита
    })
    public void pay(int initialBalance, int creditLimit, int rate, int expected, int amount) {
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(amount);

        Assertions.assertEquals(expected, account.getBalance());
    }
}