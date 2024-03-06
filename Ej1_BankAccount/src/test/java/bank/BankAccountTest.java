package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;
    private int startingBalance;

    @BeforeEach
    public void setup() {
        startingBalance = 100;
        account = new BankAccount(startingBalance);
    }

    @Test
    public void Withdraw_SufficientMoney_ReturnsTrue() {
        int amount = 50;

        boolean res = account.withdraw(amount);

        assertTrue(res);
    }

    @Test
    public void Withdraw_InsufficientMoney_ReturnsFalse() {
        int amount = 150;

        boolean res = account.withdraw(amount);

        assertFalse(res);
    }

    @Test
    public void Deposit_PositiveAmount_ReturnsSum() {
        int amount = 100;
        int expected_sum = account.getBalance() + amount;

        int res_sum = account.deposit(amount);

        assertEquals(expected_sum, res_sum);
    }

    @Test
    public void Deposit_NegativeAmount_ThrowsIllegalArgumentException() {
        int amount = -100;

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(amount);
        });
    }

    @Test
    public void Get_Balance_ReturnsBalance(){
        assertEquals(startingBalance,account.getBalance());
    }

    @Test
    public void Pending_ZeroMonths_ReturnsAmount(){
        int months = 0;
        double amount = 100;

        double res = account.pending(amount,0.001,12,months);

        assertEquals(res,amount);
    }

    @Test
    public void Pending_RegularCase_ReturnsCorrectAmount(){
        double total_amount =10000;
        double interest = 0.001;
        int months = 12;
        double expected_res = 8341.651389;

        double res = account.pending(total_amount,interest,months,2);

        assertEquals(Math.round(expected_res),Math.round(res));
    }


}
