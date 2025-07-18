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

package jmul.math.matrices;


import jmul.math.numbers.Number;
import jmul.math.operations.MatrixOperations;
import jmul.math.vectors.Vector;


/**
 * This interface defines a matrix.
 *
 * @author Kristian Kutin
 */
public interface Matrix extends MatrixOperations {

    /**
     * Returns the component at the specified index positions.
     * 
     * @param columnIndex
     *        a column index
     * @param rowIndex
     *        a row index
     * 
     * @return a number
     */
    Number component(Number columnIndex, Number rowIndex);

    /**
     * Returns the number base of this matrix.
     * 
     * @return a number base
     */
    int base();

    /**
     * Returns the number of columns of this matrix.
     * 
     * @return the number of columns
     */
    Number columns();

    /**
     * Returns the number of rows of this matrix.
     * 
     * @return the number of rows
     */
    Number rows();

}
