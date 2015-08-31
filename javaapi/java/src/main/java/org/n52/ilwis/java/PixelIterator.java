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
import java.util.Iterator;
public class PixelIterator implements Iterator<Double> {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected PixelIterator(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(PixelIterator obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ilwisobjectsJNI.delete_PixelIterator(swigCPtr);
      }
      swigCPtr = 0;
    }
  }
  public Double next() { return _next(); }
  public void remove() { } // Filler function

  public PixelIterator(PixelIterator pi) {
    this(ilwisobjectsJNI.new_PixelIterator__SWIG_0(PixelIterator.getCPtr(pi), pi), true);
  }

  public PixelIterator(RasterCoverage rc, Box b) {
    this(ilwisobjectsJNI.new_PixelIterator__SWIG_1(RasterCoverage.getCPtr(rc), rc, Box.getCPtr(b), b), true);
  }

  public PixelIterator(RasterCoverage rc) {
    this(ilwisobjectsJNI.new_PixelIterator__SWIG_2(RasterCoverage.getCPtr(rc), rc), true);
  }

  public PixelIterator(RasterCoverage rc, Geometry geom) {
    this(ilwisobjectsJNI.new_PixelIterator__SWIG_3(RasterCoverage.getCPtr(rc), rc, Geometry.getCPtr(geom), geom), true);
  }

  public PixelIterator(SWIGTYPE_p_Ilwis__PixelIterator ilwIt) {
    this(ilwisobjectsJNI.new_PixelIterator__SWIG_4(SWIGTYPE_p_Ilwis__PixelIterator.getCPtr(ilwIt)), true);
  }

  public PixelIterator iterator() {
    long cPtr = ilwisobjectsJNI.PixelIterator_iterator(swigCPtr, this);
    return (cPtr == 0) ? null : new PixelIterator(cPtr, false);
  }

  public double _next() {
    return ilwisobjectsJNI.PixelIterator__next(swigCPtr, this);
  }

  public boolean hasNext() {
    return ilwisobjectsJNI.PixelIterator_hasNext(swigCPtr, this);
  }

  public boolean isValid() {
    return ilwisobjectsJNI.PixelIterator_isValid(swigCPtr, this);
  }

  public String toString() {
    return ilwisobjectsJNI.PixelIterator_toString(swigCPtr, this);
  }

  public double toDouble() {
    return ilwisobjectsJNI.PixelIterator_toDouble(swigCPtr, this);
  }

  public java.math.BigInteger toBigInteger() {
    return ilwisobjectsJNI.PixelIterator_toBigInteger(swigCPtr, this);
  }

  public boolean contains(Pixel vox) {
    return ilwisobjectsJNI.PixelIterator_contains(swigCPtr, this, Pixel.getCPtr(vox), vox);
  }

  public Box box() {
    return new Box(ilwisobjectsJNI.PixelIterator_box(swigCPtr, this), true);
  }

  public Pixel position() {
    return new Pixel(ilwisobjectsJNI.PixelIterator_position(swigCPtr, this), true);
  }

  public void setFlow(Flow.Value flw) {
    ilwisobjectsJNI.PixelIterator_setFlow(swigCPtr, this, flw.swigValue());
  }

  public boolean xChanged() {
    return ilwisobjectsJNI.PixelIterator_xChanged(swigCPtr, this);
  }

  public boolean yChanged() {
    return ilwisobjectsJNI.PixelIterator_yChanged(swigCPtr, this);
  }

  public boolean zChanged() {
    return ilwisobjectsJNI.PixelIterator_zChanged(swigCPtr, this);
  }

  public PixelIterator set(PixelIterator ohterIt) {
    long cPtr = ilwisobjectsJNI.PixelIterator_set__SWIG_0(swigCPtr, this, PixelIterator.getCPtr(ohterIt), ohterIt);
    return (cPtr == 0) ? null : new PixelIterator(cPtr, false);
  }

  public PixelIterator get(Pixel vox) {
    return new PixelIterator(ilwisobjectsJNI.PixelIterator_get__SWIG_0(swigCPtr, this, Pixel.getCPtr(vox), vox), false);
  }

  public double get(long linearPosition) {
    return ilwisobjectsJNI.PixelIterator_get__SWIG_1(swigCPtr, this, linearPosition);
  }

  public void set(long linearPosition, double value) {
    ilwisobjectsJNI.PixelIterator_set__SWIG_1(swigCPtr, this, linearPosition, value);
  }

  public PixelIterator add(int n) {
    return new PixelIterator(ilwisobjectsJNI.PixelIterator_add(swigCPtr, this, n), true);
  }

  public PixelIterator __radd__(int n) {
    return new PixelIterator(ilwisobjectsJNI.PixelIterator___radd__(swigCPtr, this, n), true);
  }

  public PixelIterator increase(int n) {
    long cPtr = ilwisobjectsJNI.PixelIterator_increase(swigCPtr, this, n);
    return (cPtr == 0) ? null : new PixelIterator(cPtr, false);
  }

  public boolean notequal(PixelIterator other) {
    return ilwisobjectsJNI.PixelIterator_notequal(swigCPtr, this, PixelIterator.getCPtr(other), other);
  }

  public int compareTo(PixelIterator other) {
    return ilwisobjectsJNI.PixelIterator_compareTo(swigCPtr, this, PixelIterator.getCPtr(other), other);
  }

  public PixelIterator end() {
    return new PixelIterator(ilwisobjectsJNI.PixelIterator_end(swigCPtr, this), true);
  }

  public RasterCoverage raster() {
    long cPtr = ilwisobjectsJNI.PixelIterator_raster(swigCPtr, this);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

}