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

package jmul.math.indices;


import java.util.HashMap;
import java.util.Map;

import jmul.math.functions.implementations.ParameterCheckHelper;
import jmul.math.numbers.Number;


/**
 * A helper class for managing index numbers.
 *
 * @author Kristian Kutin
 */
public class IndexSingletons {

    /**
     * A singleton for managing index numbers.
     */
    private static Map<Integer, IndexRepository> SINGLETONS;

    /*
     * The static initializer.
     */
    static {

        SINGLETONS = new HashMap<>();
    }

    /**
     * The default constructor.
     */
    private IndexSingletons() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the next index to the specified index.
     *
     * @param index
     *        an index (i.e. a positive integer which is not zero)
     *
     * @return the next index
     */
    public static Number nextIndex(Number index) {

        Number nextIndex;
        synchronized (SINGLETONS) {

            ParameterCheckHelper.checkParameter(index);

            int base = index.base();

            IndexRepository indices = SINGLETONS.get(base);

            if (indices == null) {

                indices = createNewIndexRepository(base);
            }

            nextIndex = indices.nextIndex(index);
        }

        return nextIndex;
    }

    /**
     * Returns the first index.
     *
     * @param base
     *        a number base
     *
     * @return the first index
     */
    public static Number firstIndex(int base) {

        Number firstIndex;
        synchronized (SINGLETONS) {

            ParameterCheckHelper.checkNumberBase(base);

            IndexRepository indices = SINGLETONS.get(base);

            if (indices == null) {

                indices = createNewIndexRepository(base);
            }

            firstIndex = indices.firstIndex();
        }

        return firstIndex;
    }

    /**
     * Creates a new index repository for the specified number base.
     *
     * @param base
     *        a number base
     *
     * @return the created index repository
     */
    private static IndexRepository createNewIndexRepository(int base) {

        IndexRepository indices = new IndexRepositoryImpl(base);
        SINGLETONS.put(base, indices);

        return indices;
    }

}
