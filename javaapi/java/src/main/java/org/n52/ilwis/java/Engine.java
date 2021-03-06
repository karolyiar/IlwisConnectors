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

public class Engine {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected Engine(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Engine obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ilwisobjectsJNI.delete_Engine(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static IObject _do(String output_name, String operation, String c3, String c4, String c5, String c6, String c7, String c8, String c9) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_0(output_name, operation, c3, c4, c5, c6, c7, c8, c9);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static IObject _do(String output_name, String operation, String c3, String c4, String c5, String c6, String c7, String c8) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_1(output_name, operation, c3, c4, c5, c6, c7, c8);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static IObject _do(String output_name, String operation, String c3, String c4, String c5, String c6, String c7) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_2(output_name, operation, c3, c4, c5, c6, c7);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static IObject _do(String output_name, String operation, String c3, String c4, String c5, String c6) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_3(output_name, operation, c3, c4, c5, c6);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static IObject _do(String output_name, String operation, String c3, String c4, String c5) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_4(output_name, operation, c3, c4, c5);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static IObject _do(String output_name, String operation, String c3, String c4) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_5(output_name, operation, c3, c4);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static IObject _do(String output_name, String operation, String c3) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_6(output_name, operation, c3);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static IObject _do(String output_name, String operation) {
    long cPtr = ilwisobjectsJNI.Engine__do__SWIG_7(output_name, operation);
    return (cPtr == 0) ? null : new IObject(cPtr, false);
  }

  public static void setWorkingCatalog(String location) {
    ilwisobjectsJNI.Engine_setWorkingCatalog(location);
  }

  public static String getLocation() {
    return ilwisobjectsJNI.Engine_getLocation();
  }

  public static vectors operations(String filter) {
    return new vectors(ilwisobjectsJNI.Engine_operations__SWIG_0(filter), true);
  }

  public static vectors operations() {
    return new vectors(ilwisobjectsJNI.Engine_operations__SWIG_1(), true);
  }

  public static String operationMetaData(String name) {
    return ilwisobjectsJNI.Engine_operationMetaData(name);
  }

  public static vectorOperation operationVector(String name) {
    return new vectorOperation(ilwisobjectsJNI.Engine_operationVector(name), true);
  }

  public static vectorOperation getAllOperations() {
    return new vectorOperation(ilwisobjectsJNI.Engine_getAllOperations(), true);
  }

  public static IlwisOperation getOperationById(long id) {
    return new IlwisOperation(ilwisobjectsJNI.Engine_getOperationById(id), true);
  }

}
