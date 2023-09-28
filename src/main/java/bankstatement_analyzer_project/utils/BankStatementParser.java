package bankstatement_analyzer_project.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bankstatement_analyzer_project.domains.BankTransaction;
import bankstatement_analyzer_project.interfaces.IBankStatementParser;

public class BankStatementParser implements IBankStatementParser{
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransaction parseFrom(String line) throws CSVSyntaxException {
        final String[] columns = line.split(",");
        if (columns.length < 3) {
            throw new CSVSyntaxException();
        }
        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);

        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) throws CSVSyntaxException {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (final String line : lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }
}
