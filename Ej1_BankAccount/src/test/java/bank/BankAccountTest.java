package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void setup(){
        account = new BankAccount(100);
    }

    @Test
    public void Withdraw_SufficientMoney_ReturnsTrue(){
        int amount = 50;

        boolean res = account.withdraw(amount);

        assertTrue(res);
    }

    @Test
    public void Withdraw_InsufficientMoney_ReturnsFalse(){
        int amount = 150;

        boolean res = account.withdraw(amount);

        assertFalse(res);
    }

    @Test
    public void Deposit_PositiveAmount_ReturnsSum(){
        int amount = 100;

        int expected_sum = account.getBalance() + amount;
        int res_sum = account.deposit(amount);

        assertEquals(expected_sum,res_sum);
    }

    @Test
    public void Deposit_NegativeAmount_ThrowsIllegalArgumentException(){
        int amount = -100;

        assertThrows(IllegalArgumentException.class,() -> {
            account.deposit(amount);
        });
    }
}
