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

package jmul.math.indices;


import jmul.math.numbers.Number;


/**
 * This itnerface describes a repository which manages index numbers.<br>
 * <br>
 * <i>Note:</i><br>
 * <i>This repository aims at conserving memory by reusing used index numbers.</>
 *
 * @author Kristian Kutin
 */
interface IndexRepository {

    /**
     * Returns the next index to the specified index.
     *
     * @param index
     *        an index (i.e. a positive integer which is not zero)
     *
     * @return the next index
     */
    Number nextIndex(Number index);

    /**
     * Returns the first index.
     *
     * @return the first index
     */
    Number firstIndex();

    /**
     * Returns the number base for this index repostory.
     *
     * @return a default number base
     */
    int base();

}
