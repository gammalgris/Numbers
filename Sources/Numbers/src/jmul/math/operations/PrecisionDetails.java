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

package jmul.math.operations;


import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.repository.OperationIdentifier;


/**
 * A parameter class which combines various aspects of calculation precision (e.g. decimal places for rounding) and
 * performance (e.g. a choice of available algorithms).<br>
 * <br>
 * <i>Note:<br>
 * This class should help clean up method signatures and avoid ambiguity.</i>
 *
 * @author Kristian Kutin
 */
public final class PrecisionDetails {

    /**
     * The identifier for an algorithm.
     *
     * <i>Note:<br>
     * For some operations there exist several implementations of different algorithm. The difference may be performance
     * or precision/ aproximation.</i>
     */
    public final OperationIdentifier algorithm;

    /**
     * The number of decimal places retained after cutting the fraction part.
     */
    public final Number decimalPlaces;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param algorithm
     *        the identifier for an algorithm
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     */
    public PrecisionDetails(OperationIdentifier algorithm, Number decimalPlaces) {

        super();

        if (algorithm == null) {

            throw new IllegalArgumentException("No algorithm identifier was specified!");
        }

        ParameterCheckHelper.checkPositiveIntegerGreaterZero(decimalPlaces);

        this.algorithm = algorithm;
        this.decimalPlaces = decimalPlaces;
    }

}
