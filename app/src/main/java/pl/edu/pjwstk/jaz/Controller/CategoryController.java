package pl.edu.pjwstk.jaz.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.Entity.CategoryEntity;
import pl.edu.pjwstk.jaz.ObjectNotFoundException;
import pl.edu.pjwstk.jaz.Request.CategoryRequest;
import pl.edu.pjwstk.jaz.Service.CategoryService;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/{name}")
    public ResponseEntity<?> getSectionName(@PathVariable("name") String name) {
        CategoryEntity categoryEntity = categoryService.getCategoryByName(name).orElseThrow(() -> new ObjectNotFoundException("Category not found"));
        return ResponseEntity.ok(categoryEntity);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/sections/{sectionName}/addCategory")
    public void createCategory(@RequestBody CategoryRequest request, @PathVariable("sectionName") String sectionName) {
        categoryService.createCategory(request.getName(), sectionName);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PatchMapping("/updateCategory/{oldName}")
    public void updateCategory(@PathVariable("oldName") String oldName, @RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(oldName, categoryRequest.getName());
    }

}
