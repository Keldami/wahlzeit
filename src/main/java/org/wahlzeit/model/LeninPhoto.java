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


import com.googlecode.objectify.annotation.Subclass;


/**
 * specific implementation of photo class
 * @see {@link LeninPhotoFactory}
 * @see {@link LeninPhotoManager}
 * @see {@link Photo}
 */
@Subclass
public class LeninPhoto extends Photo {

    private Lenin typeInstance = null;

    public LeninPhoto() {
        super();
    }

    public LeninPhoto(PhotoId id) {
        super(id);
    }

    /**
     * Set's the type instance for a LeninPhoto
     * @param typeInstance
     */
    public void setTypeInstance(Lenin typeInstance) {
        this.typeInstance = typeInstance;
    }

    public Lenin getType() {
        return typeInstance;
    }
}
