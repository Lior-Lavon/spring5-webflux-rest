package guru.springframework.spring5webfluxrest.controller.v1;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.service.VendorService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<Vendor> findAll(){
        return vendorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> findById(@PathVariable String id){
        return vendorService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Vendor> save(@RequestBody Vendor vendor){
        return vendorService.save(vendor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> update(@PathVariable String id, @RequestBody Vendor vendor){
        return vendorService.update(id, vendor);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> updateAttribute(@PathVariable String id, @RequestBody Vendor vendor){
        return vendorService.updateAttribute(id, vendor);
    }

}
