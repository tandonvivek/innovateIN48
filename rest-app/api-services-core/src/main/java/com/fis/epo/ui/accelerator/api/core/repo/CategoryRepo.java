package com.fis.epo.ui.accelerator.api.core.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fis.epo.ui.accelerator.api.core.model.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {

	Category findByCatId(Long id);

	List<Category> findAllByOrderByCatNmeAsc();

	List<Category> findByIsDactvOrderByCatNmeAsc(Boolean isActv);
}