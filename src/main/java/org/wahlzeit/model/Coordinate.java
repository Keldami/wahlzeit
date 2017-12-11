/*
 * Copyright (c) 2017-2018 by Artur Wasinger
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;


/**
 * Interface for generic Coordinate classes
 */
public interface Coordinate {


    /**
     * converts the current instance to {@link CartesianCoordinate}
     * @see #asSphericCoordinate()
     * @return instance of CarteisanCoordinate
     */
    CartesianCoordinate asCartesianCoordinate();


    /**
     * Converts current instance and other to {@link CartesianCoordinate}
     * Calculates and returns their euclidean distance
     * @param other
     * @return distance as double
     */
    double getCartesianDistance(Coordinate other);

    /**
     * converts the current instance to {@link SphericCoordinate}
     * @see #asCartesianCoordinate()
     *
     * @return instance of SphericCoordinate
     */
    SphericCoordinate asSphericCoordinate();

    /**
     * Converts current instance and other to {@link SphericCoordinate}
     * Calculates and returns their spheric distance
     * @see <a hfref="https://en.wikipedia.org/wiki/Great-circle_distance#Computational_formulas" a>
     *     https://en.wikipedia.org/wiki/Great-circle_distance#Computational_formulas
     *     </a>
     * @param other
     * @return distance as double
     */
    double getSphericDistance(Coordinate other);

    /**
     * Calculates the distance of given Coordinate-Type
     * Get's the distance to another coordinate from the current locations coordinate
     * @see #getSphericDistance(Coordinate)
     * @see #getCartesianDistance(Coordinate)
     * @param other
     * @return distance as double
     */
    double getDistance(Coordinate other);

    /**
     * @param other
     * @return returns true if location and type is equal to other else returns false
     */
    boolean isEqual(Coordinate other);

}
