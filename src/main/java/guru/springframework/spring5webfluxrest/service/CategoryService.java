package guru.springframework.spring5webfluxrest.service;

import guru.springframework.spring5webfluxrest.api.v1.model.CategoryDTO;
import guru.springframework.spring5webfluxrest.domain.Category;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<Category> findAll();

    Mono<Category> findById(String id);

    Flux<Category> saveAll(Publisher<Category> categoryStream);

    Mono<Category> update(String id, Category category);
}
