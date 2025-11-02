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

package jmul.math.collections;


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


/**
 * A utility class for collections.
 *
 * @author Kristian Kutin
 */
public final class CollectionsHelper {

    /**
     * The default constructor.
     */
    private CollectionsHelper() {

        throw new IllegalArgumentException();
    }

    /**
     * Creates a new number set according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param elementStrings
     *        all element string
     *
     * @return a number set
     */
    public static Set<Number> createNumberSet(int base, String... elementStrings) {

        Number[] elements = new Number[elementStrings.length];

        for (int a = 0; a < elementStrings.length; a++) {

            Number element = createNumber(base, elementStrings[a]);
            elements[a] = element;
        }

        return new SetImpl<>(base, elements);
    }

    /**
     * Creates a new number sequence according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param elementStrings
     *        all element string
     *
     * @return a number sequence
     */
    public static Sequence<Number> createNumberSequence(int base, String... elementStrings) {

        Number[] elements = new Number[elementStrings.length];

        for (int a = 0; a < elementStrings.length; a++) {

            Number element = createNumber(base, elementStrings[a]);
            elements[a] = element;
        }

        return new SequenceImpl<>(base, elements);
    }

}
