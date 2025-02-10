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

package jmul.concurrent.threads;


/**
 * A utility class for starting threads.
 */
public final class ThreadStartHelper {

    /**
     * A sleep time.
     */
    private static final long DEFAULT_SLEEP_TIME;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_SLEEP_TIME = 10L;
    }

    /**
     * The default constructor.
     */
    private ThreadStartHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a thread with the specified runnable and starts it.
     *
     * @param runnable
     *        a class that implements the Runnable interface
     *
     * @return the thread
     */
    public static Thread startThread(Runnable runnable) {

        if (runnable == null) {

            throw new IllegalArgumentException("No runnable (null) was specified!");
        }

        Thread thread = new Thread(runnable);

        return startThread(thread);
    }

    /**
     * Creates a thread with the specified runnable and starts it.
     *
     * @param thread
     *        a thread object
     *
     * @return the specified thread
     */
    public static Thread startThread(Thread thread) {

        if (thread == null) {

            throw new IllegalArgumentException("No thread (null) was specified!");
        }

        if (thread.isAlive() || thread.isInterrupted()) {

            throw new IllegalArgumentException("The thread is already running!");
        }

        thread.start();

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        return thread;
    }

}
