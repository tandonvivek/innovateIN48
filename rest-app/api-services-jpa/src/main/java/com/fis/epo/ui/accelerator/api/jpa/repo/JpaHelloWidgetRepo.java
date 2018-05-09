package com.fis.epo.ui.accelerator.api.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fis.epo.ui.accelerator.api.core.model.HelloWidget;
import com.fis.epo.ui.accelerator.api.core.repo.HelloWidgetRepo;

public interface JpaHelloWidgetRepo extends HelloWidgetRepo, JpaRepository<HelloWidget, Long> {

}