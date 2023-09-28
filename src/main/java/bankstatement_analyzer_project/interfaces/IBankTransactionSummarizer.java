package bankstatement_analyzer_project.interfaces;

import bankstatement_analyzer_project.domains.BankTransaction;

@FunctionalInterface
public interface IBankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);    
}
