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

package jmul.math.functions.implementations.equality;


import jmul.math.functions.Function;
import jmul.math.indices.IndexSingletons;
import jmul.math.matrices.Matrix;
import jmul.math.numbers.Number;
import jmul.math.operations.EqualityFunction;


/**
 * An implementation of an equality comparator for matrices.
 *
 * @author Kristian Kutin
 */
public class MatrixEquality implements Function, EqualityFunction<Matrix> {

    /**
     * The default constructor.
     */
    public MatrixEquality() {

        super();
    }

    /**
     * Compares the two specified matrices regarding equality.
     *
     * @param m1
     *        a matrix
     * @param m2
     *        a matrix
     *
     * @return <code>true</code> if both matrices are considered equal, else <code>false</code>
     */
    @Override
    public boolean equals(Matrix m1, Matrix m2) {

        // Check the references

        if ((m1 == null) && (m2 == null)) {

            return true;
        }

        if ((m1 == null) || (m2 == null)) {

            return false;
        }

        if (m1 == m2) {

            return true;
        }

        // Check the number bases

        if (m1.base() != m2.base()) {

            return false;
        }

        // Check the sizes

        if (!m1.columns().equals(m2.columns())) {

            return false;
        }

        if (!m1.rows().equals(m2.rows())) {

            return false;
        }

        // check the components

        int base = m1.base();
        Number columns = m1.columns();
        Number rows = m1.rows();

        final Number firstIndex = IndexSingletons.firstIndex(base);

        for (Number columnIndex = firstIndex; columnIndex.isLesserOrEqual(columns);
             columnIndex = IndexSingletons.nextIndex(columnIndex)) {

            for (Number rowIndex = firstIndex; rowIndex.isLesserOrEqual(rows);
                 rowIndex = IndexSingletons.nextIndex(rowIndex)) {

                Number component1 = m1.component(columnIndex, rowIndex);
                Number component2 = m2.component(columnIndex, rowIndex);

                if (!component1.equals(component2)) {

                    return false;
                }
            }
        }

        return true;
    }

}
