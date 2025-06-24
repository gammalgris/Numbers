package jmul.math.functions.implementations;


import java.util.LinkedList;
import java.util.Queue;

import jmul.math.indices.IndexSingletons;
import jmul.math.matrices.Matrix;
import jmul.math.matrices.MatrixImpl;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.signs.Signs;


/**
 * An implementation of a function that multiplies two matrices.
 *
 * @author Kristian Kutin
 */
public class MatrixMultiplicationFunction implements BinaryOperation<Matrix, Result<Matrix>> {

    /**
     * The default constructor.
     */
    public MatrixMultiplicationFunction() {

        super();
    }

    /**
     * Multiplies the specified matrices.
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

        ParameterCheckHelper.checkMatricesForMultiplication(matrix1, matrix2);

        int base = matrix1.base();

        Number firstIndex = IndexSingletons.firstIndex(base);

        Number rows1 = matrix1.rows();
        Number columns2 = matrix2.columns();

        Queue<Number> results = new LinkedList<Number>();

        for (Number rowIndex1 = firstIndex; rowIndex1.isLesserOrEqual(rows1);
             rowIndex1 = IndexSingletons.nextIndex(rowIndex1)) {

            for (Number columnIndex2 = firstIndex; columnIndex2.isLesserOrEqual(columns2);
                 columnIndex2 = IndexSingletons.nextIndex(columnIndex2)) {

                Number sum = calculateSum(matrix1, rowIndex1, matrix2, columnIndex2);
                results.add(sum);
            }
        }

        Matrix result = new MatrixImpl(base, columns2, rows1, results.stream());

        return new Result<Matrix>(result);
    }

    /**
     * Calculates a component according to the specified parameters.
     *
     * @param matrix1
     *        a matrix
     * @param row
     *        a row in the first matrix
     * @param matrix2
     *        a matrix
     * @param column
     *        a column in the second matrix
     *
     * @return the sum of the specified column and row (i.e. sum of products)
     */
    private Number calculateSum(Matrix matrix1, Number row, Matrix matrix2, Number column) {

        int base = matrix1.base();
        Number firstIndex = IndexSingletons.firstIndex(base);
        final Number ZERO = createNumber(Signs.POSITIVE, base, 0);

        Number sum = ZERO;

        for (Number commonIndex = firstIndex; commonIndex.isLesserOrEqual(matrix1.columns());
             commonIndex = IndexSingletons.nextIndex(commonIndex)) {

            Number number1 = matrix1.component(commonIndex, row);
            Number number2 = matrix2.component(column, commonIndex);

            sum = sum.add(number1.multiply(number2));
        }

        return sum;
    }

}
