// DerivativeCompressGZIP

package net.sf.zipme;

import splat.ZipMeVariables;





class GZIPOutputStream_hook22 {
	GZIPOutputStream_hook22(GZIPOutputStream _this, byte[] gzipFooter) {
		if (ZipMeVariables.getSINGLETON().isDERIVATIVE_COMPRESS_GZIP___() ){
			this._this = _this;
			this.gzipFooter = gzipFooter;
		}
	}

	void execute() {
		if (ZipMeVariables.getSINGLETON().isDERIVATIVE_COMPRESS_GZIP___() ){}
		if (ZipMeVariables.getSINGLETON().isDERIVATIVE_COMPRESS_GZIPCRC___() ){
			crcval=(int)(_this.crc.getValue() & 0xffffffff);
		    gzipFooter[0]=(byte)crcval;
		    gzipFooter[1]=(byte)(crcval >> 8);
		    gzipFooter[2]=(byte)(crcval >> 16);
		    gzipFooter[3]=(byte)(crcval >> 24);
		}
	}

	protected GZIPOutputStream _this;
	protected byte[] gzipFooter;
	protected int crcval;
}
