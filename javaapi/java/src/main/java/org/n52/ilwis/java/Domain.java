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

public class Domain extends IlwisObject {
  private long swigCPtr;

  protected Domain(long cPtr, boolean cMemoryOwn) {
    super(ilwisobjectsJNI.Domain_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Domain obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ilwisobjectsJNI.delete_Domain(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public Domain() {
    this(ilwisobjectsJNI.new_Domain(), true);
  }

  public boolean isStrict() {
    return ilwisobjectsJNI.Domain_isStrict(swigCPtr, this);
  }

  public void setStrict(boolean yesno) {
    ilwisobjectsJNI.Domain_setStrict(swigCPtr, this, yesno);
  }

  public Domain parent() {
    return new Domain(ilwisobjectsJNI.Domain_parent(swigCPtr, this), true);
  }

  public void setParent(Domain dom) {
    ilwisobjectsJNI.Domain_setParent(swigCPtr, this, Domain.getCPtr(dom), dom);
  }

  public Range getRange() {
    return new Range(ilwisobjectsJNI.Domain_getRange(swigCPtr, this), false);
  }

  public String contains(double value) {
    return ilwisobjectsJNI.Domain_contains__SWIG_0(swigCPtr, this, value);
  }

  public String contains(String value) {
    return ilwisobjectsJNI.Domain_contains__SWIG_1(swigCPtr, this, value);
  }

  public boolean isCompatibleWith(Domain dom) {
    return ilwisobjectsJNI.Domain_isCompatibleWith(swigCPtr, this, Domain.getCPtr(dom), dom);
  }

  public void setRange(Range rng) {
    ilwisobjectsJNI.Domain_setRange(swigCPtr, this, Range.getCPtr(rng), rng);
  }

  public java.math.BigInteger valueType() {
    return ilwisobjectsJNI.Domain_valueType(swigCPtr, this);
  }

  public static Domain toDomain(IObject obj) {
    long cPtr = ilwisobjectsJNI.Domain_toDomain(IObject.getCPtr(obj), obj);
    return (cPtr == 0) ? null : new Domain(cPtr, false);
  }

  public final static class Containement {
    public final static Domain.Containement cSELF = new Domain.Containement("cSELF", ilwisobjectsJNI.Domain_cSELF_get());
    public final static Domain.Containement cPARENT = new Domain.Containement("cPARENT", ilwisobjectsJNI.Domain_cPARENT_get());
    public final static Domain.Containement cDECLARED = new Domain.Containement("cDECLARED", ilwisobjectsJNI.Domain_cDECLARED_get());
    public final static Domain.Containement cNONE = new Domain.Containement("cNONE", ilwisobjectsJNI.Domain_cNONE_get());

    public final int swigValue() {
      return swigValue;
    }

    public String toString() {
      return swigName;
    }

    public static Containement swigToEnum(int swigValue) {
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (int i = 0; i < swigValues.length; i++)
        if (swigValues[i].swigValue == swigValue)
          return swigValues[i];
      throw new IllegalArgumentException("No enum " + Containement.class + " with value " + swigValue);
    }

    private Containement(String swigName) {
      this.swigName = swigName;
      this.swigValue = swigNext++;
    }

    private Containement(String swigName, int swigValue) {
      this.swigName = swigName;
      this.swigValue = swigValue;
      swigNext = swigValue+1;
    }

    private Containement(String swigName, Containement swigEnum) {
      this.swigName = swigName;
      this.swigValue = swigEnum.swigValue;
      swigNext = this.swigValue+1;
    }

    private static Containement[] swigValues = { cSELF, cPARENT, cDECLARED, cNONE };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
  }

}
