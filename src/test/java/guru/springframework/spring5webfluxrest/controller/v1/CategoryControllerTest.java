package guru.springframework.spring5webfluxrest.controller.v1;

import guru.springframework.spring5webfluxrest.domain.Category;
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

//    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

//        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
//                .build();

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

        Flux<Category> categoryFlux = Flux.just(Category.builder().build());
        BDDMockito.given(categoryService.saveAll(any(Publisher.class))).willReturn(categoryFlux);

        Mono<Category> catToSaveMono = Mono.just(Category.builder().description("some description").build());

        webTestClient.post()
                .uri("/api/v1/categories")
                .body(catToSaveMono, Category.class)
                .exchange()
                .expectStatus().isCreated();
    }
}