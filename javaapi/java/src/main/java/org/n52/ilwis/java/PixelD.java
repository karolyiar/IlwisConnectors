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

public class PixelD {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected PixelD(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(PixelD obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ilwisobjectsJNI.delete_PixelD(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public PixelD(Pixel pixel) {
    this(ilwisobjectsJNI.new_PixelD__SWIG_0(Pixel.getCPtr(pixel), pixel), true);
  }

  public PixelD(PixelD pixel) {
    this(ilwisobjectsJNI.new_PixelD__SWIG_1(PixelD.getCPtr(pixel), pixel), true);
  }

  public PixelD(double x, double y) {
    this(ilwisobjectsJNI.new_PixelD__SWIG_2(x, y), true);
  }

  public PixelD(double x, double y, double z) {
    this(ilwisobjectsJNI.new_PixelD__SWIG_3(x, y, z), true);
  }

  public double x() {
    return ilwisobjectsJNI.PixelD_x(swigCPtr, this);
  }

  public double y() {
    return ilwisobjectsJNI.PixelD_y(swigCPtr, this);
  }

  public double z() {
    return ilwisobjectsJNI.PixelD_z(swigCPtr, this);
  }

  public void setX(double v) {
    ilwisobjectsJNI.PixelD_setX(swigCPtr, this, v);
  }

  public void setY(double v) {
    ilwisobjectsJNI.PixelD_setY(swigCPtr, this, v);
  }

  public void setZ(double v) {
    ilwisobjectsJNI.PixelD_setZ(swigCPtr, this, v);
  }

  public boolean is3D() {
    return ilwisobjectsJNI.PixelD_is3D(swigCPtr, this);
  }

  public PixelD multiply(double n) {
    long cPtr = ilwisobjectsJNI.PixelD_multiply(swigCPtr, this, n);
    return (cPtr == 0) ? null : new PixelD(cPtr, false);
  }

  public PixelD divide(double n) {
    long cPtr = ilwisobjectsJNI.PixelD_divide(swigCPtr, this, n);
    return (cPtr == 0) ? null : new PixelD(cPtr, false);
  }

  public boolean equals(PixelD other) {
    return ilwisobjectsJNI.PixelD_equals(swigCPtr, this, PixelD.getCPtr(other), other);
  }

  public boolean notequal(PixelD other) {
    return ilwisobjectsJNI.PixelD_notequal(swigCPtr, this, PixelD.getCPtr(other), other);
  }

  public String toString() {
    return ilwisobjectsJNI.PixelD_toString(swigCPtr, this);
  }

  public boolean isValid() {
    return ilwisobjectsJNI.PixelD_isValid(swigCPtr, this);
  }

}
