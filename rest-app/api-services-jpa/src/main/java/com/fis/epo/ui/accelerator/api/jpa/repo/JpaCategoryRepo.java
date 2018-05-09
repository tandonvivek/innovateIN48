package com.fis.epo.ui.accelerator.api.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fis.epo.ui.accelerator.api.core.model.Category;
import com.fis.epo.ui.accelerator.api.core.repo.CategoryRepo;

public interface JpaCategoryRepo extends CategoryRepo, JpaRepository<Category, Long> {

}