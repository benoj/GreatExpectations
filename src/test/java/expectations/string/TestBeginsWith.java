package expectations.string;

import expectations.exception.RunnableWithException;
import expectations.string.failures.BeginsWithFailure;
import expectations.string.failures.EndsWithFailure;
import org.junit.Test;

import static expectations.ExpectationFactory.expect;

public class TestBeginsWith {

    private final String actual = "Hello World!";

    @Test
    public void expectBeginsWithWithWordStartingCharactersToPass() throws Exception {
        expect(actual).beginsWith("Hello");
    }

    @Test
    public void expectBeginsWithWithoutStartingCharactersToFail() throws Exception {
        String expected = "World!";
        RunnableWithException test = () -> expect(actual).beginsWith(expected);
        String expectedFailureMessage = String.format("Expected: '%s' to begin with '%s'", actual,expected);
        expect(test).toThrow(BeginsWithFailure.class).withMessage(expectedFailureMessage);
    }

    @Test
    public void expectEndsWithWithEndCharactersToPass() throws Exception {
        expect(actual).endsWith("World!");
    }
    
    @Test
    public void expectEndsWithWithoutEndingCharactersToFail() throws Exception {
        String expected = "le Monde!";
        RunnableWithException test = () -> expect(actual).endsWith(expected);
        String expectedFailureMessage = String.format("Expected: '%s' to end with '%s'", actual,expected);
        expect(test).toThrow(EndsWithFailure.class).withMessage(expectedFailureMessage);
    }
}