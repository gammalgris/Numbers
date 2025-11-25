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

package jmul.math.concurrent;


import java.util.Iterator;

import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;


/**
 * A special queue that returns a constant factor.
 *
 * @author Kristian Kutin
 */
public class NextConstantFactorQueue implements Iterator<Number> {

    /**
     * A constant factor.
     */
    private final Number factor;

    /**
     * A counter for how often the constant factor is required.
     */
    private volatile Number counter;

    /**
     * Creates a queue according to the specified parameters.
     *
     * @param factor
     *        a constant factor
     * @param counter
     *        a counter for how often the constant factor is required
     */
    public NextConstantFactorQueue(Number factor, Number counter) {

        super();

        ParameterCheckHelper.checkParameters(factor, counter);

        this.factor = factor;
        this.counter = counter;
    }

    /**
     * Checks if this queue has been exhausted or not.
     *
     * @return <code>true</code> if this queue has not been exhausted, else <code>false</code>
     */
    @Override
    public boolean hasNext() {

        synchronized (this) {

            return !counter.isZero();
        }
    }

    /**
     * Returns the next factor.
     *
     * @return the next factor or <code>null</code> if this queue has been exhausted
     */
    @Override
    public Number next() {

        synchronized (this) {

            Number value = null;

            if (!counter.isZero()) {

                counter = counter.dec();
                value = factor;
            }

            return value;
        }
    }

}
