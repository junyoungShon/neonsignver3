//2015-12-08 대협추가

package org.cobro.neonsign.vo;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	private MultipartFile file;

	public FileVO() {
		super();
	}

	public FileVO(MultipartFile file) {
		super();
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "FileVO [file=" + file + "]";
	}

}
