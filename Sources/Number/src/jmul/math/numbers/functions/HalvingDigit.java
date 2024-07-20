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

package jmul.math.numbers.functions;


import jmul.math.numbers.digits.Digit;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.operations.UnaryOperation;


/**
 * Implements a function that halves a digit (i.e. divides it by two).
 *
 * @author Kristian Kutin
 */
public class HalvingDigit implements UnaryOperation<Digit, ResultWithRemainder<Digit>> {

    /**
     * The default constructor.
     */
    public HalvingDigit() {

        super();
    }

    /**
     * Takes the specified digit and halves it (i.e. divides it by 2 in the corresponding number base).<br>
     * <br>
     * <i>Note:<br>
     * Number base 2 may need a different algorithm than the other number bases.</i>
     *
     * @param digit
     *        a digit
     *
     * @return the result with the remainder
     */
    @Override
    public ResultWithRemainder<Digit> calculate(Digit digit) {

        // TODO Implement this method
        return null;
    }

}
