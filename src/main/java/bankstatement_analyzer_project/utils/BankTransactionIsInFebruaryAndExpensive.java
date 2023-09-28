package bankstatement_analyzer_project.utils;

import java.time.Month;

import bankstatement_analyzer_project.domains.BankTransaction;
import bankstatement_analyzer_project.interfaces.IBankTransactionFilter;

public class BankTransactionIsInFebruaryAndExpensive implements IBankTransactionFilter {

    @Override
    public boolean test(final BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY && (bankTransaction.getAmount() >= 1000);
    }

}
