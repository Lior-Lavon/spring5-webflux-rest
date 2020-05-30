package guru.springframework.spring5webfluxrest.service;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {

    Flux<Vendor> findAll();

    Mono<Vendor> findById(String id);

    Mono<Vendor> save(Vendor vendor);

    Mono<Vendor> update(String id, Vendor vendor);

    Mono<Vendor> updateAttribute(String id, Vendor vendor);

}
