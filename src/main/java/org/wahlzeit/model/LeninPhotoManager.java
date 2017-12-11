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


import java.util.logging.Logger;


/**
 * Manager class for LeninPhotos
 * @see {@link LeninPhoto}
 * @see {@link LeninPhotoFactory}
 * @see {@link PhotoManager}
 */
public class LeninPhotoManager extends PhotoManager {

    protected static LeninPhotoManager instance = new LeninPhotoManager();

    private static final Logger log = Logger.getLogger(LeninPhotoManager.class.getName());

    public static LeninPhotoManager getInstance() {
        return instance;
    }

}
