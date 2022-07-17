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


import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import static jmul.math.numbers.Constants.ORDINAL_MAX_LIMIT;
import static jmul.math.numbers.Constants.ORDINAL_MIN_LIMIT;
import jmul.math.numbers.exceptions.IllegalOrdinalException;
import jmul.math.numbers.exceptions.UnsupportedBaseException;


public final class ParameterHelper {

    private ParameterHelper() {

        throw new UnsupportedOperationException();
    }

    public static int checkBase(int base) {

        if ((base < BASE_MIN_LIMIT) || (base > BASE_MAX_LIMIT)) {

            throw new UnsupportedBaseException(base);
        }

        return base;
    }

    public static int checkOrdinal(int ordinal) {

        if ((ordinal < ORDINAL_MIN_LIMIT) || (ordinal > ORDINAL_MAX_LIMIT)) {

            throw new IllegalOrdinalException(ordinal);
        }

        return ordinal;
    }

    public static Sign checkSign(Sign sign) {

        if (sign == null) {

            String message = "No valid sign was specified (null)!";
            throw new IllegalArgumentException(message);
        }

        return sign;
    }

}
