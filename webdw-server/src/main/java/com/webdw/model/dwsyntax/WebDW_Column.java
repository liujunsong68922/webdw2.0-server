package com.webdw.model.dwsyntax;
// '
// 'column的支持属性定义，
// '从配置文件中读入到这个结构中
// '再用这个结构来绘图显�??
// '属�?�名称与属�?�文件中定义的相�??,不与控件本身�??�??
// '属�?�名全用小写
public class WebDW_Column {
	public String band = "";

	public int id = 0;

	public int alignment = 0;

	public int tabsequence = 0;

	public int border = 0;

	public int color = 0;

	public int x = 0;

	public int y = 0;

	public int height = 0;

	public int width = 0;

	public String format = "";

	public String Name = "";

	public String tag = "";

	public int edit_limit = 0;

	public String edit_case = "";

	public String edit_focusrectangle = "";

	public String edit_autoselect = "";

	public String edit_autohscroll = "";

	public WebDW_Font font = new WebDW_Font(); // '重新定义Font结构并使用这个结构来使用

	public int background_mode = 0;

	public int background_color = 0;

	public WebDW_Column_RadioButtons radiobuttons = new WebDW_Column_RadioButtons(); // '单�?�按钮的支持定义20090124

	public WebDW_Column_CheckBox checkbox = new WebDW_Column_CheckBox(); // '选择按钮的支持定�??20090124

	public WebDW_Column_ComboBox combobox = new WebDW_Column_ComboBox(); // '下拉列表框的支持定义20090124

	public WebDW_Column_DDDW dddw = new WebDW_Column_DDDW(); // '下拉数据窗口的支持定�??20090125

	public WebDW_Column Clone() {

		WebDW_Column newOne = new WebDW_Column();
		newOne.band = band;

		newOne.id = id;

		newOne.alignment = alignment;

		newOne.tabsequence = tabsequence;

		newOne.border = border;

		newOne.color = color;

		newOne.x = x;

		newOne.y = y;

		newOne.height = height;

		newOne.width = width;

		newOne.format = format;

		newOne.Name = Name;

		newOne.tag = tag;

		newOne.edit_limit = edit_limit;

		newOne.edit_case = edit_case;

		newOne.edit_focusrectangle = edit_focusrectangle;

		newOne.edit_autoselect = edit_autoselect;

		newOne.edit_autohscroll = edit_autohscroll;

		newOne.font = font.Clone(); // '重新定义Font结构并使用这个结构来使用

		newOne.background_mode = background_mode;

		newOne.background_color = background_color;

		newOne.radiobuttons = radiobuttons.Clone(); // '单�?�按钮的支持定义20090124

		newOne.checkbox = checkbox.Clone(); // '选择按钮的支持定�??20090124

		newOne.combobox = combobox.Clone(); // '下拉列表框的支持定义20090124

		newOne.dddw = dddw.Clone(); // '下拉数据窗口的支持定�??20090125

		return newOne;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public int getTabsequence() {
		return tabsequence;
	}

	public void setTabsequence(int tabsequence) {
		this.tabsequence = tabsequence;
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		this.border = border;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getEdit_limit() {
		return edit_limit;
	}

	public void setEdit_limit(int edit_limit) {
		this.edit_limit = edit_limit;
	}

	public String getEdit_case() {
		return edit_case;
	}

	public void setEdit_case(String edit_case) {
		this.edit_case = edit_case;
	}

	public String getEdit_focusrectangle() {
		return edit_focusrectangle;
	}

	public void setEdit_focusrectangle(String edit_focusrectangle) {
		this.edit_focusrectangle = edit_focusrectangle;
	}

	public String getEdit_autoselect() {
		return edit_autoselect;
	}

	public void setEdit_autoselect(String edit_autoselect) {
		this.edit_autoselect = edit_autoselect;
	}

	public String getEdit_autohscroll() {
		return edit_autohscroll;
	}

	public void setEdit_autohscroll(String edit_autohscroll) {
		this.edit_autohscroll = edit_autohscroll;
	}

	public WebDW_Font getFont() {
		return font;
	}

	public void setFont(WebDW_Font font) {
		this.font = font;
	}

	public int getBackground_mode() {
		return background_mode;
	}

	public void setBackground_mode(int background_mode) {
		this.background_mode = background_mode;
	}

	public int getBackground_color() {
		return background_color;
	}

	public void setBackground_color(int background_color) {
		this.background_color = background_color;
	}

	public WebDW_Column_RadioButtons getRadiobuttons() {
		return radiobuttons;
	}

	public void setRadiobuttons(WebDW_Column_RadioButtons radiobuttons) {
		this.radiobuttons = radiobuttons;
	}

	public WebDW_Column_CheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(WebDW_Column_CheckBox checkbox) {
		this.checkbox = checkbox;
	}

	public WebDW_Column_ComboBox getCombobox() {
		return combobox;
	}

	public void setCombobox(WebDW_Column_ComboBox combobox) {
		this.combobox = combobox;
	}

	public WebDW_Column_DDDW getDddw() {
		return dddw;
	}

	public void setDddw(WebDW_Column_DDDW dddw) {
		this.dddw = dddw;
	}

}