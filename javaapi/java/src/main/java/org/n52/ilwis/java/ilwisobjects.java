/* 
Copyright (C) 2007 - 2015 52°North Initiative for Geospatial Open Source
Software GmbH

This program is free software; you can redistribute it and/or modify it
under the terms of the GNU General Public License version 2 as published
by the Free Software Foundation.

If the program is linked with libraries which are licensed under one of
the following licenses, the combination of the program with the linked
library is not considered a "derivative work" of the program:

      • Apache License, version 2.0
      • Apache Software License, version 1.0
      • GNU Lesser General Public License, version 3
      • Mozilla Public License, versions 1.0, 1.1 and 2.0
      • Common Development and Distribution License (CDDL), version 1.0

Therefore the distribution of the program linked with libraries licensed
under the aforementioned licenses, is permitted by the copyright holders
if the distribution is compliant with both the GNU General Public
License version 2 and the aforementioned licenses.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
Public License for more details. 
*/ 
/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.n52.ilwis.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ilwisobjects {
  public static boolean _initIlwisObjects(String ilwisDir) {
    return ilwisobjectsJNI._initIlwisObjects(ilwisDir);
  }

  public static void _exitIlwisObjects() {
    ilwisobjectsJNI._exitIlwisObjects();
  }

  public static void disconnectIssueLogger() {
    ilwisobjectsJNI.disconnectIssueLogger();
  }

  public static void connectIssueLogger() {
    ilwisobjectsJNI.connectIssueLogger();
  }

  public static int getCOVERAGEATRIB() {
    return ilwisobjectsJNI.COVERAGEATRIB_get();
  }

  public static double getRUNDEF() {
    return ilwisobjectsJNI.rUNDEF_get();
  }

  public static int getIUNDEF() {
    return ilwisobjectsJNI.iUNDEF_get();
  }

  public static long getI64UNDEF() {
    return ilwisobjectsJNI.i64UNDEF_get();
  }


	public static String getsUNDEF() {
		return "?";
	}

	public static int getshUNDEF() {
		return 32767;
	}

	public static float getFlUNDEF() {
		return (float) 1e38;
	}

	private static boolean libLoaded = false;
	private static String ilwisLocation = getIlwisLocation();

	/**
	 * Use before any Ilwis fuction. Set the Ilwis folder with setIlwisLocation,
	 * if no ilwislocation.config is in the resources.
	 * 
	 * @throws FileNotFoundException
	 *             Can't find Ilwis-Objects.
	 */
	public static void initIlwisObjects() throws FileNotFoundException {
		if (!libLoaded) {
			if (ilwisLocation == null) {
				ilwisLocation = readIlwisLocation();
			}
			if (ilwisLocation == null) {
				throw new FileNotFoundException(
						"Ilwis location not set and ilwislocation.config not found or not well-formed.");
			}
			if (System.getProperty("os.name").toLowerCase().contains("win")) { // Windows
				System.load(ilwisLocation + "extensions" + File.separator
						+ "_ilwisobjects" + File.separator
						+ "_ilwisobjects0.dll");
			} else { // Linux
				System.load(ilwisLocation + "extensions/_ilwisobjects/lib_ilwisobjects.so");
			}

			ilwisobjects._initIlwisObjects(ilwisLocation);

			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					ilwisobjects._exitIlwisObjects();
				}
			}));
			libLoaded = true;
		}
	}

	private static String readIlwisLocation() {
		BufferedReader br = null;
		// default location
		URL input = ClassLoader.getSystemResource("ilwislocation.config");
		if (input == null)
			return null;
		try {
			String line;

			br = new BufferedReader(new InputStreamReader(input.openStream()));

			while ((line = br.readLine()) != null) {
				if (line.indexOf("=") == -1) {
					continue;
				}
				String[] lineSplit = line.split("=");
				if (lineSplit[0].equals("ilwisDir")) {
					return (lineSplit[1]);
				}
			}
		} catch (IOException e) {
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
			}
		}
		return null;
	}

	public static String getIlwisLocation() {
		return ilwisLocation;
	}

	/**
	 * Set before initIlwisObjects!
	 * 
	 * @param location
	 *            Ilwis-Objects location eg.
	 *            G:/IlwisObjects/output/win32release/bin/
	 */
	public static void setIlwisLocation(String location) {
		ilwisLocation = location;
	}
  

}
