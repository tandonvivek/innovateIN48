package com.fis.epo.ui.accelerator.api.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fis.epo.ui.accelerator.api.core.model.HelloWidget;
import com.fis.epo.ui.accelerator.api.core.repo.HelloWidgetRepo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hellowidget")
public class HelloWidgetRestController {

	private final HelloWidgetRepo helloWidgetRepo;

	@Autowired
	HelloWidgetRestController(HelloWidgetRepo helloWidgetRepo) {
		this.helloWidgetRepo = helloWidgetRepo;
	}

	@ApiOperation(value = "Get All Messages", nickname = "GetAllMessages", notes = "Getting all Messages", produces = "application/json", response = HelloWidget.class)
	@GetMapping
	public ResponseEntity<List<HelloWidget>> readAllCategory() {
		List<HelloWidget> result = helloWidgetRepo.findAll();
		return ResponseEntity.ok(result);
	}

}