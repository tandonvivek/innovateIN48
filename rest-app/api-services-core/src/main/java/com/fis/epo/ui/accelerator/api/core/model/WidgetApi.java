package com.fis.epo.ui.accelerator.api.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@JsonInclude(Include.NON_NULL)
public class WidgetApi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@ApiModelProperty(position = 1, required = true, value = "Id")
	public Long id;

	@Column(nullable = false)
	@ApiModelProperty(position = 2, required = true, value = "Widget Id")
	public Long wdgtId;

	@Column(nullable = false)
	@ApiModelProperty(position = 3, required = true, value = "API Operation")
	public String apiOper;

	@Column(nullable = false)
	@ApiModelProperty(position = 4, required = true, value = "API Name")
	public String apiNme;

	@Column(nullable = false)
	@ApiModelProperty(position = 5, required = true, value = "API Endpoint")
	public String apiEndPt;

	@ApiModelProperty(position = 6, required = false, value = "API Store")
	public String apiStore;

	@ApiModelProperty(position = 7, required = false, value = "Widget Extension")
	@ManyToOne
	@JoinColumn(name = "wdgtId", insertable = false, updatable = false)
	private WidgetExt widgetExt;

	public void setWdgtId(Long wdgtId) {
		this.wdgtId = wdgtId;
	}

	WidgetApi() {
	}

	public WidgetApi(Long wdgtId, String apiOper, String apiNme, String apiEndPt, String apiStore) {
		this.wdgtId = wdgtId;
		this.apiOper = apiOper;
		this.apiNme = apiNme;
		this.apiEndPt = apiEndPt;
		this.apiStore = apiStore;
	}

}