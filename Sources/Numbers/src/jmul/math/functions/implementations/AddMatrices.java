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

import jmul.math.matrices.Matrix;
import jmul.math.matrices.MatrixImpl;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.indices.IndexSingletons;


/**
 * An implementation of a function that adds two matrices.
 *
 * @author Kristian Kutin
 */
public class AddMatrices implements BinaryOperation<Matrix, Result<Matrix>> {


    /**
     * The default constructor.
     */
    public AddMatrices() {

        super();
    }

    /**
     * Adds the specified matrices and returns the result.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     *
     * @return a matrix
     */
    @Override
    public Result<Matrix> calculate(Matrix matrix1, Matrix matrix2) {

        ParameterCheckHelper.checkMatrices(matrix1, matrix2);

        int base = matrix1.base();

        Number firstIndex = IndexSingletons.firstIndex(base);
        Number columns = matrix1.columns();
        Number rows = matrix1.rows();

        Queue<Number> results = new LinkedList<Number>();

        for (Number rowIndex = firstIndex; rowIndex.isLesserOrEqual(rows);
             rowIndex = IndexSingletons.nextIndex(rowIndex)) {

            for (Number columnIndex = firstIndex; columnIndex.isLesserOrEqual(columns);
                 columnIndex = IndexSingletons.nextIndex(columnIndex)) {


                Number component1 = matrix1.component(columnIndex, rowIndex);
                Number component2 = matrix2.component(columnIndex, rowIndex);

                Number sum = component1.add(component2);

                results.add(sum);
            }
        }

        Matrix result = new MatrixImpl(base, columns, rows, results.stream());

        return new Result<Matrix>(result);
    }

}
