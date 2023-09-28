package bankstatement_analyzer_project;

import java.io.IOException;

import bankstatement_analyzer_project.services.BankStatementAnalyzer;
import bankstatement_analyzer_project.utils.BankStatementParser;
import bankstatement_analyzer_project.utils.CSVSyntaxException;

public class MainApplication {
    public static void main(final String... args) throws IOException, CSVSyntaxException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser = new BankStatementParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
