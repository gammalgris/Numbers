/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
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

package jmul.math.numbers.notations.regex;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import jmul.math.operations.implementations.ParameterCheckHelper;


/**
 * This entity provides details about parsing a number in standard notation.<br>
 * <br>
 * <i>Note:<br>
 * The scientific notation compies to this pattern:<br>
 * <br>
 * &nbsp;&nbsp;&nbsp;&nbsp;{sign}{mantissa}E{exponent}<br>
 * <br>
 * Using the letter E (or e) as a digit leads to disambiguities. To reduce disambiguities there are two cases to
 * handle.<br>
 * <br>
 * <ul>
 *   <li>E (or e) is used as a digit. Remove the disambiguitiy by making the sign of the exponent mandatory.</li>
 *   <li>E (or e) is not used as a digit. There is no disambiguity and the sign of teh exponent can stay optional.</li>
 * </ul>
 * </i>
 *
 * @author Kristian Kutin
 */
public class ScientificNotationRegex implements NotationRegex {

    /**
     * A placeholder.
     */
    private static final String DIGITS_PLACEHOLDER;

    /**
     * A placeholder.
     */
    private static final String DIGITS_PLACEHOLDER_WITHOUT_ZERO;

    /**
     * A regex with placeholders. The sign of the exponent is optional.
     */
    private static final String SCIENTIFIC_NOTATION_1_PATTERN =
        "^(?<SIGN>[+-]){0,1}(?<MANTISSA>%DIGITS%[.,]%DIGITS%*%DIGITS_WITHOUT_ZERO%){1}[0]*[eE](?<SIGNEXPONENT>[+-]){0,1}(?<EXPONENT>%DIGITS%%DIGITS%*){1}$";

    /**
     * A regex with placeholders. The sign of the exponent is mandatory.
     */
    private static final String SCIENTIFIC_NOTATION_2_PATTERN =
        "^(?<SIGN>[+-]){0,1}(?<MANTISSA>%DIGITS%[.,]%DIGITS%*%DIGITS_WITHOUT_ZERO%){1}[0]*[eE](?<SIGNEXPONENT>[+-]){1}(?<EXPONENT>%DIGITS%%DIGITS%*){1}$";

    /**
     * Capturing groups defined in the regex.
     */
    private static final List<String> CAPTURING_GROUPS;

    /*
     * The static initializer.
     */
    static {

        DIGITS_PLACEHOLDER = "%DIGITS%";
        DIGITS_PLACEHOLDER_WITHOUT_ZERO = "%DIGITS_WITHOUT_ZERO%";

        List<String> capturingGroups = new ArrayList<>();
        capturingGroups.add("SIGN");
        capturingGroups.add("MANTISSA");
        capturingGroups.add("SIGNEXPONENT");
        capturingGroups.add("EXPONENT");
        CAPTURING_GROUPS = Collections.unmodifiableList(capturingGroups);
    }

    /**
     * A number base.
     */
    private final int base;

    /**
     * A regular expression for parsing numbers in the standard notation.
     */
    private final String regexString;

    /**
     * A compiled regex pattern.
     */
    private final Pattern pattern;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param allowedDigits
     *        a set of digits as regex snippet
     * @param allowedDigitsWithoutZero
     *        a set of digits as regex snippet
     */
    public ScientificNotationRegex(int base, String allowedDigits, String allowedDigitsWithoutZero) {

        super();

        ParameterCheckHelper.checkNumberBase(base);
        checkParameter(allowedDigits);
        checkParameter(allowedDigitsWithoutZero);

        this.base = base;

        if (allowedDigits.contains("E") || allowedDigits.contains("e")) {

            this.regexString = createRegex(SCIENTIFIC_NOTATION_2_PATTERN, allowedDigits, allowedDigitsWithoutZero);

        } else {

            this.regexString = createRegex(SCIENTIFIC_NOTATION_1_PATTERN, allowedDigits, allowedDigitsWithoutZero);
        }

        this.pattern = Pattern.compile(regexString);
    }

    /**
     * Checks the specified parameter.
     *
     * @param s
     *        a regex snippet
     *
     * @return the specified parameter
     */
    private static String checkParameter(String s) {

        if (s == null) {

            throw new IllegalArgumentException("No regex snippet (null) was specified!");
        }

        if (s.trim().isEmpty()) {

            throw new IllegalArgumentException("No regex snippet (empty string) was specified!");
        }

        return s;
    }

    /**
     * Creates a regex accoding to the specified parameters.
     *
     * @param regex
     *        the regex with placeholders
     * @param allowedDigits
     *        a regex snippet with all allowed digits
     * @param allowedDigitsWithoutZero
     *        a regex snippet with a subset of allowed digits
     *
     * @return a regex
     */
    private static String createRegex(String regex, String allowedDigits, String allowedDigitsWithoutZero) {

        String result = regex;
        result = result.replace(DIGITS_PLACEHOLDER, allowedDigits);
        result = result.replace(DIGITS_PLACEHOLDER_WITHOUT_ZERO, allowedDigitsWithoutZero);

        return result;
    }

    /**
     * Returns the regex string which describes this notation.
     *
     * @return a regex string
     */
    @Override
    public String regexString() {

        return regexString;
    }

    /**
     * Returns a compiled regex pattern.
     *
     * @return a compiled regex pattern
     */
    @Override
    public Pattern pattern() {

        return pattern;
    }

    /**
     * Returns a list of named capturing groups which are defined within the regex string.
     *
     * @return a list of names of capturing groups
     */
    @Override
    public List<String> namedCapturingGroups() {

        return CAPTURING_GROUPS;
    }

}
