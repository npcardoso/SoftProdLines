//

package net.sf.zipme;

import splat.ZipMeVariables;




class Deflater_deflate2 {
	Deflater_deflate2(Deflater _this, byte[] output, int offset, int length) {
		if (ZipMeVariables.getSINGLETON().isCOMPRESS___() ){
			this._this = _this;
			this.output = output;
			this.offset = offset;
			this.length = length;
		}
	}

	int execute() {
		if (ZipMeVariables.getSINGLETON().isCOMPRESS___() ){
			origLength = length;
			if (_this.state == _this.CLOSED_STATE)
				throw new IllegalStateException("Deflater closed");
			if (_this.state < _this.BUSY_STATE) {
				header = (_this.DEFLATED + ((DeflaterConstants.MAX_WBITS - 8) << 4)) << 8;
				level_flags = (_this.level - 1) >> 1;
				if (level_flags < 0 || level_flags > 3)
					level_flags = 3;
				header |= level_flags << 6;
				if ((_this.state & _this.IS_SETDICT) != 0)
					header |= DeflaterConstants.PRESET_DICT;
				header += 31 - (header % 31);
				_this.pending.writeShortMSB(header);
				if ((_this.state & _this.IS_SETDICT) != 0) {
					this.hook22();
				}
				_this.state = _this.BUSY_STATE
						| (_this.state & (_this.IS_FLUSHING | _this.IS_FINISHING));
			}
			for (;;) {
				count = _this.pending.flush(output, offset, length);
				offset += count;
				_this.totalOut += count;
				length -= count;
				if (length == 0 || _this.state == _this.FINISHED_STATE)
					break;
				if (!_this.engine.deflate(
						(_this.state & _this.IS_FLUSHING) != 0,
						(_this.state & _this.IS_FINISHING) != 0)) {
					if (_this.state == _this.BUSY_STATE)
						return origLength - length;
					else if (_this.state == _this.FLUSHING_STATE) {
						if (_this.level != _this.NO_COMPRESSION) {
							neededbits = 8 + ((-_this.pending.getBitCount()) & 7);
							while (neededbits > 0) {
								_this.pending.writeBits(2, 10);
								neededbits -= 10;
							}
						}
						_this.state = _this.BUSY_STATE;
					} else if (_this.state == _this.FINISHING_STATE) {
						_this.pending.alignToByte();
						if (!_this.noHeader) {
							this.hook23();
						}
						_this.state = _this.FINISHED_STATE;
					}
				}
			}
			return origLength - length;
		} else
			return -1;
	}

	protected Deflater _this;
	protected byte[] output;
	protected int offset;
	protected int length;
	protected int origLength;
	protected int header;
	protected int level_flags;
	protected int chksum;
	protected int count;
	protected int neededbits;
	protected int adler;

	protected void hook22() {
		if (ZipMeVariables.getSINGLETON().isCOMPRESS___() ){
		}
		if (ZipMeVariables.getSINGLETON().isDERIVATIVE_COMPRESS_ADLER32CHECKSUM___()) {
			chksum = _this.engine.getAdler();
			_this.engine.resetAdler();
			_this.pending.writeShortMSB(chksum >> 16);
			_this.pending.writeShortMSB(chksum & 0xffff);
		}
	}

	protected void hook23() {
		if (ZipMeVariables.getSINGLETON().isCOMPRESS___() ){
		}
		if (ZipMeVariables.getSINGLETON().isDERIVATIVE_COMPRESS_ADLER32CHECKSUM___()) {
			adler = _this.engine.getAdler();
			_this.pending.writeShortMSB(adler >> 16);
			_this.pending.writeShortMSB(adler & 0xffff);
		}
	}
}
