/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package jmul.math.numbers.operations;


import java.util.Comparator;

import jmul.math.numbers.DigitSequence;
import jmul.math.numbers.builders.MinOperation;
import jmul.math.numbers.comparators.NaturalOrderingComparator;
import jmul.math.numbers.digits.Digit;


/**
 * An implementation of a function which compares two numbers and returns the lower number.
 *
 * @param <T>
 *        The actual implementation of a digit sequence (i,.e. a number)
 *
 * @author Kristian Kutin
 */
public class MinOperationImpl<T extends DigitSequence<? extends Digit>> extends AbstractOperation<T> implements MinOperation<T> {

    /**
     * The default constructor.
     */
    public MinOperationImpl() {

        super();
    }

    /**
     * Compares the two numbers and return the lower number.
     *
     * @param n
     *        a number
     * @param m
     *        a number
     *
     * @return the lower number of the two specified numbers
     */
    @Override
    public T min(T n, T m) {

        Comparator<T> comparator = new NaturalOrderingComparator<>();

        int result = comparator.compare(n, m);

        if (result < 0) {

            return n;

        } else {

            return m;
        }
    }

}
