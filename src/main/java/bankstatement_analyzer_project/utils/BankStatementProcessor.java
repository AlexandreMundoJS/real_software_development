package bankstatement_analyzer_project.utils;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import bankstatement_analyzer_project.domains.BankTransaction;
import bankstatement_analyzer_project.interfaces.IBankTransactionFilter;
import bankstatement_analyzer_project.interfaces.IBankTransactionSummarizer;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(final IBankTransactionSummarizer iBankTransactionSummarizer) {
        double result = 0;
        for(final BankTransaction bankTransaction: bankTransactions) {
            result = iBankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }


    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((accumulator, bankTransaction) -> bankTransaction.getDate().getMonth() == month ? accumulator + bankTransaction.getAmount() : accumulator);
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
    
    public List<BankTransaction> findTransactions(final IBankTransactionFilter iBankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction: bankTransactions){
            if(iBankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsInMonth(final Month month){
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getDate().getMonth() == month){
                result.add(bankTransaction);
            }
        }
        return result;
    }

}
