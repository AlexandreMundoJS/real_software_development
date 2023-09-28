package bankstatement_analyzer_project;

import bankstatement_analyzer_project.domains.BankTransaction;

import bankstatement_analyzer_project.utils.BankStatementParser;
import bankstatement_analyzer_project.utils.CSVSyntaxException;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

public class BankStatementParserTest {
	private final BankStatementParser statementParser = new BankStatementParser();
	
	@Test
	public void shouldParseOneCorrectLine() throws Exception, CSVSyntaxException {
		final String line = "30-01-2017,-50,Tesco";
		
		final BankTransaction result = statementParser.parseFrom(line);
		
		final BankTransaction expected = new BankTransaction(LocalDate.of(2017,  Month.JANUARY, 30), -50, "Tesco");
		final double tolerance = 0d;
		
		Assert.assertEquals(expected.getDate(), result.getDate());
		Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
		Assert.assertEquals(expected.getDescription(), result.getDescription());
	}
}
