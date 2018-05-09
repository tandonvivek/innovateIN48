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

import com.fis.epo.ui.accelerator.api.core.model.Category;
import com.fis.epo.ui.accelerator.api.core.repo.CategoryRepo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

	private final CategoryRepo categoryRepo;

	@Autowired
	CategoryRestController(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@ApiOperation(value = "Add a new Category", nickname = "addCategory", notes = "Adding a new Category", produces = "application/json", response = Category.class)
	@PutMapping
	public ResponseEntity<Category> addCategory(@RequestBody Category newCategory) {
		Category savedCategory = categoryRepo.save(newCategory);
		return ResponseEntity.ok(savedCategory);
	}

	@ApiOperation(value = "Get All Categories", nickname = "GetAllCategories", notes = "Getting all Categories", produces = "application/json", response = Category.class)
	@GetMapping
	public ResponseEntity<List<Category>> readAllCategory(@RequestParam(required = false) boolean isDactv) {
		List<Category> result = null;

		if (isDactv) {
			result = categoryRepo.findByIsDactvOrderByCatNmeAsc(true);
		} else {
			result = categoryRepo.findByIsDactvOrderByCatNmeAsc(false);
		}

		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Get Category By Id", nickname = "GetCategory", notes = "Getting Category from Id", produces = "application/json", response = Category.class)
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> readCategory(@PathVariable long id) {
		Category result = categoryRepo.findByCatId(id);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Update a Category", nickname = "updateCategory", notes = "Updating a Category", produces = "application/json", response = Category.class)
	@PostMapping
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		Category result = categoryRepo.findByCatId(category.catId);

		if (result == null) {
			return ResponseEntity.notFound().build();
		}

		Category updateCategory = categoryRepo.save(category);
		return ResponseEntity.ok(updateCategory);
	}

	@ApiOperation(value = "Approve Pending Category", nickname = "AprrovePendingCategory", notes = "Approving Pending Category", produces = "application/json", response = Category.class)
	@PostMapping(value = "/{id}/approve")
	public ResponseEntity<Category> approvePendingCategory(@PathVariable long id) {
		Category category = categoryRepo.findByCatId(id);
		category.setisDactv(false);
		Category result = categoryRepo.save(category);

		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Delete a Category", nickname = "deleteCategory", notes = "Deleting a Category", produces = "application/json")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable long id) {
		Category result = categoryRepo.findByCatId(id);

		if (result == null) {
			return ResponseEntity.notFound().build();
		}

		categoryRepo.delete(id);
		return ResponseEntity.ok().build();
	}

}