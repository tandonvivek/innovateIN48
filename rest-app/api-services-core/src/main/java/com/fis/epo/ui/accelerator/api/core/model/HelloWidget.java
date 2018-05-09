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
public class HelloWidget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 1, required = true, value = "Id")
	public Long id;

	@Column(nullable = false)
	@ApiModelProperty(position = 2, required = true, value = "Message")
	public String msg;

	@ApiModelProperty(position = 3, required = false, value = "Image")
	public byte[] img;

	HelloWidget() {
	}

	public HelloWidget(String msg, byte[] img) {
		this.msg = msg;
		this.img = img;
	}

}