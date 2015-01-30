package expectations.exception;

import exceptions.ExpectationMismatch;
import exceptions.ExceptionNotThrown;
import org.junit.Test;

import java.io.IOException;
import java.util.EmptyStackException;

import static expectations.ExpectationFactory.expect;
import static org.junit.Assert.assertEquals;

public class ExceptionExpectiaionBuilderTest {

    @Test
    public void expectToThrowPassesWhenExpectationMatches() throws Exception {
        RunnableWithException runnable = () -> {
            throw new Exception();
        };
        expect(runnable).toThrow(Exception.class);
    }

    @Test(expected = ExceptionNotThrown.class)
    public void expectToThrowFailsWithExceptionNotThrownWhenNoException() throws Exception {
        expect(() -> {}).toThrow(Exception.class);
    }

    @Test(expected = ExpectationMismatch.class)
    public void expectToThrowFailsWithExceptionMismatchWhenExpectedExceptionIsNotEqual() throws Exception {
        RunnableWithException runnable = () -> {
            throw new IOException();
        };
        expect(runnable).toThrow(Exception.class);
    }

    @Test
    public void expectToThrowExceptionMismatchShowsTheExpectedAndActualExceptionTypes() throws ExceptionNotThrown {
        try {
            RunnableWithException runnable = () -> {
                throw new Exception();
            };
            expect(runnable).toThrow(EmptyStackException.class);
        } catch (ExpectationMismatch e) {
            assertEquals(e.getMessage(), String.format("Expected: %s, Actual: %s", EmptyStackException.class, Exception.class));
        }
    }
}