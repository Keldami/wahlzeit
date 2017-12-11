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


/*
 * Interface for generic Coordinate classes
 */
public interface Coordinate {

    /*
     *
     *
     */
    // @methodtype interpreter method for CartesianCoordinates
    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate other);


    // @methodtype interpreter method for SphericCoordinates
    SphericCoordinate asSphericCoordinate();

    double getSphericDistance(Coordinate other);


    //get's the distance to another coordinate
    double getDistance(Coordinate other);

    //returns true if location and type is equal to other
    //else returns false
    boolean isEqual(Coordinate other);

}
