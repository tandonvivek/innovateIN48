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
public class CategoryWidgetRel {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 1, required = true, value = "Id")
	public Long id;

	@Column(nullable = false)
	@ApiModelProperty(position = 2, required = true, value = "Category Id")
	public Long catId;

	@Column(nullable = false)
	@JsonIgnore
	@ApiModelProperty(position = 3, required = true, value = "Widget Id")
	public Long wdgtId;

	@ApiModelProperty(position = 4, required = false, value = "Widget Extension")
	@ManyToOne
	@JoinColumn(name = "wdgtId", insertable = false, updatable = false)
	private WidgetExt widgetExt;

	public void setWdgtId(Long wdgtId) {
		this.wdgtId = wdgtId;
	}

	CategoryWidgetRel() {
	}

	public CategoryWidgetRel(Long catId, Long wdgtId) {
		this.catId = catId;
		this.wdgtId = wdgtId;
	}

}