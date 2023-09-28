package bankstatement_analyzer_project.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import bankstatement_analyzer_project.domains.BankTransaction;
import bankstatement_analyzer_project.utils.BankStatementParser;
import bankstatement_analyzer_project.utils.BankStatementProcessor;
import bankstatement_analyzer_project.utils.CSVSyntaxException;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final String filename, final BankStatementParser bankStatementParser)
            throws IOException, CSVSyntaxException {
        final Path path = Paths.get(RESOURCES + filename);
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        // final List<BankTransaction> transactions =
        // bankStatementProcessor.findTransactions(new
        // BankTransactionIsInFebruaryAndExpensive());
        final List<BankTransaction> transactions = bankStatementProcessor
                .findTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == Month.FEBRUARY
                        && bankTransaction.getAmount() >= 1_000);

        collectSumary(bankStatementProcessor);

    }

    public static void collectSumary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transactions in January is "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for all transactions in February is "
                + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month)
            throws CSVSyntaxException {
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }

}
