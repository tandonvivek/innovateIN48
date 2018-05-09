package com.fis.epo.ui.accelerator.api.core.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fis.epo.ui.accelerator.api.core.helper.JsonDateSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@JsonInclude(Include.NON_NULL)
public class Widget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 1, required = true, value = "Widget Id")
	public Long wdgtId;

	@Column(nullable = false)
	@ApiModelProperty(position = 2, required = true, value = "Widget Name")
	public String wdgtNme;

	@ApiModelProperty(position = 3, required = false, value = "HTML Location")
	public String htmlLoctn;

	@ApiModelProperty(position = 4, required = false, value = "JavaScript Location")
	public String jsLoctn;

	@ApiModelProperty(position = 5, required = false, value = "Last Used Time Stamp")
	@JsonSerialize(using = JsonDateSerializer.class)
	public Timestamp lstUsgTs;

	@Column(nullable = false)
	@ApiModelProperty(position = 6, required = true, value = "Usage Count")
	public Long usgCnt;

	@Column(nullable = false)
	@ApiModelProperty(position = 7, required = true, value = "Is Deactive")
	public boolean isDactv;

	@Column(nullable = false)
	@ApiModelProperty(position = 8, required = true, value = "Component Name")
	public String cmptNme;

	@ApiModelProperty(position = 9, required = false, value = "Widget Description")
	public String wdgtDesc;

	@ApiModelProperty(position = 10, required = false, value = "Widget Image")
	public byte[] wdgtImg;

	@ApiModelProperty(position = 11, required = false, value = "Widget Extension")
	@OneToOne
	@PrimaryKeyJoinColumn
	private WidgetExt widgetExt;

	public void setisDactv(boolean isDactv) {
		this.isDactv = isDactv;
	}

	Widget() {
	}

	public Widget(String wdgtNme, String htmlLoctn, String jsLoctn, Timestamp lstUsgTs, Long usgCnt, boolean isDactv,
			String cmptNme, String wdgtDesc, byte[] wdgtImg) {
		this.wdgtNme = wdgtNme;
		this.htmlLoctn = htmlLoctn;
		this.jsLoctn = jsLoctn;
		this.lstUsgTs = lstUsgTs;
		this.usgCnt = usgCnt;
		this.isDactv = isDactv;
		this.cmptNme = cmptNme;
		this.wdgtDesc = wdgtDesc;
		this.wdgtImg = wdgtImg;
	}

}