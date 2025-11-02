/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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


import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.numbers.Number;


/**
 * This iterface describes various trigonometric operations.
 *
 * @author Kristian Kutin
 */
public interface TrigonometricOperations {

    /**
     * Calculates the sine of this number (in radian).
     *
     * @return the sine of this number
     */
    Number sine();

    /**
     * Calculates the sine of this number (in radian) according to the specified proessing parameters.
     *
     * @param processingDetails
     *        procewssing parameters
     *
     * @return the sine of this number
     */
    Number sine(ProcessingDetails processingDetails);

    /**
     * Calculates the cosine of this number (in radian).
     *
     * @return the cosine of this number
     */
    Number cosine();

    /**
     * Calculates the cosine of this number (in radian) according to the specified proessing parameters.
     *
     * @param processingDetails
     *        procewssing parameters
     *
     * @return the cosine of this number
     */
    Number cosine(ProcessingDetails processingDetails);

    /*
    //TODO
    // Add the latter method signatures later. Currently too many method signatures without implementation will only
    // clutter the implementation classes.

    Number tangent();

    Number tangent(ProcessingDetails processingDetails);

    Number cosecant();

    Number cosecant(ProcessingDetails processingDetails);

    Number secant();

    Number secant(ProcessingDetails processingDetails);

    Number cotangent();

    Number cotangent(ProcessingDetails processingDetails);
    */

}
