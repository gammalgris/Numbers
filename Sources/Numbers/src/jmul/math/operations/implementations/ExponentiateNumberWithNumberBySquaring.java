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

package jmul.math.operations.implementations;


import jmul.math.Math;
import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * Implements the exponentiation function with exponents that are integers (see
 * <a href="https://en.wikipedia.org/wiki/Exponentiation_by_squaring">exponentiation by squaring</a>).
 *
 * @author Kristian Kutin
 */
public class ExponentiateNumberWithNumberBySquaring implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public ExponentiateNumberWithNumberBySquaring() {

        super();
    }

    /**
     * Exponentiates the specified number by the specified exponent.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent (i.e. an integer)
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    @Override
    public Result<Number> calculate(Number number, Number exponent, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(number, exponent, decimalPlaces);
        ParameterCheckHelper.checkIntegerIgnoreNull(exponent);
        ParameterCheckHelper.checkPositiveInteger(decimalPlaces);

        if (number.isZero()) {

            System.out.println("zero^?");
        }

        int base = number.base();
        final Number ONE = Math.ONE.value(base);
        final Number ZERO = Math.ZERO.value(base);

        if (number.isInfinity()) {

            if (exponent.isNegative()) {

                return new Result<Number>(ZERO);

            } else if (exponent.isZero()) {

                String message = String.format("(%s)^%s is an undefined operation!", number, exponent);
                throw new UndefinedOperationException(message);

            } else if (exponent.isEven()) {

                Number result = number.absoluteValue();
                return new Result<Number>(result);

            } else {

                Number result = createNumber(CLONE, number);
                return new Result<Number>(result);
            }
        }

        if (exponent.isZero()) {

            Number clone = createNumber(CLONE, ONE);
            return new Result<Number>(clone);

        } else if (exponent.isPositive() && exponent.isOne()) {

            Number clone = createNumber(CLONE, number);
            return new Result<Number>(clone);

        } else if (exponent.isNegative() && exponent.isOne()) {

            Fraction reciprocal = number.reciprocal();
            Number result = reciprocal.evaluate(decimalPlaces);
            return new Result<Number>(result);
        }

        Number result;
        if (exponent.isNegative()) {

            Number absoluteExponent = exponent.absoluteValue();
            Fraction reciprocal = number.reciprocal();
            result = exponentiate(reciprocal, absoluteExponent, decimalPlaces);

        } else {

            result = exponentiate(number, exponent, decimalPlaces);
        }

        return new Result<Number>(result);
    }

    /**
     * Exponentiates the specified number by the specified exponent.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent (i.e. a positive integer greater than or equal to one)
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    private Number exponentiate(Number number, Number exponent, Number decimalPlaces) {

        int base = number.base();
        final Number ONE = Math.ONE.value(base);

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        Number x = number;
        Number n = exponent;
        Number y = ONE;

        while (n.isGreater(ONE)) {

            if (isOdd(n)) {

                y = x.multiply(y);
                y = y.round(processingDetails);
                n = n.dec();
            }

            x = x.multiply(x);
            x = x.round(processingDetails);
            n = n.halving();
        }

        x = x.multiply(y);
        x = x.round(processingDetails);

        return x;
    }

    /**
     * Checks if the specified number is odd.<br>
     * <br>
     * <i>Note:<br>
     * A number might be odd or even within a specific positional numeral system of a specific base, but there are
     * differences between odd and even number bases. E.g.:</i><br>
     * <br>
     * <table>
     * <tr>
     * <th>base 10</th>
     * <th></th>
     * <th>base 3</th>
     * <th></th>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>odd</td>
     * <td>1<td>
     * <td>odd</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>even</td>
     * <td>2<td>
     * <td>even</td>
     * </tr>
     * <tr>
     * <td>3</td>
     * <td>odd</td>
     * <td>10<td>
     * <td>even</td>
     * </tr>
     * <tr>
     * <td>4</td>
     * <td>even</td>
     * <td>11<td>
     * <td>odd</td>
     * </tr>
     * <tr>
     * <td>5</td>
     * <td>odd</td>
     * <td>12<td>
     * <td>even</td>
     * </tr>
     * <tr>
     * <td>6</td>
     * <td>even</td>
     * <td>20<td>
     * <td>even</td>
     * </tr>
     * <tr>
     * <td>7</td>
     * <td>odd</td>
     * <td>21<td>
     * <td>odd</td>
     * </tr>
     * <tr>
     * <td>8</td>
     * <td>even</td>
     * <td>22<td>
     * <td>even</td>
     * </tr>
     * <tr>
     * <td>9</td>
     * <td>odd</td>
     * <td>100<td>
     * <td>even</td>
     * </tr>
     * <tr>
     * <td>10</td>
     * <td>even</td>
     * <td>101<td>
     * <td>odd</td>
     * </tr>
     * </table>
     * <br>
     * <i>The table shows that determining if a number is odd or even based on the last digit, might not suffice.</i>
     *
     * @param n
     *        a number
     *
     * @return <code>true</code> if the specified number is odd, else <code>false</code>
     */
    private boolean isOdd(Number n) {

        int base = n.base();

        if (base % 2 == 0) {

            return n.isOdd();

        } else {

            Number digitSum = n.digitSum();
            return digitSum.isOdd();
        }
    }

    /**
     * Exponentiates the specified number (i.e. reciprocal of a number) by the specified exponent.
     *
     * @param reciprocal
     *        the reciprocal of a number
     * @param exponent
     *        an exponent (i.e. a positive integer greater than or equal to one)
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    private Number exponentiate(Fraction reciprocal, Number exponent, Number decimalPlaces) {

        Number n = reciprocal.evaluate(decimalPlaces);

        return exponentiate(n, exponent, decimalPlaces);
    }

}
