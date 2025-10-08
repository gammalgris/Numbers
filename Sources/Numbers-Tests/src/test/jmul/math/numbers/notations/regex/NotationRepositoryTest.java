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

package test.jmul.math.numbers.notations.regex;


import jmul.math.numbers.notations.regex.NotationRegex;
import jmul.math.numbers.notations.regex.NotationRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import jmul.math.numbers.notations.regex.NotationRepositoryImpl;

import jmul.math.numbers.notations.regex.NotationTypes;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This test suite tests creating a regex repository.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class NotationRepositoryTest {

    /**
     * Test creating a new repository.
     */
    @Test
    public void testCreatingRepository() {

        NotationRepository repository = new NotationRepositoryImpl();

        assertEquals("#size", 0, repository.size());
    }

    /**
     * Test adding an element.
     */
    @Test
    public void testAddElement() {

        NotationRepository repository = new NotationRepositoryImpl();

        NotationRegex regex = repository.notationRegex(NotationTypes.STANDARD_NOTATION, 10);

        assertNotNull("#element", regex);
        assertEquals("#size", 1, repository.size());

    }

}
