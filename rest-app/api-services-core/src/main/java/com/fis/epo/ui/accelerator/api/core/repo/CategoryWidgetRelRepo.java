package com.fis.epo.ui.accelerator.api.core.repo;

import org.springframework.data.repository.CrudRepository;

import com.fis.epo.ui.accelerator.api.core.model.CategoryWidgetRel;

public interface CategoryWidgetRelRepo extends CrudRepository<CategoryWidgetRel, Long> {

	void deleteInBulkByWdgtId(Long wdgtId);

}