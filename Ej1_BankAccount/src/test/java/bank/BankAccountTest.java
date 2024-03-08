package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
    @DisplayName("Withdraw of less money than current balance")
    public void Withdraw_SufficientMoney_ReturnsTrue() {
        int amount = 50;

        boolean res = account.withdraw(amount);

        assertTrue(res);
    }

    @Test
    @DisplayName("Withdraw of more money than current balance")
    public void Withdraw_InsufficientMoney_ReturnsFalse() {
        int amount = 150;

        boolean res = account.withdraw(amount);

        assertFalse(res);
    }

    @Test
    @DisplayName("Withdraw of negative number")
    public void Withdraw_NegativeAmount_ThrowsIllegalArgumentException() {
        int amount = -100;

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(amount);
        });
    }

    @Test
    @DisplayName("Deposit of positive amount returns the sum of balance and amount")
    public void Deposit_PositiveAmount_ReturnsSum() {
        int amount = 100;
        int expected_sum = account.getBalance() + amount;

        int res_sum = account.deposit(amount);

        assertEquals(expected_sum, res_sum);
    }

    @Test
    @DisplayName("Deposit of negative amount throws and IllegalArgumentException")
    public void Deposit_NegativeAmount_ThrowsIllegalArgumentException() {
        int amount = -100;

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(amount);
        });
    }

    @Test
    @DisplayName("Method get returns the corresponding balance")
    public void Get_Balance_ReturnsBalance() {
        assertEquals(startingBalance, account.getBalance());
    }

    @ParameterizedTest
    @DisplayName("Payment of negative number inputs throws IllegalArgumentException")
    @CsvSource({
            "-100.0,0.01,12",
            "100.0,-0.01,12",
            "100.0,0.01,-12"
    })
    public void Payment_NegativeInputs_ThrowsIllegalArgumentException(double total_amount, double interest, int npayments) {
        assertThrows(IllegalArgumentException.class, () -> {
            account.payment(total_amount, interest, npayments);
        });
    }

    @Test
    @DisplayName("Payment of a known case returns correct amount")
    public void Payment_RegularCase_ReturnsCorrectAmount() {
        double total_amount = 10000;
        double interest = 0.001;
        int months = 12;
        double expected_sum = 838.759926;

        double res_sum = account.payment(total_amount, interest, months);

        assertEquals(Math.round(expected_sum), Math.round(res_sum));
    }

    @ParameterizedTest
    @DisplayName("Payment of negative number inputs throws IllegalArgumentException")
    @CsvSource({
            "-100.0,0.01,12,2",
            "100.0,-0.01,12,2",
            "100.0,0.01,-12,2",
            "100.0,0.01,12,-2"
    })
    public void Pending_NegativeInputs_ThrowsIllegalArgumentException(double amount, double inte, int npayments, int month) {
        assertThrows(IllegalArgumentException.class, () -> {
            account.pending(amount, inte, npayments, month);
        });
    }

    @Test
    @DisplayName("Pending of zero months returns the input amount")
    public void Pending_ZeroMonths_ReturnsAmount() {
        int months = 0;
        double amount = 100;

        double res = account.pending(amount, 0.001, 12, months);

        assertEquals(res, amount);
    }

    @Test
    @DisplayName("Pending of known case returns the correct amount")
    public void Pending_RegularCase_ReturnsCorrectAmount() {
        double total_amount = 10000;
        double interest = 0.001;
        int months = 12;
        double expected_res = 8341.651389;

        double res = account.pending(total_amount, interest, months, 2);

        assertEquals(Math.round(expected_res), Math.round(res));
    }


}
