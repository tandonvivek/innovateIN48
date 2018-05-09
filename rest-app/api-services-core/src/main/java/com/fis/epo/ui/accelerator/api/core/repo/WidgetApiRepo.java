package com.fis.epo.ui.accelerator.api.core.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fis.epo.ui.accelerator.api.core.model.WidgetApi;

public interface WidgetApiRepo extends CrudRepository<WidgetApi, Long> {

	List<WidgetApi> findByWdgtId(Long id);

	void deleteInBulkByWdgtId(Long wdgtId);

}