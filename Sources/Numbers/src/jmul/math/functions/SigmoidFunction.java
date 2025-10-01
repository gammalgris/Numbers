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
 * <br>
 * f'(x) = e<sup>x</sup> / ( e<sup>x</sup> + 1 )<sup>2</sup><br>
 * <br>
 * f''(x) = ?
 *
 * TODO Calculate more derivatives and identify a pattern.
 * TODO Need to find a suitable representation of the function to generate derivatives.
 */
public class SigmoidFunction extends FunctionBaseImpl {

    /**
     * Creates a new sigmoid function for the specified number base.
     *
     * @param base
     *        a number base
     */
    public SigmoidFunction(int base) {

        super(base);
    }

    /**
     * Evaluate the function with the specified input value.
     *
     * @param number
     *        the input value
     *
     * @return the output value
     */
    @Override
    public Number calculate(Number number) {

        return f(number);
    }

    /**
     * The actual sigmoid function.
     *
     * @param x
     *        an input value
     *
     * @return f(x)
     */
    private Number f(Number x) {

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

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

}
