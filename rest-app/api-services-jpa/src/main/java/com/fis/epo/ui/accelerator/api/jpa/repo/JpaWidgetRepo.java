package com.fis.epo.ui.accelerator.api.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fis.epo.ui.accelerator.api.core.model.Widget;
import com.fis.epo.ui.accelerator.api.core.repo.WidgetRepo;

public interface JpaWidgetRepo extends WidgetRepo, JpaRepository<Widget, Long> {

	@Query(value = "SELECT DISTINCT * FROM widget WHERE is_dactv = :isDactv AND (wdgt_nme ILIKE %:searchByName% OR wdgt_desc ILIKE %:searchByDesc%) ORDER BY wdgt_nme ASC", nativeQuery = true)
	List<Widget> findByIsDactvAndWdgtNmeOrWdgtDescOrderByWdgtNmeAsc(@Param("isDactv") Boolean isDactv,
			@Param("searchByName") String searchByName, @Param("searchByDesc") String searchByDesc);

	@Query(value = "SELECT widget.* FROM category_widget_rel, widget WHERE category_widget_rel.wdgt_id = widget.wdgt_id AND category_widget_rel.cat_id = :categoryId ORDER BY widget.wdgt_nme ASC", nativeQuery = true)
	List<Widget> findByCatIdOrderByWdgtNmeAsc(@Param("categoryId") Long categoryId);

	@Query(value = "SELECT widget.* FROM category_widget_rel, widget WHERE category_widget_rel.wdgt_id = widget.wdgt_id AND category_widget_rel.cat_id = :categoryId AND widget.is_dactv = :isDactv ORDER BY widget.wdgt_nme ASC", nativeQuery = true)
	List<Widget> findByCatIdAndIsDactvOrderByWdgtNmeAsc(@Param("categoryId") Long categoryId,
			@Param("isDactv") Boolean isDactv);

}