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

package jmul.math.functions.implementations;


import java.util.LinkedList;
import java.util.Queue;

import jmul.math.indices.IndexSingletons;
import jmul.math.matrices.Matrix;
import jmul.math.matrices.MatrixImpl;
import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * An implementation of a fucntion to transpose a matrix.
 *
 * @author Kristian Kutin
 */
public class TransposeMatrixFunction implements UnaryOperation<Matrix, Result<Matrix>> {

    /**
     * The default constructor.
     */
    public TransposeMatrixFunction() {

        super();
    }

    /**
     *Calculates the transpose of the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return a transposed matrix
     */
    @Override
    public Result<Matrix> calculate(Matrix matrix) {

        ParameterCheckHelper.checkParameter(matrix);

        int base = matrix.base();

        Number firstIndex = IndexSingletons.firstIndex(base);
        Number columns = matrix.columns();
        Number rows = matrix.rows();

        Queue<Number> results = new LinkedList<Number>();

        for (Number columnIndex = firstIndex; columnIndex.isLesserOrEqual(columns);
             columnIndex = IndexSingletons.nextIndex(columnIndex)) {

            for (Number rowIndex = firstIndex; rowIndex.isLesserOrEqual(rows);
                 rowIndex = IndexSingletons.nextIndex(rowIndex)) {

                Number component = matrix.component(columnIndex, rowIndex);

                results.add(component);
            }
        }

        Matrix result = new MatrixImpl(base, rows, columns, results.stream());

        return new Result<Matrix>(result);
    }

}
