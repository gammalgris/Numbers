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

package jmul.math.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


/**
 * A container for training data.
 *
 * @author Kristian Kutin
 */
public final class TrainingData implements Iterable<DataEntry> {

    /**
     * A number base.
     */
    private final int base;

    /**
     * The actual data container.
     */
    private final List<DataEntry> trainingData;

    /**
     * Creates a new data container according to the specified parameters.
     *
     * @param entries
     *        all data entries
     */
    public TrainingData(DataEntry... entries) {

        super();

        if (entries == null) {

            throw new IllegalArgumentException("No data entries (null) were specified!");
        }

        if (entries.length == 0) {

            throw new IllegalArgumentException("No data entries (empty array) were specified!");
        }

        this.base = entries[0].base;
        this.trainingData = Arrays.asList(entries);
    }

    /**
     * Creates a new data container according to the specified parameters.
     *
     * @param entries
     *        all data entries
     */
    public TrainingData(Stream<DataEntry> entries) {

        super();

        if (entries == null) {

            throw new IllegalArgumentException("No data entries (null) were specified!");
        }

        this.trainingData = new ArrayList<>();

        DataEntry entry = null;
        Iterator<DataEntry> iterator = entries.iterator();
        if (!iterator.hasNext()) {

            throw new IllegalArgumentException("No data entries (empty stream) were specified!");
        }

        entry = iterator.next();

        this.base = entry.base;
        this.trainingData.add(entry);

        while (iterator.hasNext()) {

            entry = iterator.next();
            this.trainingData.add(entry);
        }
    }

    /**
     * Returns an iterator to iterate thrugh this data container.
     *
     * @return an iterator
     */
    @Override
    public Iterator<DataEntry> iterator() {

        return this.trainingData.iterator();
    }

    /**
     * Returns the number of data entries.
     *
     * @return the number of data entries
     */
    public int size() {

        return this.trainingData.size();
    }

    /**
     * Returns a summary of the training data.
     *
     * @return a summary of the training data
     */
    @Override
    public String toString() {

        return this.trainingData.toString();
    }


    /**
     * Returns the data as stream.
     *
     * @return a stream
     */
    public Stream<DataEntry> stream() {

        return this.trainingData.stream();
    }

}
