package com.fis.epo.ui.accelerator.api.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@JsonInclude(Include.NON_NULL)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 1, required = true, value = "Category Id")
	public Long catId;

	@Column(nullable = false)
	@ApiModelProperty(position = 2, required = true, value = "Category Name")
	public String catNme;

	@Column(nullable = false)
	@ApiModelProperty(position = 3, required = true, value = "Routing")
	public String rout;

	@Column(nullable = false)
	@ApiModelProperty(position = 4, required = true, value = "Is Deactive")
	public boolean isDactv;

	public void setisDactv(boolean isDactv) {
		this.isDactv = isDactv;
	}

	Category() {
	}

	public Category(String catNme, String rout, boolean isDactv) {
		this.catNme = catNme;
		this.rout = rout;
		this.isDactv = isDactv;
	}

}