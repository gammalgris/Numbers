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

package jmul.math.operations.implementations;


import jmul.math.Math;
import jmul.math.digits.Digit;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;

import jmul.metainfo.annotations.Modified;


/**
 * An implementation of a function that rebases a number to a dfferent number base.
 *
 * @author Kristian Kutin
 */
public class RebaseNumber implements MixedBinaryOperation<Number, Integer, Result<Number>> {

    /**
     * The default constructor.
     */
    public RebaseNumber() {

        super();
    }

    /**
     * Translates the specified integer part to a number of the specified new number base.
     *
     * @param operand
     *        a number (i.e. unsigned integer)
     * @param newNumberBase
     *        the new number base
     *
     * @return a reference to a center node of the translated number
     */
    private DigitNode translateIntegerPart(Number operand, Integer newNumberBase) {

        DigitNode centerNode = null;
        DigitNode currentNode = null;
        int base = operand.base();

        BinaryOperation<Number, ResultWithRemainder<Number>> divisionFunction =
            (BinaryOperation<Number, ResultWithRemainder<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_RESULT_AND_REMAINDER_FUNCTION);
        BinaryOperation<Integer, Result<Number>> translateBaseFunction =
            (BinaryOperation<Integer, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.BASE_TO_NUMBER_FUNCTION);
        MixedBinaryOperation<Number, Integer, Result<Digit>> translateDigitFunction =
            (MixedBinaryOperation<Number, Integer, Result<Digit>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_DIGIT_FUNCTION);

        Result<Number> translationResult = translateBaseFunction.calculate(newNumberBase, base);
        Number newBase = translationResult.result();

        Number result = operand;
        while (true) {

            ResultWithRemainder<Number> divisionResult = divisionFunction.calculate(result, newBase);
            result = divisionResult.result();
            Number remainder = divisionResult.remainder();

            Result<Digit> digitResult = translateDigitFunction.calculate(remainder, newNumberBase);
            Digit digit = digitResult.result();

            if (centerNode == null) {

                centerNode = NodesHelper.createNode(digit);
                currentNode = centerNode;

            } else {

                DigitNode newNode = NodesHelper.createNode(digit);
                NodesHelper.linkNodes(newNode, currentNode);
                currentNode = currentNode.leftNode();
            }

            if (result.isZero()) {

                break;
            }
        }

        return centerNode;
    }

    /**
     * Translates the specified fraction part to a number of the specified new number base.
     *
     * @param centerNode
     *        the left half of the translated number. This linked list is modified.
     * @param operand
     *        a number (i.e. unsigned fraction)
     * @param newNumberBase
     *        the new number base
     *
     * @return the sprecified reference to the center node of the translated number
     */
    private DigitNode translateFractionPart(@Modified DigitNode centerNode, Number operand, Integer newNumberBase) {

        int base = operand.base();

        BinaryOperation<Integer, Result<Number>> translateBaseFunction =
            (BinaryOperation<Integer, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.BASE_TO_NUMBER_FUNCTION);
        MixedBinaryOperation<Number, Integer, Result<Digit>> translateDigitFunction =
            (MixedBinaryOperation<Number, Integer, Result<Digit>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_DIGIT_FUNCTION);

        Result<Number> translationResult = translateBaseFunction.calculate(newNumberBase, base);
        Number newBase = translationResult.result();

        final Number ONE = createNumber(base, Signs.POSITIVE, 1);
        Number debug = null;
        Number result = operand;
        DigitNode currentNode = centerNode;
        Number remainingDigits = Math.getDefaultMaximumFractionLength(base);
        while (true) {

            result = result.multiply(newBase);

            if (result.isZero()) {

                break;
            }

            Number integerPart = result.removeFractionPart();
            Number fractionPart = result.removeIntegerPart();

            Result<Digit> digitResult = translateDigitFunction.calculate(integerPart, newNumberBase);
            Digit digit = digitResult.result();

            DigitNode newNode = NodesHelper.createNode(digit);
            NodesHelper.linkNodes(currentNode, newNode);
            currentNode = currentNode.rightNode();

            if (result.isGreaterOrEqual(ONE)) {

                result = fractionPart;
            }

            if (!remainingDigits.isZero()) {

                remainingDigits = remainingDigits.dec();
            }

            if (remainingDigits.isZero() && !currentNode.digit().isZero()) {

                break;
            }
        }

        return centerNode;
    }

    /**
     * Rebases the specified number to a new number base.<br>
     * <br>
     * <i>Note:<br>
     * The specified number is split into an integer part and fraction part. First the integer part
     * is used to build the rebased integer part. Then the fraction part is used to build the
     * rebased fraction part. The new number base is specified in decimal and needs to be translated
     * to a number of the current number base for further calculations.<br>
     * <br>
     * Rebasing the integer part:</i><br>
     * <br>
     * <ol>
     * <li><i>Divide the number by the new number base and keep the result and remainder.</i></li>
     * <li><i>The result is divided by the new number base and repeated until the result is zero. Keep the remainders.</i></li>
     * <li><i>The remainders of each division are taken to build the rebased integer part.</i></li>
     * </ol>
     * <br>
     * <i>Rebasing the fraction part:</i><br>
     * <ol>
     * <li><i>Multiply the number with the new number base.</i></li>
     * <li><i>The integer part of the result is taken to determine the next digit of the rebased fraction part.</i></li>
     * <li><i>If the result is greater than one then keep the fractional part. The integer part represents the next digit of the rebased fraction.</i></li>
     * <li><i>Take the result and go to step one. Repat this until the result is zero.</i></li>
     * </ol>
     *
     * @param operand
     *        a number
     * @param newNumberBase
     *        the new number base (decimal)
     *
     * @return a rebased number
     */
    @Override
    public Result<Number> calculate(Number operand, Integer newNumberBase) {

        ParameterCheckHelper.checkParameter(operand);
        ParameterCheckHelper.checkNumberBase(newNumberBase);

        int base = operand.base();
        if (base == newNumberBase) {

            Number clone = NumberHelper.createNumber(CLONE, operand);
            return new Result<Number>(clone);
        }

        Sign sign = operand.sign();

        if (operand.isInfinity()) {

            Number infinity = createInfinity(newNumberBase, sign);
            return new Result<Number>(infinity);
        }

        Number absoluteValue = operand.absoluteValue();

        Number integerPart = absoluteValue.removeFractionPart();
        Number fractionPart = absoluteValue.removeIntegerPart();

        DigitNode translatedResult = translateIntegerPart(integerPart, newNumberBase);
        translatedResult = translateFractionPart(translatedResult, fractionPart, newNumberBase);

        Number result = createNumber(newNumberBase, sign, translatedResult);

        return new Result<Number>(result);
    }

}
