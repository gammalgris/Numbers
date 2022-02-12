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

package jmul.math.numbers;


import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.operations.ArithmeticOperations;


public abstract class NumberBuilder<T extends Digit> extends AbstractSignedNumber<T> implements ArithmeticOperations<DigitSequence<T>> {

    public NumberBuilder() {
        super(Signs.NEGATIVE);
    }

    @Override
    public DigitSequence<T> add(DigitSequence<T> n, DigitSequence<T> m) {
        // TODO Implement this method
        return null;
    }

    @Override
    public DigitSequence<T> subtract(DigitSequence<T> n, DigitSequence<T> m) {
        // TODO Implement this method
        return null;
    }

    @Override
    public DigitSequence<T> multiply(DigitSequence<T> n) {
        // TODO Implement this method
        return null;
    }

    @Override
    public DigitSequence<T> divide(DigitSequence<T> n) {
        // TODO Implement this method
        return null;
    }

    @Override
    public DigitSequence<T> modulo(DigitSequence<T> n) {
        // TODO Implement this method
        return null;
    }

    @Override
    public int leftDigits() {
        // TODO Implement this method
        return 0;
    }

    @Override
    public int rightDigits() {
        // TODO Implement this method
        return 0;
    }

    @Override
    public int digits() {
        // TODO Implement this method
        return 0;
    }

    @Override
    public T digitAt(int anIndex) {
        // TODO Implement this method
        return null;
    }

    @Override
    public String toScientificNotation() {
        // TODO Implement this method
        return null;
    }

    @Override
    public String toStandardNotation() {
        // TODO Implement this method
        return null;
    }

    @Override
    public boolean isInfinity() {
        // TODO Implement this method
        return false;
    }

    @Override
    public int compareTo(DigitSequence<T> o) {
        // TODO Implement this method
        return 0;
    }

}
