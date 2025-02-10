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

package jmul.resource;


/**
 * This interface describes a memory monitor.
 *
 * @author Kristian Kutin
 */
public interface MemoryMonitor {

    /**
     * Returns the maximum amount of memory that the JVM can use (see memory settings when
     * starting the java program).
     *
     * @return the maximum amount of memory in bytes
     */
    long maxMemory();

    /**
     * Returns the current maximum amount of memory available within the JVM (current max memory &lt; max memory).
     *
     * @return the current maximum amount of memory in bytes
     */
    long currentMaxMemory();

    /**
     * Returns the current amount of free memory available within the JVM (current free memory &lt; free memory).
     *
     * @return the current amount of free memory in bytes
     */
    long currentFreeMemory();

    /**
     * Returns the amount of free memory available for the JVM.
     *
     * @return the amount of free memory in bytes
     */
    long freeMemory();

    /**
     * Returns the amount of used memory within the JVM.
     *
     * @return the amount of used memory in bytes
     */
    long usedMemory();

    /**
     * Checks if the mass memory allocation should be stopped or not. Have memory intense components
     * regularly check this in order to not block too much memory.
     *
     * @return <code>true</code> if the mass memory allocation should be stopped, else <code>false</code>
     */
    boolean stopMassMemoryAllocation();

}
