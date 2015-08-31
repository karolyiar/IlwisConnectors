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

public class RasterCoverage extends Coverage implements Iterable<Double> {
  private long swigCPtr;

  protected RasterCoverage(long cPtr, boolean cMemoryOwn) {
    super(ilwisobjectsJNI.RasterCoverage_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(RasterCoverage obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ilwisobjectsJNI.delete_RasterCoverage(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public RasterCoverage() {
    this(ilwisobjectsJNI.new_RasterCoverage__SWIG_0(), true);
  }

  public RasterCoverage(String resource) {
    this(ilwisobjectsJNI.new_RasterCoverage__SWIG_1(resource), true);
  }

  public RasterCoverage add(RasterCoverage rc) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_add__SWIG_0(swigCPtr, this, RasterCoverage.getCPtr(rc), rc);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage add(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_add__SWIG_1(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage __radd__(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage___radd__(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage subtract(RasterCoverage rc) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_subtract__SWIG_0(swigCPtr, this, RasterCoverage.getCPtr(rc), rc);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage subtract(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_subtract__SWIG_1(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage __rsub__(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage___rsub__(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage multiply(RasterCoverage rc) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_multiply__SWIG_0(swigCPtr, this, RasterCoverage.getCPtr(rc), rc);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage multiply(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_multiply__SWIG_1(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage __rmul__(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage___rmul__(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage __truediv__(RasterCoverage rc) {
    long cPtr = ilwisobjectsJNI.RasterCoverage___truediv____SWIG_0(swigCPtr, this, RasterCoverage.getCPtr(rc), rc);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage __truediv__(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage___truediv____SWIG_1(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage __rtruediv__(double value) {
    long cPtr = ilwisobjectsJNI.RasterCoverage___rtruediv__(swigCPtr, this, value);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public PixelIterator iterator() {
    return new PixelIterator(ilwisobjectsJNI.RasterCoverage_iterator(swigCPtr, this), true);
  }

  public double coord2value(Coordinate c) {
    return ilwisobjectsJNI.RasterCoverage_coord2value(swigCPtr, this, Coordinate.getCPtr(c), c);
  }

  public double pix2value(Pixel pix) {
    return ilwisobjectsJNI.RasterCoverage_pix2value__SWIG_0(swigCPtr, this, Pixel.getCPtr(pix), pix);
  }

  public double pix2value(PixelD pix) {
    return ilwisobjectsJNI.RasterCoverage_pix2value__SWIG_1(swigCPtr, this, PixelD.getCPtr(pix), pix);
  }

  public Size size() {
    return new Size(ilwisobjectsJNI.RasterCoverage_size(swigCPtr, this), true);
  }

  public void setSize(Size sz) {
    ilwisobjectsJNI.RasterCoverage_setSize(swigCPtr, this, Size.getCPtr(sz), sz);
  }

  public void unloadBinary() {
    ilwisobjectsJNI.RasterCoverage_unloadBinary(swigCPtr, this);
  }

  public CoordinateSystem coordinateSystem() {
    return new CoordinateSystem(ilwisobjectsJNI.RasterCoverage_coordinateSystem(swigCPtr, this), true);
  }

  public GeoReference geoReference() {
    return new GeoReference(ilwisobjectsJNI.RasterCoverage_geoReference(swigCPtr, this), true);
  }

  public void setGeoReference(GeoReference gr) {
    ilwisobjectsJNI.RasterCoverage_setGeoReference(swigCPtr, this, GeoReference.getCPtr(gr), gr);
  }

  public DataDefinition datadef() {
    return new DataDefinition(ilwisobjectsJNI.RasterCoverage_datadef(swigCPtr, this), false);
  }

  public void setDataDef(DataDefinition datdef) {
    ilwisobjectsJNI.RasterCoverage_setDataDef__SWIG_0(swigCPtr, this, DataDefinition.getCPtr(datdef), datdef);
  }

  public void setDataDef(Domain dm) {
    ilwisobjectsJNI.RasterCoverage_setDataDef__SWIG_1(swigCPtr, this, Domain.getCPtr(dm), dm);
  }

  public SWIGTYPE_p_javaapi__ContainerStatisticsT_double_t statistics(int mode, int bins) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_statistics__SWIG_0(swigCPtr, this, mode, bins);
    return (cPtr == 0) ? null : new SWIGTYPE_p_javaapi__ContainerStatisticsT_double_t(cPtr, false);
  }

  public SWIGTYPE_p_javaapi__ContainerStatisticsT_double_t statistics(int mode) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_statistics__SWIG_1(swigCPtr, this, mode);
    return (cPtr == 0) ? null : new SWIGTYPE_p_javaapi__ContainerStatisticsT_double_t(cPtr, false);
  }

  public SWIGTYPE_p_javaapi__ContainerStatisticsT_double_t statistics() {
    long cPtr = ilwisobjectsJNI.RasterCoverage_statistics__SWIG_2(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_javaapi__ContainerStatisticsT_double_t(cPtr, false);
  }

  public static RasterCoverage toRasterCoverage(IObject obj) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_toRasterCoverage(IObject.getCPtr(obj), obj);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public PixelIterator begin() {
    return new PixelIterator(ilwisobjectsJNI.RasterCoverage_begin(swigCPtr, this), true);
  }

  public PixelIterator end() {
    return new PixelIterator(ilwisobjectsJNI.RasterCoverage_end(swigCPtr, this), true);
  }

  public PixelIterator band(double index) {
    return new PixelIterator(ilwisobjectsJNI.RasterCoverage_band__SWIG_0(swigCPtr, this, index), true);
  }

  public PixelIterator band(String index) {
    return new PixelIterator(ilwisobjectsJNI.RasterCoverage_band__SWIG_1(swigCPtr, this, index), true);
  }

  public long indexOf(String variantId) {
    return ilwisobjectsJNI.RasterCoverage_indexOf__SWIG_0(swigCPtr, this, variantId);
  }

  public long indexOf(double domainItem) {
    return ilwisobjectsJNI.RasterCoverage_indexOf__SWIG_1(swigCPtr, this, domainItem);
  }

  public String atIndex(long idx) {
    return ilwisobjectsJNI.RasterCoverage_atIndex(swigCPtr, this, idx);
  }

  public long countSubs() {
    return ilwisobjectsJNI.RasterCoverage_countSubs(swigCPtr, this);
  }

  public Domain subDomain() {
    return new Domain(ilwisobjectsJNI.RasterCoverage_subDomain(swigCPtr, this), true);
  }

  public void clear() {
    ilwisobjectsJNI.RasterCoverage_clear(swigCPtr, this);
  }

  public RasterCoverage select(String selectionQ) {
    return new RasterCoverage(ilwisobjectsJNI.RasterCoverage_select__SWIG_0(swigCPtr, this, selectionQ), true);
  }

  public RasterCoverage select(Geometry geom) {
    return new RasterCoverage(ilwisobjectsJNI.RasterCoverage_select__SWIG_1(swigCPtr, this, Geometry.getCPtr(geom), geom), true);
  }

  public RasterCoverage reprojectRaster(String newName, long epsg, String interpol) {
    long cPtr = ilwisobjectsJNI.RasterCoverage_reprojectRaster(swigCPtr, this, newName, epsg, interpol);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public RasterCoverage clone() {
    long cPtr = ilwisobjectsJNI.RasterCoverage_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new RasterCoverage(cPtr, false);
  }

  public Envelope envelope() {
    return new Envelope(ilwisobjectsJNI.RasterCoverage_envelope(swigCPtr, this), true);
  }

}