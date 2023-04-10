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
    public void shouldNotAddAmountEqualsNull() { //нельзя пополнить на сумму равную 0
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
    public void shouldNotPayAmountEqualsNull() { //нельзя оплатить на сумму равную 0
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
    public void shouldAddMoreThanMaxBalance() { //оплата покупки меньше чем макс баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(499);

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

}
