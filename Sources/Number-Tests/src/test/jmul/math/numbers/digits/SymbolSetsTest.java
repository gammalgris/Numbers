package test.jmul.math.numbers.digits;


import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;
import java.util.List;

import javax.xml.stream.events.Characters;

import jmul.math.numbers.NumberImpl;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import jmul.math.numbers.digits.SymbolSets;

import jmul.test.classification.UnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite checks all available symbols sets.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SymbolSetsTest {

    /**
     * The base for a positional numeral system.
     */
    private final int base;

    /**
     * The excpected exception.
     */
    private final Class expectedException;

    /**
     * A set containing all digit symbols.
     */
    private final SymbolSets symbolSets;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param base
     *        the base for a positional numeral system.
     * @param expectedException
     *        the expected exception if the base is not supported
     */
    public SymbolSetsTest(int base, Class expectedException) {

        super();

        this.base = base;
        this.expectedException = expectedException;

        this.symbolSets = new SymbolSets();
    }

    @Test
    public void checkSubset() {

        char[] characters = null;
        try {

            characters = symbolSets.subset(base);

        } catch (Exception e) {

            if (expectedException == null) {

                throw e;
            }

            Class actualException = e.getClass();
            if (actualException == expectedException) {

                return;

            } else {

                String message =
                    String.format("%s is expected but %s was thrown!", expectedException.getName(),
                                  actualException.getName());
                fail(message);
            }
        }

        List<Character> uniqueSymbols = new ArrayList<>();
        List<Character> duplicateSymbols = new ArrayList<>();

        for (char character : characters) {

            if (uniqueSymbols.contains(character)) {

                duplicateSymbols.add(character);

            } else {

                uniqueSymbols.add(character);
            }
        }

        {
            String message = String.format("The base %d has no unique symbols (%s)!", base, uniqueSymbols.toString());
            assertFalse(message, uniqueSymbols.isEmpty());
        }

        {
            String message = String.format("The base %d has duplicate symbols (%s)!", base, duplicateSymbols.toString());
            assertTrue(message, duplicateSymbols.isEmpty());
        }
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = -1; base < 100; base++) {

            if (base < 2) {
                
                parameters.add(new Object[] { base, IllegalArgumentException.class });

            } else if (base >= 66) {

                parameters.add(new Object[] { base, IllegalArgumentException.class });

            } else {

                parameters.add(new Object[] { base, null });
            }
        }

        return parameters;
    }

}
