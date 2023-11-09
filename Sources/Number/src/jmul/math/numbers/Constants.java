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
 * This class contains various constant values.
 *
 * @author Kristian Kutin
 */
public final class Constants {

    /**
     * Theabbreviation for an exponent in a scientific notation.
     */
    public static final String EXPONENT_ABBREVIATION;

    /**
     * The allowed maximum value for a base.
     */
    public static final int BASE_MAX_LIMIT;

    /**
     * The allowed minimum value for a base.
     */
    public static final int BASE_MIN_LIMIT;

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
     * A character representing zero.
     */
    public static final char ZERO;

    /*
     * The static initializer,.
     */
    static {

        ZERO = '0';

        EXPONENT_ABBREVIATION = "E";

        BASE_MIN_LIMIT = 2;
        BASE_MAX_LIMIT = 65;

        DECIMAL_COMMA = ',';
        DECIMAL_POINT = '.';

        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();

        DECIMAL_SEPARATOR = symbols.getDecimalSeparator();

        INFINITY_REPRESENTATION = "infinity";
    }

    /**
     * The default constructor.
     */
    private Constants() {

        throw new UnsupportedOperationException();
    }

}
