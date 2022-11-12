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

package jmul.math.numbers.functions;


import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.notations.ScientificNotationFunctionImpl;
import jmul.math.numbers.notations.ScientificNotationParserImpl;
import jmul.math.numbers.notations.StandardNotationFunctionImpl;
import jmul.math.numbers.notations.StandardNotationParserImpl;

import jmul.singletons.FunctionRepository;
import jmul.singletons.FunctionRepositoryImpl;
import jmul.singletons.FunctionRepositoryInitializer;


/**
 * An implementation of a function repository initializer.
 *
 *  @author Kristian Kutin
 */
public class FunctionRepositoryInitializerImpl implements FunctionRepositoryInitializer {

    /**
     * The default constructor.
     */
    public FunctionRepositoryInitializerImpl() {

        super();
    }

    /**
     * Returns an initialized function repository.
     *
     * @return a function repository
     */
    public FunctionRepository init() {

        FunctionRepository repository = new FunctionRepositoryImpl();

        repository.registerFunction(FunctionIdentifiers.DIGIT_COMPARATOR_FUNCTION, DigitComparatorFunctionImpl.class);
        repository.registerFunction(FunctionIdentifiers.DIGIT_EQUALITY_FUNCTION, DigitEqualityFunctionImpl.class);

        repository.registerFunction(FunctionIdentifiers.STANDARD_NOTATION_PARSER, StandardNotationParserImpl.class);
        repository.registerFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_PARSER, ScientificNotationParserImpl.class);

        repository.registerFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_FUNCTION,
                                    ScientificNotationFunctionImpl.class);
        repository.registerFunction(FunctionIdentifiers.STANDARD_NOTATION_FUNCTION, StandardNotationFunctionImpl.class);

        repository.registerFunction(FunctionIdentifiers.NEGATE_NUMBER_FUNCTION, NegateNumberFunctionImpl.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_COMPLEMENT_FUNCTION, NumberComplementFunctionImpl.class);

        repository.registerFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION, NumberComparatorFunctionImpl.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_EQUALITY_FUNCTION, NumberEqualityFunctionImpl.class);

        repository.registerFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION, AddDigitsFunctionImpl.class);
        repository.registerFunction(FunctionIdentifiers.DIGIT_COMPLEMENT_FUNCTION, DigitComplementFunctionImpl.class);

        repository.registerFunction(FunctionIdentifiers.ADDITION_FUNCTION, AddNumbersFunctionImpl.class);

        return repository;
    }

}
