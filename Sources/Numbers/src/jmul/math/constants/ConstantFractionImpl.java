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

package jmul.math.constants;


import java.util.HashMap;
import java.util.Map;

import jmul.math.Math;
import jmul.math.fractions.Fraction;
import jmul.math.functions.implementations.ParameterCheckHelper;
import static jmul.math.functions.implementations.ParameterCheckHelper.checkParameter;
import jmul.math.numbers.Number;


/**
 * A constant fraction for various number bases.
 *
 * @author Kristian Kutin
 */
class ConstantFractionImpl implements Constant {

    /**
     * The default base for this constant.
     */
    private final int defaultBase;

    /**
     * The default value for this constant.
     */
    private final Fraction defaultValue;

    /**
     * A default precision to evaluate the fraction.
     */
    private final Number defaultPrecision;

    /**
     * A map which associates a constant value with various number bases.
     */
    private final Map<Integer, Fraction> constants;

    /**
     * A map which contains the precision for various number bases.
     */
    private final Map<Integer, Number> precisions;

    /**
     * Creates a new constant according to the specified initial value.
     *
     * @param value
     *        a fraction
     */
    protected ConstantFractionImpl(Fraction value) {

        this(checkParameter(value), Math.getDefaultMaximumFractionLength(value.base()));
    }

    /**
     * Creates a new constant according to the specified initial value.
     *
     * @param value
     *        a constant value
     * @param precision
     *        a precision (i.e. limit to the number of digits of the fraction part)
     */
    protected ConstantFractionImpl(Fraction value, Number precision) {

        super();

        this.defaultValue = checkParameter(value);
        this.defaultBase = value.base();
        this.defaultPrecision = checkParameter(precision);

        this.constants = new HashMap<>();
        this.precisions = new HashMap<>();

        this.constants.put(defaultBase, defaultValue);
        this.precisions.put(defaultBase, defaultPrecision);
    }

    /**
     * Returns the constant value for the specified number base.
     *
     * @param base
     *        a number base
     *
     * @return a constant value
     */
    @Override
    public Number value(int base) {

        ParameterCheckHelper.checkNumberBase(base);

        Fraction value = null;
        Number precision = null;
        synchronized (this) {

            value = constants.get(base);
            precision = precisions.get(base);

            if (value == null) {

                value = defaultValue.rebase(base);
                constants.put(base, value);

                precision = defaultPrecision.rebase(base);
                precisions.put(base, precision);
            }
        }

        Number evaluatedValue = value.evaluate(precision);

        return evaluatedValue;
    }

    /**
     * Returns a string representation for this constant.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("default value = [base:%d] %s (precision: %s)", defaultBase, defaultValue,
                             defaultPrecision);
    }

}
