package com.webdw.model.syntaxmodel.dwsyntax;

/**
 * WebDW�﷨�����Java���� ��VB������ֲ����
 * 
 * @author admin
 * 
 */

public class WebDW_DataWindow {
	public String unit = "";

	public String timer_interval = "";

	public int color = 0;

	public String processiong = "";

	public String HTMLDW = "";

	public String print_documentname = "";

	public int print_orientation = 0;

	public int print_margin_left = 0;

	public int print_margin_right = 0;

	public int print_margin_top = 0;

	public int print_margin_bottom = 0;

	public int print_paper_source = 0;

	public int print_paper_size = 0;

	public String print_prompt = "";

	public String print_buttons = "";

	public String print_preview_buttons = "";

	public String grid_lines = "";

	public WebDW_DataWindow Clone() {
		WebDW_DataWindow newOne = new WebDW_DataWindow();
		newOne.unit = this.unit;
		newOne.timer_interval = this.timer_interval;
		newOne.color = this.color;
		newOne.processiong = processiong;
		newOne.HTMLDW = HTMLDW;
		newOne.print_documentname = print_documentname;
		newOne.print_orientation = print_orientation;
		newOne.print_margin_left = print_margin_left;
		newOne.print_margin_right = print_margin_right;
		newOne.print_margin_top = print_margin_top;
		newOne.print_margin_bottom = print_margin_bottom;
		newOne.print_paper_source = print_paper_source;
		newOne.print_paper_size = print_paper_size;
		newOne.print_prompt = print_prompt;
		newOne.print_buttons = print_buttons;
		newOne.print_preview_buttons = print_preview_buttons;
		newOne.grid_lines = grid_lines;

		return newOne;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTimer_interval() {
		return timer_interval;
	}

	public void setTimer_interval(String timer_interval) {
		this.timer_interval = timer_interval;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getProcessiong() {
		return processiong;
	}

	public void setProcessiong(String processiong) {
		this.processiong = processiong;
	}

	public String getHTMLDW() {
		return HTMLDW;
	}

	public void setHTMLDW(String hTMLDW) {
		HTMLDW = hTMLDW;
	}

	public String getPrint_documentname() {
		return print_documentname;
	}

	public void setPrint_documentname(String print_documentname) {
		this.print_documentname = print_documentname;
	}

	public int getPrint_orientation() {
		return print_orientation;
	}

	public void setPrint_orientation(int print_orientation) {
		this.print_orientation = print_orientation;
	}

	public int getPrint_margin_left() {
		return print_margin_left;
	}

	public void setPrint_margin_left(int print_margin_left) {
		this.print_margin_left = print_margin_left;
	}

	public int getPrint_margin_right() {
		return print_margin_right;
	}

	public void setPrint_margin_right(int print_margin_right) {
		this.print_margin_right = print_margin_right;
	}

	public int getPrint_margin_top() {
		return print_margin_top;
	}

	public void setPrint_margin_top(int print_margin_top) {
		this.print_margin_top = print_margin_top;
	}

	public int getPrint_margin_bottom() {
		return print_margin_bottom;
	}

	public void setPrint_margin_bottom(int print_margin_bottom) {
		this.print_margin_bottom = print_margin_bottom;
	}

	public int getPrint_paper_source() {
		return print_paper_source;
	}

	public void setPrint_paper_source(int print_paper_source) {
		this.print_paper_source = print_paper_source;
	}

	public int getPrint_paper_size() {
		return print_paper_size;
	}

	public void setPrint_paper_size(int print_paper_size) {
		this.print_paper_size = print_paper_size;
	}

	public String getPrint_prompt() {
		return print_prompt;
	}

	public void setPrint_prompt(String print_prompt) {
		this.print_prompt = print_prompt;
	}

	public String getPrint_buttons() {
		return print_buttons;
	}

	public void setPrint_buttons(String print_buttons) {
		this.print_buttons = print_buttons;
	}

	public String getPrint_preview_buttons() {
		return print_preview_buttons;
	}

	public void setPrint_preview_buttons(String print_preview_buttons) {
		this.print_preview_buttons = print_preview_buttons;
	}

	public String getGrid_lines() {
		return grid_lines;
	}

	public void setGrid_lines(String grid_lines) {
		this.grid_lines = grid_lines;
	}

}
