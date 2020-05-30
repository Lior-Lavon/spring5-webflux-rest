package guru.springframework.spring5webfluxrest.controller.v1;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void findAll() {

        Vendor v1 = Vendor.builder().id("1").lastName("n1").lastName("l1").build();
        Vendor v2 = Vendor.builder().id("1").lastName("n2").lastName("l2").build();

        BDDMockito.given(vendorService.findAll()).willReturn(Flux.just(v1, v2));

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void findById() {
        Vendor v1 = Vendor.builder().id("1").lastName("n1").lastName("l1").build();

        BDDMockito.given(vendorService.findById(anyString())).willReturn(Mono.just(v1));

        webTestClient.get()
                .uri("/api/v1/vendors/anystring")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Vendor.class);

    }

    @Test
    void postVendor(){

        BDDMockito.given(vendorService.save(any(Vendor.class))).willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> venToSaveMono = Mono.just(Vendor.builder().firstName("lior").lastName("lavon").build());

        webTestClient.post()
                .uri("/api/v1/vendors")
                .body(venToSaveMono, Vendor.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void update() {

        BDDMockito.given(vendorService.update(anyString(), any(Vendor.class))).willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> venToUpdateMono = Mono.just(Vendor.builder().firstName("lior").lastName("lavon").build());

        webTestClient.put()
                .uri("/api/v1/vendors/anyString")
                .body(venToUpdateMono, Vendor.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Vendor.class);
    }

    @Test
    void patch() {

        Mono<Vendor> venToSaveMono = Mono.just(Vendor.builder().id("1").firstName("lior").lastName("lavon").build());

        BDDMockito.given(vendorService.findById(anyString())).willReturn(Mono.just(Vendor.builder().build()));
        BDDMockito.given((vendorService.save(any(Vendor.class)))).willReturn(venToSaveMono);

        Mono<Vendor> venToUpdateMono = Mono.just(Vendor.builder().firstName("lior").lastName("lavon").build());

        webTestClient.patch()
                .uri("/api/v1/vendors/anyString")
                .body(venToUpdateMono, Vendor.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Vendor.class);
    }

}