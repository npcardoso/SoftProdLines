package splat;


public class ZipMeVariables extends Variables{

  private static ZipMeVariables SINGLETON;

  public static ZipMeVariables getSINGLETON() {
    if (SINGLETON == null) {
      SINGLETON = new ZipMeVariables();
    }
    return SINGLETON;
  }
  
  private ZipMeVariables() {
    restore();
  }
  
  private enum VARS {
    BASE___, 
    COMPRESS___,  
    GZIP___, 
    EXTRACT___, 
    ARCHIVECHECK___, 
    CRC___, 
    ADLER32CHECKSUM___,
    DERIVATIVE_COMPRESS_ADLER32CHECKSUM___, 
    DERIVATIVE_COMPRESS_CRC___, 
    DERIVATIVE_COMPRESS_GZIP___, 
    DERIVATIVE_COMPRESS_GZIPCRC___, 
    DERIVATIVE_EXTRACT_CRC___, 
    DERIVATIVE_GZIPCRC___
  }
  
  private void init() {
    map.put(VARS.BASE___, "?");
    map.put(VARS.COMPRESS___, "?");
    map.put(VARS.GZIP___, "?");
    map.put(VARS.EXTRACT___, "?");
    map.put(VARS.ARCHIVECHECK___, "?");
    map.put(VARS.CRC___, "?");
    map.put(VARS.ADLER32CHECKSUM___, "?");
    map.put(VARS.DERIVATIVE_COMPRESS_ADLER32CHECKSUM___, "?");
    map.put(VARS.DERIVATIVE_COMPRESS_CRC___, "?");
    map.put(VARS.DERIVATIVE_COMPRESS_GZIP___, "?");
    map.put(VARS.DERIVATIVE_COMPRESS_GZIPCRC___, "?");
    map.put(VARS.DERIVATIVE_EXTRACT_CRC___, "?");
    map.put(VARS.DERIVATIVE_GZIPCRC___, "?");
  }
  
  private String get(VARS f) {
    String tmp = map.get(f);
    if (tmp == "?") {
      /**
       * only makes a choice if it is not already present in the map
       */
      tmp = SPLat.bt.choose() ? "1" : "0";
      map.put(f, tmp);
    }
    return tmp;
  }


  public boolean isBASE___() {
    return get(VARS.BASE___) == "1" ? true : false;
  }

 public boolean isEXTRACT___() {
    return get(VARS.EXTRACT___) == "1" ? true : false;
  }

  public boolean isARCHIVECHECK___() {
    return get(VARS.ARCHIVECHECK___) == "1" ? true : false;
  }

  public boolean isGZIP___() {
    return get(VARS.GZIP___) == "1" ? true : false;
  }

  public boolean isCRC___() {
    return get(VARS.CRC___) == "1" ? true : false;
  }

  public boolean isCOMPRESS___() {
    return get(VARS.COMPRESS___) == "1" ? true : false;
  }

  public boolean isADLER32CHECKSUM___() {
    return get(VARS.ADLER32CHECKSUM___) == "1" ? true : false;
  }

  public boolean isDERIVATIVE_COMPRESS_ADLER32CHECKSUM___() {
    return get(VARS.DERIVATIVE_COMPRESS_ADLER32CHECKSUM___) == "1" ? true : false;
  }

  public boolean isDERIVATIVE_COMPRESS_CRC___() {
    return get(VARS.DERIVATIVE_COMPRESS_CRC___) == "1" ? true : false;
  }

  public boolean isDERIVATIVE_COMPRESS_GZIP___() {
    return get(VARS.DERIVATIVE_COMPRESS_GZIP___) == "1" ? true : false;
  }

  public boolean isDERIVATIVE_COMPRESS_GZIPCRC___() {
    return get(VARS.DERIVATIVE_COMPRESS_GZIPCRC___) == "1" ? true : false;
  }

  public boolean isDERIVATIVE_EXTRACT_CRC___() {
    return get(VARS.DERIVATIVE_EXTRACT_CRC___) == "1" ? true : false;
  }

  public boolean isDERIVATIVE_GZIPCRC___() {
    return get(VARS.DERIVATIVE_GZIPCRC___) == "1" ? true : false;
  }

  public void setBASE___(boolean v) {
    if (v) {
      map.put(VARS.BASE___, "1");
    } else {
      map.put(VARS.BASE___, "0");
    }
  }

  public void setEXTRACT___(boolean v) {
    if (v) {
      map.put(VARS.EXTRACT___, "1");
    } else {
      map.put(VARS.EXTRACT___, "0");
    }
  }

  public void setARCHIVECHECK___(boolean v) {
    if (v) {
      map.put(VARS.ARCHIVECHECK___, "1");
    } else {
      map.put(VARS.ARCHIVECHECK___, "0");
    }
  }

  public void setGZIP___(boolean v) {
    if (v) {
      map.put(VARS.GZIP___, "1");
    } else {
      map.put(VARS.GZIP___, "0");
    }
  }

  public void setCRC___(boolean v) {
    if (v) {
      map.put(VARS.CRC___, "1");
    } else {
      map.put(VARS.CRC___, "0");
    }
  }

  public void setCOMPRESS___(boolean v) {
    if (v) {
      map.put(VARS.COMPRESS___, "1");
    } else {
      map.put(VARS.COMPRESS___, "0");
    }
  }

  public void setADLER32CHECKSUM___(boolean v) {
    if (v) {
      map.put(VARS.ADLER32CHECKSUM___, "1");
    } else {
      map.put(VARS.ADLER32CHECKSUM___, "0");
    }
  }

  public void setDERIVATIVE_COMPRESS_ADLER32CHECKSUM___(boolean v) {
    if (v) {
      map.put(VARS.DERIVATIVE_COMPRESS_ADLER32CHECKSUM___, "1");
    } else {
      map.put(VARS.DERIVATIVE_COMPRESS_ADLER32CHECKSUM___, "0");
    }
  }

  public void setDERIVATIVE_COMPRESS_CRC___(boolean v) {
    if (v) {
      map.put(VARS.DERIVATIVE_COMPRESS_CRC___, "1");
    } else {
      map.put(VARS.DERIVATIVE_COMPRESS_CRC___, "0");
    }
  }

  public void setDERIVATIVE_COMPRESS_GZIP___(boolean v) {
    if (v) {
      map.put(VARS.DERIVATIVE_COMPRESS_GZIP___, "1");
    } else {
      map.put(VARS.DERIVATIVE_COMPRESS_GZIP___, "0");
    }
  }

  public void setDERIVATIVE_COMPRESS_GZIPCRC___(boolean v) {
    if (v) {
      map.put(VARS.DERIVATIVE_COMPRESS_GZIPCRC___, "1");
    } else {
      map.put(VARS.DERIVATIVE_COMPRESS_GZIPCRC___, "0");
    }
  }

  public void setDERIVATIVE_EXTRACT_CRC___(boolean v) {
    if (v) {
      map.put(VARS.DERIVATIVE_EXTRACT_CRC___, "1");
    } else {
      map.put(VARS.DERIVATIVE_EXTRACT_CRC___, "0");
    }
  }

  public void setDERIVATIVE_GZIPCRC___(boolean v) {
    if (v) {
      map.put(VARS.DERIVATIVE_GZIPCRC___, "1");
    } else {
      map.put(VARS.DERIVATIVE_GZIPCRC___, "0");
    }
  }
  
  /******************/

  @Override
  public void restore() {
    map.clear();
    init();
  }

}
