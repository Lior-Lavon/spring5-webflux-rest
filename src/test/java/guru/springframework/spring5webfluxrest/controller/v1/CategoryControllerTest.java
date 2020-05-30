package guru.springframework.spring5webfluxrest.controller.v1;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static reactor.core.publisher.Mono.when;

class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    WebTestClient webTestClient;

    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // setting up WebTestClient
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void getAll() {

        Category cat1 = Category.builder().id("1").description("des1").build();
        Category cat2 = Category.builder().id("2").description("des1").build();
        BDDMockito.given(categoryService.findAll()).willReturn(Flux.just(cat1, cat2));

        webTestClient.get()
                .uri("/api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getById() {
        Category cat1 = Category.builder().id("1").description("des1").build();
        BDDMockito.given(categoryService.findById(anyString())).willReturn(Mono.just(cat1));

        webTestClient.get()
                .uri("/api/v1/categories/1")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    void postCategory() {

        Mono<Category> categoryMono = Mono.just(Category.builder().build());
        BDDMockito.given(categoryService.save(any(Category.class))).willReturn(categoryMono);

        Mono<Category> catToSaveMono = Mono.just(Category.builder().description("some description").build());

        webTestClient.post()
                .uri("/api/v1/categories")
                .body(catToSaveMono, Category.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void update() {

        Mono<Category> categoryMono = Mono.just(Category.builder().description("some value").build());
        BDDMockito.given(categoryService.update("1", Category.builder().build())).willReturn(categoryMono);

        webTestClient.put()
                .uri("/api/v1/categories/someid")
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Category.class);
    }

    @Test
    void patch() {

        Mono<Category> catToSaveMono = Mono.just(Category.builder().id("1").description("lavon").build());

        BDDMockito.given(categoryService.findById(anyString())).willReturn(Mono.just(Category.builder().build()));
        BDDMockito.given((categoryService.save(any(Category.class)))).willReturn(catToSaveMono);

        Mono<Category> catToUpdateMono = Mono.just(Category.builder().description("lavon").build());

        webTestClient.patch()
                .uri("/api/v1/categories/anyString")
                .body(catToUpdateMono, Category.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Category.class);
    }

}