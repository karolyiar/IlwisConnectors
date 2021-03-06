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

public class ContinuousColorRange extends ColorRange {
  private long swigCPtr;

  protected ContinuousColorRange(long cPtr, boolean cMemoryOwn) {
    super(ilwisobjectsJNI.ContinuousColorRange_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ContinuousColorRange obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ilwisobjectsJNI.delete_ContinuousColorRange(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public ContinuousColorRange() {
    this(ilwisobjectsJNI.new_ContinuousColorRange__SWIG_0(), true);
  }

  public ContinuousColorRange(Color clr1, Color clr2) {
    this(ilwisobjectsJNI.new_ContinuousColorRange__SWIG_1(Color.getCPtr(clr1), clr1, Color.getCPtr(clr2), clr2), true);
  }

  public ContinuousColorRange clone() {
    long cPtr = ilwisobjectsJNI.ContinuousColorRange_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new ContinuousColorRange(cPtr, false);
  }

  public SWIGTYPE_p_QVariant ensure(SWIGTYPE_p_QVariant v, boolean inclusive) {
    long cPtr = ilwisobjectsJNI.ContinuousColorRange_ensure__SWIG_0(swigCPtr, this, SWIGTYPE_p_QVariant.getCPtr(v), inclusive);
    return (cPtr == 0) ? null : new SWIGTYPE_p_QVariant(cPtr, false);
  }

  public SWIGTYPE_p_QVariant ensure(SWIGTYPE_p_QVariant v) {
    long cPtr = ilwisobjectsJNI.ContinuousColorRange_ensure__SWIG_1(swigCPtr, this, SWIGTYPE_p_QVariant.getCPtr(v));
    return (cPtr == 0) ? null : new SWIGTYPE_p_QVariant(cPtr, false);
  }

  public boolean containsVar(SWIGTYPE_p_QVariant v, boolean inclusive) {
    return ilwisobjectsJNI.ContinuousColorRange_containsVar__SWIG_0(swigCPtr, this, SWIGTYPE_p_QVariant.getCPtr(v), inclusive);
  }

  public boolean containsVar(SWIGTYPE_p_QVariant v) {
    return ilwisobjectsJNI.ContinuousColorRange_containsVar__SWIG_1(swigCPtr, this, SWIGTYPE_p_QVariant.getCPtr(v));
  }

  public boolean containsColor(Color clr, boolean inclusive) {
    return ilwisobjectsJNI.ContinuousColorRange_containsColor__SWIG_0(swigCPtr, this, Color.getCPtr(clr), clr, inclusive);
  }

  public boolean containsColor(Color clr) {
    return ilwisobjectsJNI.ContinuousColorRange_containsColor__SWIG_1(swigCPtr, this, Color.getCPtr(clr), clr);
  }

  public boolean containsRange(ColorRange v, boolean inclusive) {
    return ilwisobjectsJNI.ContinuousColorRange_containsRange__SWIG_0(swigCPtr, this, ColorRange.getCPtr(v), v, inclusive);
  }

  public boolean containsRange(ColorRange v) {
    return ilwisobjectsJNI.ContinuousColorRange_containsRange__SWIG_1(swigCPtr, this, ColorRange.getCPtr(v), v);
  }

}
