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

package jmul.math.numbers.notations.regex;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.hash.HashHelper;
import jmul.math.operations.implementations.ParameterCheckHelper;


/**
 * A repository for regex expressions.
 *
 * @author Kristian Kutin
 */
public class NotationRepositoryImpl implements NotationRepository {

    /**
     * The actual data structure that contains all regex expressions.
     */
    private Map<NotationKey, NotationRegex> notationMap;

    /**
     * The default constructor.
     */
    public NotationRepositoryImpl() {

        super();

        this.notationMap = new ConcurrentHashMap<>();
    }

    /**
     * Returns a regex according to the specified parameters.
     *
     * @param notationType
     *        a notation type
     * @param base
     *        a number base
     *
     * @return a regex
     */
    @Override
    public NotationRegex notationRegex(NotationType notationType, int base) {

        NotationKey key = new NotationKey(notationType, base);

        NotationRegex regex = notationMap.get(key);
        if (regex == null) {

            String allowedDigits = PositionalNumeralSystems.allowedDigitsRegex(base);
            String allowedDigitsWithoutZero = PositionalNumeralSystems.allowedDigitsWithoutZeroRegex(base);

            regex = createRegex(notationType, base, allowedDigits, allowedDigitsWithoutZero);
            notationMap.put(key, regex);
        }

        return regex;
    }

    /**
     * Creates an entity which contains a regex according to the specified parameters.
     *
     * @param notationType
     *        a notation type
     * @param base
     *        a number base
     * @param allowedDigits
     *        a regex snippet with all allowed digits
     * @param allowedDigitsWithoutZero
     *        a regex snippet with a subset of allowed digits
     *
     * @return a notation regex
     */
    private static NotationRegex createRegex(NotationType notationType, int base, String allowedDigits,
                                             String allowedDigitsWithoutZero) {

        if (NotationTypes.STANDARD_NOTATION.equals(notationType)) {

            return new StandardNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        }

        if (NotationTypes.SCIENTIFIC_NOTATION.equals(notationType)) {

            return new ScientificNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        }

        String message = String.format("An unknown notation type (%s) was specified!", notationType);
        throw new IllegalArgumentException(message);
    }

    /**
     * Returns the size of the repsoitory.
     *
     * @return a size
     */
    @Override
    public int size() {

        return notationMap.size();
    }

}


/**
 * A key class that contains various properties.
 *
 * @author Kristian Kutin
 */
class NotationKey {

    /**
     * A notation type.
     */
    public final NotationType notationType;

    /**
     * a number base.
     */
    public final int base;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param notationType
     *        a notation type
     * @param base
     *        a number base
     */
    public NotationKey(NotationType notationType, int base) {

        super();

        if (notationType == null) {

            throw new IllegalArgumentException("No notation type (null) was specified!");
        }

        ParameterCheckHelper.checkNumberBase(base);

        this.notationType = notationType;
        this.base = base;
    }

    /**
     * Checks the equality of this object and the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if both objects are considered to be equal, esle <code>false</code>
     */
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof NotationKey) {

            NotationKey other = (NotationKey) o;

            return this.notationType.equals(other.notationType) && (this.base == other.base);
        }

        return false;
    }

    /**
     * Calculates a hash code for this object.
     *
     * @return a hash code
     */
    public int hashCode() {

        return HashHelper.calculateHashCode(NotationKey.class, notationType, base);
    }

}
