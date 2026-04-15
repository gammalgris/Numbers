/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2026  Kristian Kutin
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

package jmul.math.equations.tree;


/**
 * An implementation of an expression tree.
 *
 * @author Kristian Kutin
 */
class ExpressionTreeImpl implements ExpressionTree {

    /**
     * A reference to a root node or <code>null</code>.
     */
    private ExpressionNode root;

    /**
     * The default constructor.
     */
    public ExpressionTreeImpl() {

        super();

        this.root = null;
    }

    /**
     * A reference to a root node or <code>null</code>.
     *
     * @return a reference to a root node or <code>null</code>
     */
    @Override
    public ExpressionNode root() {

        return root;
    }

    /**
     * Sets the root node.
     *
     * @param node
     *        an expression node or <code>null</code>
     */
    public void setRoot(ExpressionNode node) {

        root = node;
    }

}
