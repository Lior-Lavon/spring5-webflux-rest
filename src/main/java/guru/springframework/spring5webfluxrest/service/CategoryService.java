package guru.springframework.spring5webfluxrest.service;

import guru.springframework.spring5webfluxrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<Category> findAll();

    Mono<Category> findById(String id);

    Mono<Category> save(Category category);

    Mono<Category> update(String id, Category category);

    Mono<Category> updateAttribute(String id, Category category);
}
