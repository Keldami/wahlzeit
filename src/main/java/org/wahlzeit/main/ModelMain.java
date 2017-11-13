/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
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

package org.wahlzeit.main;

import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.services.LogBuilder;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * A single-threaded Main class with database connection. Can be used by tools that don't want to start a server.
 */
public abstract class ModelMain extends AbstractMain {

	private static final Logger log = Logger.getLogger(ModelMain.class.getName());

	/**
	 *
	 */
	protected void startUp(String rootDir) throws Exception {
		super.startUp(rootDir);
		log.info("AbstractMain.startUp completed");

		log.config(LogBuilder.createSystemMessage().addAction("load image storage").toString());
		//GcsAdapter.Builder gcsAdapterBuilder = new GcsAdapter.Builder();
		ImageStorage.setInstance(new DatastoreAdapter());

		log.config(LogBuilder.createSystemMessage().addAction("load globals").toString());
		GlobalsManager.getInstance().loadGlobals();

		log.config(LogBuilder.createSystemMessage().addAction("load user").toString());
		UserManager.getInstance().init();

		log.config(LogBuilder.createSystemMessage().addAction("init PhotoFactory").toString());
		PhotoFactory.initialize();

		log.config(LogBuilder.createSystemMessage().addAction("init LeninPhotoFactory").toString());
		LeninPhotoFactory.initialize();

		log.config(LogBuilder.createSystemMessage().addAction("load Photos").toString());
		PhotoManager.getInstance().init();

		log.config(LogBuilder.createSystemMessage().addAction("load LeninPhotos").toString());
		LeninPhotoManager.getInstance().init();
	}


	/**
	 *
	 */
	protected void shutDown() throws Exception {
		saveAll();

		super.shutDown();
	}

	/**
	 *
	 */
	public void saveAll() throws IOException{
		PhotoCaseManager.getInstance().savePhotoCases();
		PhotoManager.getInstance().savePhotos();
		UserManager.getInstance().saveClients();
		GlobalsManager.getInstance().saveGlobals();
	}

	/**
	 *
	 */
	protected void createUser(String userId, String nickName, String emailAddress, String photoDir) throws Exception {
		UserManager userManager = UserManager.getInstance();
		new User(userId, nickName, emailAddress);

		PhotoManager photoManager = PhotoManager.getInstance();
		File photoDirFile = new File(photoDir);
		FileFilter photoFileFilter = new FileFilter() {
			public boolean accept(File file) {
				//TODO: check and change
				return file.getName().endsWith(".jpg");
			}
		};

		File[] photoFiles = photoDirFile.listFiles(photoFileFilter);
		for (int i = 0; i < photoFiles.length; i++) {
			//TODO: change to datastore/cloud storage
			//Photo newPhoto = photoManager.createPhoto(photoFiles[i]);
			//user.addPhoto(newPhoto);
		}
	}
}
