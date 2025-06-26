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

package jmul.math.exponentiations;


import jmul.math.numbers.AbstractNumber;
import jmul.math.numbers.Number;


/**
 * This interface describes an exponentiation expression.<br>
 * <br>
 * See <a href="https://en.wikipedia.org/wiki/Exponentiation">Exponentiation</a><br>
 * <br>
 * <i>Note:<br>
 * This is an intermediate representation to delay evaluation of the expression. Thus
 * you might avoid some rounding errors.</i>
 *
 * @author Kristian Kutin
 */
public interface Exponentiation extends AbstractNumber {

    /**
     * Returns the base of this exponentiation expression.
     *
     * @return the base (i.e. a number)
     */
    Number number();

    /**
     * Returns the exponent of this exponentiation expression.
     *
     * @return the exponent (i.e. a number)
     */
    Number exponent();

    /**
     * Multiplies this exponentiation expression with the specified exponentiation expression. If the bases
     * of the exponentiation expressions are equal then the exponents are added. If the exponents are equal then
     * the bases are multiplied.
     *
     * @param exponentiation
     *        an exponentiation expression
     *
     * @return an exponentiation expression
     */
    Exponentiation multiply(Exponentiation exponentiation);

    /**
     * Divides this exponentiation expression by the specified exponentiation expression. If the bases of
     * the exponentiation expressions are equal then the second exponent is subtracted from the first exponent.
     * If the exponents are equal then the first base is divided by the second base.
     *
     * @param exponentiation
     *        an exponentiation expression
     *
     * @return an exponentiation expression
     */
    Exponentiation divide(Exponentiation exponentiation);

    /**
     * Multiplies the exponent of this epxonentiation expression with the specified number.
     *
     * @param exponent
     *        an exponent (i.e. a number)
     *
     * @return an exponentiation expression
     */
    Exponentiation exponentiate(Number exponent);

    /**
     * Adds the specified exponent to the exponent of this exponentiation expression.
     *
     * @param exponent
     *        an exponent (i.e. a number)
     *
     * @return an exponentiation expression
     */
    Exponentiation add(Number exponent);

    /**
     * Subtracts the specified exponent from the exponent of this exponentiation expression.
     *
     * @param exponent
     *        an exponent (i.e. a number)
     *
     * @return an exponentiation expression
     */
    Exponentiation subtract(Number exponent);

    /**
     * Evaluates this exponentiation expression.
     *
     * @return a number
     */
    Number evaluate();

}
