package com.fis.epo.ui.accelerator.api.core.repo;

import org.springframework.data.repository.CrudRepository;

import com.fis.epo.ui.accelerator.api.core.model.WidgetExt;

public interface WidgetExtRepo extends CrudRepository<WidgetExt, Long> {

	WidgetExt findByWdgtId(Long id);

}