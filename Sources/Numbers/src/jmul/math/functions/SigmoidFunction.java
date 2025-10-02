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


import jmul.math.Math;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


/**
 * This class implements a sigmoid function.<br>
 * <br>
 * f(x) = 1 / ( 1 + e<sup>x</sup> )<br>
 *
 * @author Kristian Kutin
 */
public class SigmoidFunction extends FunctionBaseImpl {

    /**
     * Creates a new sigmoid function for the specified number base.
     *
     * @param base
     *        a number base
     */
    protected SigmoidFunction(int base) {

        super(base);
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

        final Number ONE = createNumber(base(), "1");
        final Number e = Math.e(base());

        return (ONE.divide(ONE.add(e.exponentiate(x.negate()))));
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        return new SigmoidFunctionFirstDerivative(base());
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return "1 / ( 1 + e^x )";
    }

}


/**
 * An implementation of the first derivative of a sigmoid function.<br>
 * <br>
 * f'(x) = e<sup>x</sup> / ( e<sup>x</sup> + 1 )<sup>2</sup><br>
 *
 * @author Kristian Kutin
 */
class SigmoidFunctionFirstDerivative extends FunctionBaseImpl {

    /**
     * Creates a new function for the specified number base.
     *
     * @param base
     *        a number base
     */
    public SigmoidFunctionFirstDerivative(int base) {

        super(base);
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

        final Number ONE = createNumber(base(), "1");
        final Number TWO = ONE.inc();
        final Number e = Math.e(base());

        return (e.exponentiate(x)).divide(((e.exponentiate(x)).add(ONE)).exponentiate(TWO));
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return "e^x / ( e^x + 1 )^2";
    }

}
