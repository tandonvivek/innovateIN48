package com.fis.epo.ui.accelerator.api.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fis.epo.ui.accelerator.api.core.model.WidgetApi;
import com.fis.epo.ui.accelerator.api.core.repo.WidgetApiRepo;

@Transactional(readOnly = true)
public interface JpaWidgetApiRepo extends WidgetApiRepo, JpaRepository<WidgetApi, Long> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM WidgetApi WHERE wdgt_id = :wdgtId")
	void deleteInBulkByWdgtId(@Param("wdgtId") Long wdgtId);

}