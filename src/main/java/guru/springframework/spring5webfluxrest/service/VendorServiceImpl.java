package guru.springframework.spring5webfluxrest.service;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Flux<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Mono<Vendor> findById(String id) {
        return vendorRepository.findById(id);
    }

    @Override
    public Mono<Vendor> save(Vendor vendor) {
        vendor.setId(null);
        return vendorRepository.save(vendor);
    }

    @Override
    public Mono<Vendor> update(String id, Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @Override
    public Mono<Vendor> updateAttribute(String id, Vendor vendor) {

        return vendorRepository.findById(id)
                .flatMap(vendor1 -> {
                    if(vendor.getFirstName()!=null){
                        vendor1.setFirstName(vendor.getFirstName());
                    }
                    if(vendor.getLastName()!=null){
                        vendor1.setLastName(vendor.getLastName());
                    }
                    return vendorRepository.save(vendor1);
                });

    }
}
