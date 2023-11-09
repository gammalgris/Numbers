package jmul.math.numbers.digits;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class contains all allowed symbols for digits and provides
 * subsets for various positional numeral systems.
 *
 * TODO Check later if base 1 could work. Until then base 2 is the minimum.
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

        for (int a = 0; a <= 64; a++) {

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

        if ((base < 2) || (base > symbols.size())) {

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
