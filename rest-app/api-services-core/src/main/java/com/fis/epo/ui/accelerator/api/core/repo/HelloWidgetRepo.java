package com.fis.epo.ui.accelerator.api.core.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fis.epo.ui.accelerator.api.core.model.HelloWidget;

public interface HelloWidgetRepo extends CrudRepository<HelloWidget, Long> {

	List<HelloWidget> findAll();
}