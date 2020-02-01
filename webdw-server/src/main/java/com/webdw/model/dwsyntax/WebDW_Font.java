package com.webdw.model.dwsyntax;

//'WebDW�õ����ڲ�Font���壬
//'���Font���Զ�text��column��Ч
//'20081225������

public class WebDW_Font {
	public String face = "";

	public int height = 0;

	public int weight = 0;

	public int family = 0;

	public int pitch = 0;

	public int charset = 0;

	public int italic = 0;

	public int underline = 0;

	public int strikethrough = 0;

	public WebDW_Font Clone() {

		WebDW_Font newOne = new WebDW_Font();

		newOne.face = face;

		newOne.height = height;

		newOne.weight = weight;

		newOne.family = family;

		newOne.pitch = pitch;

		newOne.charset = charset;

		newOne.italic = italic;

		newOne.underline = underline;

		newOne.strikethrough = strikethrough;

		return newOne;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getFamily() {
		return family;
	}

	public void setFamily(int family) {
		this.family = family;
	}

	public int getPitch() {
		return pitch;
	}

	public void setPitch(int pitch) {
		this.pitch = pitch;
	}

	public int getCharset() {
		return charset;
	}

	public void setCharset(int charset) {
		this.charset = charset;
	}

	public int getItalic() {
		return italic;
	}

	public void setItalic(int italic) {
		this.italic = italic;
	}

	public int getUnderline() {
		return underline;
	}

	public void setUnderline(int underline) {
		this.underline = underline;
	}

	public int getStrikethrough() {
		return strikethrough;
	}

	public void setStrikethrough(int strikethrough) {
		this.strikethrough = strikethrough;
	}

}
