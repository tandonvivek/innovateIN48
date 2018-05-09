package com.fis.epo.ui.accelerator.api.core.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fis.epo.ui.accelerator.api.core.model.Widget;

public interface WidgetRepo extends CrudRepository<Widget, Long> {

	Widget findByWdgtId(Long id);

	List<Widget> findAllByOrderByWdgtNmeAsc();

	List<Widget> findByIsDactvOrderByWdgtNmeAsc(Boolean isActv);

	List<Widget> findByIsDactvAndWdgtNmeOrWdgtDescOrderByWdgtNmeAsc(Boolean isActv, String searchByName,
			String searchByDesc);

	List<Widget> findTop8ByIsDactvOrderByLstUsgTsDesc(Boolean isActv);

	List<Widget> findTop8ByIsDactvOrderByUsgCntDesc(Boolean isActv);

	List<Widget> findByCatIdOrderByWdgtNmeAsc(Long categoryId);

	List<Widget> findByCatIdAndIsDactvOrderByWdgtNmeAsc(Long categoryId, Boolean isActv);
}