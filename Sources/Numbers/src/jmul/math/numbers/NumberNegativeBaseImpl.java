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

package jmul.math.numbers;


/**
 * An implementation of a real number with a negative number base. This implementation requires memory depending on the
 * number of digits the number consists of. Even very large or very small numbers can be represented without rounding.
 * Since the underlying data structure is a linked list numbers can exceed the array size limitations in java.<br>
 * Thus it might not be possible to create a string representation for every number (see array size limitations).<br>
 * <br>
 * <i>Note:<br>
 * Consider implementing a stream mechanism to serialize/ deserialize very large or very small numbers.</i><br>
 * <br>
 * <i>Node:<br>
 * Consider an alternative implementation which compresses the linked list in some way in order to save up memory.</i>
 *
 * TODO check if a separate implementation is required or not
 * TODO a sign might not be necessary for numbers with a negative base (i.e. a sign might lead to two numbers representing the same number)
 * TODO Set the positive sign as default?
 * TODO Test how this affects addition, subtraction, etc.)
 *
 * @author Kristian Kutin
 */
public class NumberNegativeBaseImpl {

    public NumberNegativeBaseImpl() {

        super();
    }

}
