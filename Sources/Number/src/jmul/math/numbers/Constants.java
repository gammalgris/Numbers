/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.numbers;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


/**
 * This utility class contains certain constants.
 *
 * @author Kristian Kutin
 */
public final class Constants {

    /**
     * The constant contains the decimal separator for this system. The actual decimal separator
     * depends on the current locale.
     */
    public static final char DECIMAL_SEPARATOR;

    /**
     * The constant contains a decimal comma which is used as decimal separator in some regions.
     */
    public static final char DECIMAL_COMMA;

    /**
     * The constant contains a decimal point which is used as decimal separator in some regions.
     */
    public static final char DECIMAL_POINT;

    /**
     * The constant contains a representation for infinity.
     */
    public static final String INFINITY_REPRESENTATION;

    /**
     * This constant contains the name of a named capturing group used within {@link #DECIMAL_STANDARD_NOTATION_PATTERN_STRING}
     * and {@link #DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING}
     */
    public static final String SIGN_GROUP;

    /**
     * This constant contains the name of a named capturing group used within {@link #DECIMAL_STANDARD_NOTATION_PATTERN_STRING}.
     */
    public static final String LEFT_GROUP;

    /**
     * This constant contains the name of a named capturing group used within {@link #DECIMAL_STANDARD_NOTATION_PATTERN_STRING}.
     */
    public static final String RIGHT_GROUP;

    /**
     * This constant contains a regular expression which is used for identifying numbers written in the standard
     * notation (i.e. a sequence of digits).
     */
    public static final String DECIMAL_STANDARD_NOTATION_PATTERN_STRING;

    /**
     * This constant contains the name of a named capturing group used within {@link #DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING}
     */
    public static final String MANTISSA_GROUP;

    /**
     * This constant contains the name of a named capturing group used within {@link #DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING}
     */
    public static final String SIGN_EXPONENT_GROUP;

    /**
     * This constant contains the name of a named capturing group used within {@link #DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING}
     */
    public static final String EXPONENT_GROUP;

    /**
     * This constant contains a regular expression which is used for identifying numbers written in the scientifc
     * notation (i.e. a sequence of digits).
     */
    public static final String DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING;

    /*
     * The static initializer.
     */
    static {

        DECIMAL_COMMA = ',';
        DECIMAL_POINT = '.';

        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();

        DECIMAL_SEPARATOR = symbols.getDecimalSeparator();

        INFINITY_REPRESENTATION = "Infinity";

        SIGN_GROUP = "sign";
        LEFT_GROUP = "left";
        RIGHT_GROUP = "right";

        DECIMAL_STANDARD_NOTATION_PATTERN_STRING =
            String.format("^(?<%s>[+-]{0,1})[0]*(?<%s>(([1-9][0-9]*)|([0]))){1}([,.](?<%s>[0]|[0-9]*[1-9])([0]+){0,1}){0,1}$",
                          SIGN_GROUP, LEFT_GROUP, RIGHT_GROUP);

        MANTISSA_GROUP = "mantissa";
        SIGN_EXPONENT_GROUP = "signExponent";
        EXPONENT_GROUP = "exponent";

        DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING =
            String.format("^(?<%s>[+-]){0,1}(?<%s>[0-9][.,][0-9]*[1-9]){1}[0]*[E](?<%s>[+-]){0,1}(?<%s>[1-9][0-9]*){1}$",
                          SIGN_GROUP, MANTISSA_GROUP, SIGN_EXPONENT_GROUP, EXPONENT_GROUP);
    }

    /**
     * The default constructor.
     */
    private Constants() {

        throw new UnsupportedOperationException();
    }

}
