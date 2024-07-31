package jmul.math.numbers.digits;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static jmul.math.Constants.MAX_BASE;
import static jmul.math.Constants.MIN_BASE;


/**
 * This class contains all allowed symbols for digits and provides
 * subsets for various positional numeral systems.<br>
 * <br>
 * <i>Note:<br>
 * <ol>
 * <li>Base 1 is not supported. Integers would be represented by zeroes and
 * the zero count would define the integer. It's unclear how to interpret
 * a fraction of zeroes.</li>
 * <li>Minimum is base 2 which represents binary.</li>
 * <li>Maximum base is currently 62. If you allow greater bases then check which symbols
 * should be used for digits. These characters should not be conflict with operators (e.g. +, -, etc.).
 * Accordingly the regular expressions for parsing numbers should be expanded.</li>
 * </ol>
 * </i>
 *
 * @author Kristian Kutin
 */
public class SymbolSets {

    /**
     * A set which contains all allowed symbols.
     */
    private final List<Character> symbols;

    /**
     * The default constructor.
     */
    public SymbolSets() {

        super();

        List<Character> symbols = new ArrayList<>();

        for (int a = 0; a <= MAX_BASE; a++) {

            if (a < 10) {

                int code = 48 + a;
                symbols.add((char) code);

            } else if (a < 36) {

                int code = 55 + a;
                symbols.add((char) code);

            } else {

                int code = 61 + a;
                symbols.add((char) code);
            }
        }

        this.symbols = Collections.unmodifiableList(symbols);
    }

    /**
     * Returns a subset of symbols for a positional numeral system with the specified base.
     *
     * @param base
     *        the base for a positional numeral system
     *
     * @return a subset of symbols
     */
    public char[] subset(int base) {

        if ((base < MIN_BASE) || (base > MAX_BASE)) {

            String message = String.format("Unsuported base %d!", base);
            throw new IllegalArgumentException(message);
        }

        char[] subset = new char[base];
        for (int ordinalNumber = 0; ordinalNumber < base; ordinalNumber++) {

            char symbol = symbols.get(ordinalNumber);
            subset[ordinalNumber] = symbol;
        }

        return subset;
    }

}
