package bankstatement_analyzer_project.interfaces;

import java.util.List;

import bankstatement_analyzer_project.domains.BankTransaction;
import bankstatement_analyzer_project.utils.CSVSyntaxException;

public interface IBankStatementParser {
    BankTransaction parseFrom(String line) throws CSVSyntaxException;
    List<BankTransaction> parseLinesFrom(List<String> lines) throws CSVSyntaxException;
}
