package com.fis.epo.ui.accelerator.api.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fis.epo.ui.accelerator.api.core.model.WidgetExt;
import com.fis.epo.ui.accelerator.api.core.repo.WidgetExtRepo;

public interface JpaWidgetExtRepo extends WidgetExtRepo, JpaRepository<WidgetExt, Long> {

}