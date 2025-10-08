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

package jmul.math.functions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * An implementation of a polynomial function f(x) = c<sub>n</sub> * x<sup>n</sup> + c<sub>n-1</sub> * x<sup>n-1</sup> + ... + c<sub>1</sub> * x + c<sub>0</sub>.
 *
 * @author Kristian Kutin
 */
public class PolynomialFunctionImpl extends FunctionBaseImpl {

    /**
     * All coefficients of the function. The index position determines the position within the formula (see class
     * description; in ascending order c<sub>0</sub>, c<sub>1</sub>, c<sub>2</sub>, ..., c<sub>n</sub>).
     *
     * @author Kristian Kutin
     */
    private final List<Number> coefficients;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param coefficients
     *        all coefficients. The index position determines the position within the formula (see class description;
     *        in ascending order c<sub>0</sub>, c<sub>1</sub>, c<sub>2</sub>, ..., c<sub>n</sub>).
     */
    PolynomialFunctionImpl(Number... coefficients) {

        super(extractBase(coefficients));

        this.coefficients = Collections.unmodifiableList(Arrays.asList(coefficients));
    }

    /**
     * Checks the specified parameter and tries to extract a number base.
     *
     * @param coefficients
     *        all coefficients
     *
     * @return a number base
     */
    private static int extractBase(Number... coefficients) {

        if (coefficients == null) {

            throw new IllegalArgumentException("No coefficients (null) were specified!");
        }

        if (coefficients.length == 0) {

            throw new IllegalArgumentException("No coefficients (empty array) were specified!");
        }

        return coefficients[0].base();
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param coefficients
     *        all coefficients. The index position determines the position within the formula (see class description).
     */
    PolynomialFunctionImpl(List<Number> coefficients) {

        super(extractBase(coefficients));

        this.coefficients = Collections.unmodifiableList(coefficients);
    }

    /**
     * Checks the specified parameter and tries to extract a number base.
     *
     * @param coefficients
     *        all coefficients
     *
     * @return a number base
     */
    private static int extractBase(List<Number> coefficients) {

        if (coefficients == null) {

            throw new IllegalArgumentException("No coefficients (null) were specified!");
        }

        if (coefficients.size() == 0) {

            throw new IllegalArgumentException("No coefficients (empty list) were specified!");
        }

        return coefficients.get(0).base();
    }

    /**
     * Calculate the function value for x.
     *
     * @param x
     *        the input value
     *
     * @return f(x)
     */
    @Override
    public Number calculate(Number x) {

        final Number ZERO = createNumber(base(), "0");

        Number sum = ZERO;

        for (int index = 0; index < coefficients.size(); index++) {

            Number coefficient = coefficients.get(index);

            Number result = coefficient;
            for (int exponent = 1; exponent <= index; exponent++) {

                result = result.multiply(x);
            }

            sum = sum.add(result);
        }

        return sum;
    }

    /**
     * Calculate the function value for x.
     *
     * @param processingDetails
     *        additonal processing details
     * @param x
     *        the input value
     *
     * @return f(x)
     */
    @Override
    public Number calculate(ProcessingDetails processingDetails, Number x) {

        final Number ZERO = createNumber(base(), "0");

        Number sum = ZERO;

        for (int index = 0; index < coefficients.size(); index++) {

            Number coefficient = coefficients.get(index);

            Number result = coefficient;
            for (int exponent = 1; exponent <= index; exponent++) {

                result = result.multiply(x);
            }

            sum = sum.add(result);
        }

        return sum;
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        Number ZERO = createNumber(base(), "0");
        List<Number> newCoefficients = new ArrayList<>();

        Iterator<Number> iterator = coefficients.iterator();

        if (!iterator.hasNext()) {

            return new MonomialFunctionImpl(ZERO, ZERO);
        }

        iterator.next();

        Number index = createNumber(base(), "1");

        while (iterator.hasNext()) {

            Number coefficient = iterator.next();
            Number newCoefficient = coefficient.multiply(index);

            newCoefficients.add(newCoefficient);
            index = index.inc();
        }

        if (newCoefficients.isEmpty()) {

            return new MonomialFunctionImpl(ZERO, ZERO);
        }

        return new PolynomialFunctionImpl(newCoefficients);
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        if (coefficients.size() == 0) {

            return "0";
        }

        StringBuilder buffer = new StringBuilder();

        for (int index = coefficients.size() - 1; index >= 0; index--) {

            Number coefficient = coefficients.get(index);

            if (!coefficient.isZero()) {

                buffer.append(coefficient);

                if (index > 0) {

                    buffer.append(" * x");

                    if (index > 1) {

                        buffer.append("^");
                        buffer.append(index);
                    }

                    buffer.append(" + ");
                }
            }
        }

        String s = buffer.toString();

        int lastIndex = s.length() - 1;
        if (!s.isEmpty() && (s.charAt(lastIndex) == ' ')) {

            int startIndex = lastIndex - 2;
            s = s.substring(0, startIndex);
        }

        return s;
    }

}
