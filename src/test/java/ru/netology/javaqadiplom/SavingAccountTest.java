package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { //кладем денег меньше, чем максимальный баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddMoreThanMaxBalance() { //кладем денег больше, чем максимальный баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(20_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddMoreThanMinBalance() { //!!кладем денег меньше, чем минимальный баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(500);

        Assertions.assertEquals(2_500, account.getBalance());
    }

    @Test
    public void testAddLessThanMaxBalance() { //проверка на true/false пополнение на сумму меньше, чем макс баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.add(3_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddMoreThanMaxBalance() { //проверка на true/false пополнение на сумму больше, чем макс баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(20_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAddMoreThanMinBalance() { //проверка на true/false пополнение на сумму больше, чем мин баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.add(500);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNotAddAmountEqualsNull() { //нельзя пополнить на сумму равную 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddAmountEqualsNull() { //пополнение на сумму 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(0);
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void testNotAddAmountUnderNull() { //нельзя пополнить на сумму меньше нуля
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(-1_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPayLessThanMinBalance() { //оплата покупки меньше, чем мин баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(500);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPayMoreThanMinBalance() { //оплата покупки больше, чем мин баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(1_500);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPayMoreThanMaxBalance() { //оплата покупки больше, чем макс баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(20_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPayLessThanMinBalance() { //оплата на сумму меньше, чем мин баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(500);
        Assertions.assertEquals(2_000 - 500, account.getBalance());
    }

    @Test
    public void shouldPayMoreThanMinBalance() { //оплата на сумму больше, чем мин баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(1_500);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayMoreThanInitialBalance() { //оплата на сумму больше, чем текущий баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(3_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayMoreThanMaxBalance() { //оплата на сумму больше, чем мин баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(20_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayEqualsNull() { //оплата на сумму 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(0);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayAndStayMinBalance() { //оплата на сумму и баланс стал равен минимальному
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(1_000);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void testPayAndStayMinBalance() { //оплата на сумму и баланс стал равен минимальному true/false
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(1_000);
    }

    @Test
    public void testNotPayAmountEqualsNull() { //нельзя оплатить на сумму равную 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNotPayAmountUnderNull() { //нельзя оплатить на сумму меньше нуля
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(-1_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldYearChangeEqualsNull() { //проценты на остаток равны 0
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                0
        );
        int expected = 0;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldYearChangeNormal() { //проценты на остаток считаются согласно условиям
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        int expected = 100;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void initialBalanceShouldNotBeNegative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    -2000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void initialRateShouldNotBeNegative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2000,
                    1_000,
                    10_000,
                    -5
            );
        });
    }

    @Test
    public void minBalanceShouldNotBeNegative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2000,
                    -1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void maxBalanceShouldNotBeNegative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2000,
                    1_000,
                    -10_000,
                    5
            );
        });
    }

    @Test
    public void minBalanceMoreMaxBalanceTest() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2000,
                    10_000,
                    1_000,
                    5);
        });
    }

    @Test
    public void minBalanceShouldBeZero() { //минимальный баланс может быть равен 0
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );
        Assertions.assertEquals(0, account.getMinBalance());
    }

    @Test
    public void initialBalanceEqualMaxBalance() { //Текущий баланс равен максимальному
        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(10_000, account.getMaxBalance());
    }

    @Test
    public void negativeRateTestMinBalance() { //Текущий баланс не может быть меньше минимального баланса
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void negativeRateTestMaxBalance() { //Текущий баланс не может быть больше макс баланса
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    20_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

}