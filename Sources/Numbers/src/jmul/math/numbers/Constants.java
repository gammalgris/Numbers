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

import java.util.ResourceBundle;


/**
 * This class contains various constant values.
 *
 * @author Kristian Kutin
 */
public final class Constants {

    /**
     * A default number base,
     */
    public static final int DEFAULT_NUMBER_BASE;

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

    /*
     * The static initializer,.
     */
    static {

        DEFAULT_NUMBER_BASE = PropertiesLookup.defaultBase();

        EXPONENT_ABBREVIATION = "E";

        BASE_MIN_LIMIT = 2;
        BASE_MAX_LIMIT = 62;

        DECIMAL_COMMA = ',';
        DECIMAL_POINT = '.';

        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();

        DECIMAL_SEPARATOR = symbols.getDecimalSeparator();

        INFINITY_REPRESENTATION = PropertiesLookup.infinityRepresentation();
    }

    /**
     * The default constructor.
     */
    private Constants() {

        throw new UnsupportedOperationException();
    }

}

/**
 * A utility class to look up certain properties.
 *
 * @author Kristian Kutin
 */
final class PropertiesLookup {

    /**
     * Constains the name of the resource bundle.
     */
    private static final String BUNDLE_NAME;

    /**
     * Contains the key for a property.
     */
    private static final String DEFAULT_BASE_KEY;

    /**
     * Contains the key for a property.
     */
    private static final String INFINITY_REPRESENTATION_KEY;

    /*
     * The static initializer.
     */
    static {

        BUNDLE_NAME = Number.class.getName();

        DEFAULT_BASE_KEY = "default-base";
        INFINITY_REPRESENTATION_KEY = "infinity-representation";
    }

    /**
     * The default constructor.
     */
    private PropertiesLookup() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a resource bundle.
     *
     * @return a resource bundle
     */
    private static ResourceBundle getBundle() {

        return ResourceBundle.getBundle(BUNDLE_NAME);
    }

    /**
     * Returns the default base for new numbers.
     *
     * @return a number base
     */
    public static int defaultBase() {

        ResourceBundle bundle = getBundle();
        String value = bundle.getString(DEFAULT_BASE_KEY);
        int base = Integer.parseInt(value);

        return base;
    }

    /**
     * Returns a represenration for infinity.
     *
     * @return a string representation for infinity
     */
    public static String infinityRepresentation() {

        ResourceBundle bundle = getBundle();
        String value = bundle.getString(INFINITY_REPRESENTATION_KEY);

        return value;
    }

}
