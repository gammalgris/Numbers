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

package jmul.math.concurrent;

import jmul.concurrent.threads.ThreadHelper;


/**
 * A base class for concurrent calculations.
 *
 * @param <T>
 *        the input type
 * @param <S>
 *        the result type
 *
 * @author Kristian Kutin
 */
public abstract class ConcurrentCalculation<T, S> implements Runnable {

    /**
     * The input for the calculation.
     */
    private final T input;

    /**
     * The result of the calculation.
     */
    private volatile S result;

    /**
     * A flag indicating that this calculation has not finished.
     */
    private volatile boolean running;

    /**
     * An exception that may occur.
     */
    private volatile Throwable exception;

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param input
     *        an input
     */
    public ConcurrentCalculation(T input) {

        super();

        this.input = input;
        this.result = null;
        this.running = false;
        this.exception = null;
    }

    /**
     * Checks if this calculation has finished.
     *
     * @return <code>true</code> if this calculation has not finished, else <code>false</code>
     */
    public boolean isRunning() {

        synchronized (this) {

            return running;
        }
    }

    /**
     * Returns the calculation result. If no result is available an exception is thrown. Before calling this method,
     * check if the calculation is running or check if the calculation has failed.
     *
     * @return the calculation result
     */
    public S result() {

        synchronized (this) {

            if (result == null) {

                throw new NoConcurrentResultException();
            }

            return result;
        }
    }

    /**
     * The concurrent calculation (i.e. a wrapper which handles also the exception handling).
     */
    @Override
    public void run() {

        running = true;

        try {

            result = calculate(input);

        } catch (Exception e) {

            result = null;
            exception = e;
        }

        running = false;
    }

    /**
     * The actual calcualtion.
     *
     * @param input
     *        an input
     *
     * @return the result
     */
    protected abstract S calculate(T input);

    /**
     * Checks if this concurrent calculation has failed.
     *
     * @return <code>true</code> if this concurrent calculation has failed, else <code>false</code>
     */
    public boolean hasFailed() {

        synchronized (this) {

            if (running) {

                return false;

            } else {

                return exception != null;
            }
        }
    }

    /**
     * Returns an exception that may have occurred during the concurrent calculation. If no exception was thrown an
     * exception is thrown. Before calling this method, check if the calculation is running or check if the calculation
     * has failed.
     *
     * @return an exception
     */
    public Throwable exception() {

        synchronized (this) {

            if (exception == null) {

                throw new NoConcurrentExceptionException();
            }

            return exception;
        }
    }

}
