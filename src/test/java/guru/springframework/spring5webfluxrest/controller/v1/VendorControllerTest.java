package guru.springframework.spring5webfluxrest.controller.v1;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
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
                .uri("api/v1/vendors/anystring")
                .exchange()
                .expectBody(Vendor.class);

    }
}