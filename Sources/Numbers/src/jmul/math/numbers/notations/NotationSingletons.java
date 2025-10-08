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

package jmul.math.numbers.notations;


import jmul.math.numbers.notations.regex.NotationRegex;
import jmul.math.numbers.notations.regex.NotationRepository;
import jmul.math.numbers.notations.regex.NotationRepositoryImpl;
import jmul.math.numbers.notations.regex.NotationType;


/**
 * An implementation for a repository singleton.
 *
 * @author Kristian Kutin
 */
public final class NotationSingletons {

    /**
     * A repository for various notations.
     */
    private static final NotationRepository NOTATION_REPOSITORY;

    /*
     * The static initializer.
     */
    static {

        NOTATION_REPOSITORY = new NotationRepositoryImpl();
    }

    /**
     * The default constructor.
     */
    private NotationSingletons() {

        throw new UnsupportedOperationException();
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
    public static NotationRegex notationRegex(NotationType notationType, int base) {

        return NOTATION_REPOSITORY.notationRegex(notationType, base);
    }

}
