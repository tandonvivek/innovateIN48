package com.fis.epo.ui.accelerator.api.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.epo.ui.accelerator.api.core.model.CategoryWidgetRel;
import com.fis.epo.ui.accelerator.api.core.model.WidgetApi;
import com.fis.epo.ui.accelerator.api.core.model.Widget;
import com.fis.epo.ui.accelerator.api.core.model.WidgetExt;
import com.fis.epo.ui.accelerator.api.core.repo.CategoryWidgetRelRepo;
import com.fis.epo.ui.accelerator.api.core.repo.WidgetApiRepo;
import com.fis.epo.ui.accelerator.api.core.repo.WidgetExtRepo;
import com.fis.epo.ui.accelerator.api.core.repo.WidgetRepo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/widgets")
public class WidgetRestController {

	private final WidgetRepo widgetRepo;
	private final WidgetApiRepo widgetApiRepo;
	private final CategoryWidgetRelRepo catWdgtRelRepo;
	private final WidgetExtRepo widgetExtRepo;

	@Autowired
	WidgetRestController(WidgetRepo widgetRepo, WidgetApiRepo widgetApiRepo, CategoryWidgetRelRepo catWdgtRelRepo,
			WidgetExtRepo widgetExtRepo) {
		this.widgetRepo = widgetRepo;
		this.widgetApiRepo = widgetApiRepo;
		this.catWdgtRelRepo = catWdgtRelRepo;
		this.widgetExtRepo = widgetExtRepo;
	}

	@ApiOperation(value = "Add a Widget", nickname = "addWidget", notes = "Adding a Widget", produces = "application/json", response = WidgetExt.class)
	@PutMapping
	public ResponseEntity<WidgetExt> addWidget(@RequestBody WidgetExt newWidget) {
		Widget savedWidget = widgetRepo.save(newWidget.widget);
		Long wdgtId = savedWidget.wdgtId;
		for (WidgetApi widgetApi : newWidget.widgetApi) {
			widgetApi.setWdgtId(wdgtId);
			widgetApiRepo.save(widgetApi);
		}
		for (CategoryWidgetRel categoryWidgetRel : newWidget.categoryWidgetRel) {
			categoryWidgetRel.setWdgtId(wdgtId);
			catWdgtRelRepo.save(categoryWidgetRel);
		}
		WidgetExt newWidgetExt = new WidgetExt(wdgtId);
		WidgetExt savedWidgetExt = widgetExtRepo.save(newWidgetExt);
		savedWidgetExt.setWidget(savedWidget);
		savedWidgetExt.setWidgetApi(newWidget.widgetApi);
		savedWidgetExt.setCategoryWidgetRel(newWidget.categoryWidgetRel);

		return ResponseEntity.ok(savedWidgetExt);
	}

	@ApiOperation(value = "Get Frequently Used Widgets / Widget By Category Id / Search Widget By Name or Description", nickname = "GetMostlyUsed", notes = "Getting mostly used Widgets", produces = "application/json", response = Widget.class)
	@GetMapping
	public ResponseEntity<List<Widget>> getWidget(@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false) String name, @RequestParam(required = false) boolean isDactv,
			@RequestParam(required = false) String param) {
		List<Widget> result = null;

		if (categoryId != null) {
			result = widgetRepo.findByCatIdAndIsDactvOrderByWdgtNmeAsc(categoryId, false);
		} else if (name != null) {
			result = widgetRepo.findByIsDactvAndWdgtNmeOrWdgtDescOrderByWdgtNmeAsc(false, name, name);
		} else if ("all".equalsIgnoreCase(param)) {
			if (isDactv) {
				result = widgetRepo.findByIsDactvOrderByWdgtNmeAsc(true);
			} else {
				result = widgetRepo.findByIsDactvOrderByWdgtNmeAsc(false);
			}
		} else if ("mstRcntUsed".equalsIgnoreCase(param)) {
			result = widgetRepo.findTop8ByIsDactvOrderByLstUsgTsDesc(false);
		} else if (isDactv) {
			result = widgetRepo.findByIsDactvOrderByWdgtNmeAsc(true);
		} else {
			result = widgetRepo.findTop8ByIsDactvOrderByUsgCntDesc(false);
		}

		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Get Widget By Id", nickname = "GetWidget", notes = "Getting Widget by Id", produces = "application/json", response = Widget.class)
	@GetMapping(value = "/{id}")
	public ResponseEntity<Widget> readWidget(@PathVariable long id) {
		Widget result = widgetRepo.findByWdgtId(id);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Get Widget API By Id", nickname = "GetWidgetAPI", notes = "Getting Widget API by Id", produces = "application/json", response = WidgetApi.class)
	@GetMapping(value = "/{id}/api")
	public ResponseEntity<List<WidgetApi>> readWidgetApi(@PathVariable long id) {
		List<WidgetApi> result = widgetApiRepo.findByWdgtId(id);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Get Widget Extension By Id", nickname = "GetWidgetExtension", notes = "Getting Widget Extension by Id", produces = "application/json", response = WidgetExt.class)
	@GetMapping(value = "/{id}/ext")
	public ResponseEntity<WidgetExt> readWidgetExt(@PathVariable long id) {
		WidgetExt result = widgetExtRepo.findByWdgtId(id);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Update a Widget", nickname = "UpdateWidget", notes = "Updatting a Widget", produces = "application/json", response = WidgetExt.class)
	@PostMapping
	public ResponseEntity<WidgetExt> updateWidget(@RequestBody WidgetExt newWidget) {
		Widget savedWidget = widgetRepo.save(newWidget.widget);
		Long wdgtId = savedWidget.wdgtId;
		widgetApiRepo.deleteInBulkByWdgtId(wdgtId);
		for (WidgetApi widgetApi : newWidget.widgetApi) {
			widgetApi.setWdgtId(wdgtId);
			widgetApiRepo.save(widgetApi);
		}
		catWdgtRelRepo.deleteInBulkByWdgtId(wdgtId);
		for (CategoryWidgetRel categoryWidgetRel : newWidget.categoryWidgetRel) {
			categoryWidgetRel.setWdgtId(wdgtId);
			catWdgtRelRepo.save(categoryWidgetRel);
		}
		WidgetExt newWidgetExt = new WidgetExt(wdgtId);
		newWidgetExt.setWidget(savedWidget);
		newWidgetExt.setWidgetApi(newWidget.widgetApi);
		newWidgetExt.setCategoryWidgetRel(newWidget.categoryWidgetRel);

		return ResponseEntity.ok(newWidgetExt);
	}

	@ApiOperation(value = "Approve Pending Widget", nickname = "AprrovePendingWidget", notes = "Approving Pending Widget", produces = "application/json", response = Widget.class)
	@PostMapping(value = "/{id}/approve")
	public ResponseEntity<Widget> approvePendingWidget(@PathVariable long id) {
		Widget widget = widgetRepo.findByWdgtId(id);
		widget.setisDactv(false);
		Widget result = widgetRepo.save(widget);

		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Delete a Widget", nickname = "DeleteWidget", notes = "Deleting a Widget", produces = "application/json")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Widget> deleteWidget(@PathVariable long id) {
		Widget widget = widgetRepo.findByWdgtId(id);

		if (widget == null) {
			return ResponseEntity.notFound().build();
		}

		widgetRepo.delete(id);
		return ResponseEntity.ok().build();
	}

}