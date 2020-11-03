package int101.banking;

import java.math.BigDecimal;

public class AccountTransaction {
    private final TransactionType type;
    private final BigDecimal amount;

    public AccountTransaction(TransactionType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountTransaction[" + type + ":" + amount + ']';
    }
}
