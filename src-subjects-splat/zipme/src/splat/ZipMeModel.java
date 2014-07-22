package splat;

import java.util.ArrayList;
import java.util.List;

import util.model.And;
import util.model.FeatureExpression;
import util.model.FeatureId;
import util.model.Implies;
import util.model.Model;

public class ZipMeModel extends Model {

  public ZipMeModel() {    
    super(constraints());
  }

  private static List<FeatureExpression> constraints() {
    List<FeatureExpression> list = new ArrayList<FeatureExpression>();

    // features
    FeatureId derivativeGzipCRC = new FeatureId("DERIVATIVE_GZIPCRC___");
    FeatureId extract = new FeatureId("EXTRACT___");
    FeatureId adler32checksum = new FeatureId("ADLER32CHECKSUM___");    
    FeatureId base = new FeatureId("BASE___");
    FeatureId compress = new FeatureId("COMPRESS___");
    FeatureId gzip = new FeatureId("GZIP___");
    FeatureId crc = new FeatureId("CRC___");
    FeatureId derivativeCompressAdler32Checksum = new FeatureId("DERIVATIVE_COMPRESS_ADLER32CHECKSUM___");    
    FeatureId derivativeCompressCRC = new FeatureId("DERIVATIVE_COMPRESS_CRC___");
    FeatureId derivativeCompressGzip = new FeatureId("DERIVATIVE_COMPRESS_GZIP___");
    FeatureId derivativeCompressGzipCRC = new FeatureId("DERIVATIVE_COMPRESS_GZIPCRC___");
    FeatureId derivativeExtractCRC = new FeatureId("DERIVATIVE_EXTRACT_CRC___");
    

    list.add(base);
    list.add(compress);
    list.add(new Implies(gzip, crc));
    
    //
    list.add(new Implies(new And(compress, adler32checksum), derivativeCompressAdler32Checksum));
    list.add(new Implies(derivativeCompressAdler32Checksum, new And(compress, adler32checksum)));
    
    //
    list.add(new Implies(new And(compress, crc), derivativeCompressCRC));
    list.add(new Implies(derivativeCompressCRC, new And(compress, crc)));
    
    //
    list.add(new Implies(new And(compress, gzip), derivativeCompressGzip));
    list.add(new Implies(derivativeCompressGzip, new And(compress, gzip)));
    
    //
    list.add(new Implies(new And(new And(compress, gzip), crc), derivativeCompressGzipCRC));
    list.add(new Implies(derivativeCompressGzipCRC, new And(new And(compress, gzip), crc)));
    
    //
    list.add(new Implies(new And(extract, crc), derivativeExtractCRC));
    list.add(new Implies(derivativeExtractCRC, new And(extract, crc)));
    
    list.add(new Implies(new And(gzip, crc), derivativeGzipCRC));
    list.add(new Implies(derivativeGzipCRC, new And(gzip, crc)));

    
    return list;
  }
  
  
  /** example **/
  public static void main(String[] args) { 
    //1111101001101
    //1111101001101
    String[] sa = new String[]{
        "1","1","1","1","1","0","1","0","0","1","1","0","1"
    };
    boolean res = (new ZipMeModel()).eval(ZipMeVariables.getSINGLETON(), sa);
    System.out.println(res);
  }

}