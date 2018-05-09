package com.fis.epo.ui.accelerator.api.core.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@JsonInclude(Include.NON_NULL)
public class WidgetExt {

	@Id
	@JsonIgnore
	@ApiModelProperty(position = 1, required = true, value = "Widget Id")
	public Long wdgtId;

	@ApiModelProperty(position = 2, required = true, value = "Widget")
	@OneToOne(mappedBy = "widgetExt")
	public Widget widget;

	@ApiModelProperty(position = 3, required = true, value = "Widget API")
	@OneToMany(mappedBy = "widgetExt")
	public List<WidgetApi> widgetApi;

	@ApiModelProperty(position = 4, required = true, value = "Category Widget Relation")
	@OneToMany(mappedBy = "widgetExt")
	public List<CategoryWidgetRel> categoryWidgetRel;

	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	public void setWidgetApi(List<WidgetApi> widgetApi) {
		this.widgetApi = widgetApi;
	}

	public void setCategoryWidgetRel(List<CategoryWidgetRel> categoryWidgetRel) {
		this.categoryWidgetRel = categoryWidgetRel;
	}

	WidgetExt() {
	}

	public WidgetExt(Long wdgtId) {
		this.wdgtId = wdgtId;
	}

}