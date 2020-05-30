package guru.springframework.spring5webfluxrest.controller.v1;

import guru.springframework.spring5webfluxrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5webfluxrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.service.CategoryService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<Category> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> getById(@PathVariable String id){
        return categoryService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Category> create(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> update(@PathVariable String id, @RequestBody Category category){
        return categoryService.update(id, category);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> updateAttribute(@PathVariable String id, @RequestBody Category category){
        return categoryService.updateAttribute(id, category);
    }
}
