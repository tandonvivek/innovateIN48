package com.fis.epo.ui.accelerator.api.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fis.epo.ui.accelerator.api.core.model.CategoryWidgetRel;
import com.fis.epo.ui.accelerator.api.core.repo.CategoryWidgetRelRepo;

@Transactional(readOnly = true)
public interface JpaCategoryWidgetRelRepo extends CategoryWidgetRelRepo, JpaRepository<CategoryWidgetRel, Long> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CategoryWidgetRel WHERE wdgt_id = :wdgtId")
	void deleteInBulkByWdgtId(@Param("wdgtId") Long wdgtId);

}