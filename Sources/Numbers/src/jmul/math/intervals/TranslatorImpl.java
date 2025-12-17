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

package jmul.math.intervals;


import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;


/**
 * An implementation of a translator.
 *
 * @author Kristian Kutin
 */
public class TranslatorImpl implements Translator {

    /**
     * The origin interval.
     */
    private final Interval originInterval;

    /**
     * The destination interval.
     */
    private final Interval destinationInterval;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param originInterval
     *        an origin interval
     * @param destinationInterval
     *        a destination interval
     */
    TranslatorImpl(Interval originInterval, Interval destinationInterval) {

        super();

        if (originInterval == null) {

            throw new IllegalArgumentException("No origin interval (null) was specified!");
        }

        if (destinationInterval == null) {

            throw new IllegalArgumentException("No destination interval (null) was specified!");
        }

        if (originInterval.base() != destinationInterval.base()) {

            throw new IllegalArgumentException("Number base mismatch!");
        }

        this.originInterval = originInterval;
        this.destinationInterval = destinationInterval;
    }

    /**
     * The number base.
     *
     * @return a number base
     */
    @Override
    public int base() {

        return originInterval.base();
    }

    /**
     * Returns a string representation for this translator.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s -> %s", originInterval, destinationInterval);
    }

    /**
     * Translates the specified number from an origin interval to a destination interval.
     *
     * @param number
     *        a number which has to be within the origin interval
     *
     * @return a translated number
     */
    @Override
    public Number translate(Number number) {

        ParameterCheckHelper.checkParameter(number);

        if (base() != number.base()) {

            throw new IllegalArgumentException("Number base mismatch!");
        }

        Number deltaOrigin = originInterval.length();
        Number deltaDestination = destinationInterval.length();
        Number ratio = deltaDestination.divide(deltaOrigin);

        Number result = number.subtract(originInterval.lowerBoundary());
        result = result.multiply(ratio);
        result = result.add(destinationInterval.lowerBoundary());

        return result;
    }

    /**
     * Returns the origin interval.
     *
     * @return an interval
     */
    @Override
    public Interval originInterval() {

        return originInterval;
    }

    /**
     * Returns the destination interval.
     *
     * @return an interval
     */
    @Override
    public Interval destinationInterval() {

        return destinationInterval;
    }

}
