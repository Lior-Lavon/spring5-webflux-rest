package guru.springframework.spring5webfluxrest.service;

import guru.springframework.spring5webfluxrest.api.v1.mapper.CategoryMapper;
import guru.springframework.spring5webfluxrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Mono<Category> save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Mono<Category> update(String id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Mono<Category> updateAttribute(String id, Category category) {

        return categoryRepository.findById(id)
                .flatMap(category1 -> {
                    if(category.getDescription()!=null){
                        category1.setDescription(category.getDescription());
                    }
                    return categoryRepository.save(category1);
                });
    }

}
