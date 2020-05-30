package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count().block()>0)
            categoryRepository.deleteAll().block();
        loadCategories();

        if(vendorRepository.count().block()>0)
            vendorRepository.deleteAll().block();
        LoadVendors();
    }

    private void loadCategories() {
        Category fruit = new Category();
        fruit.setDescription("Fruit");

        Category dried = new Category();
        dried.setDescription("Dried");

        Category fresh = new Category();
        fresh.setDescription("Fresh");

        Category exotic = new Category();
        exotic.setDescription("Exotic");

        Category nuts = new Category();
        nuts.setDescription("Nuts");

        categoryRepository.save(fruit).block();
        categoryRepository.save(dried).block();
        categoryRepository.save(fresh).block();
        categoryRepository.save(exotic).block();
        categoryRepository.save(nuts).block();

        log.info("Categories loaded : " + categoryRepository.count().block());
    }

    private void LoadVendors(){

        Vendor v1 = Vendor.builder().firstName("Joe").lastName("Buck").build();
        Vendor v2 = Vendor.builder().firstName("Micheal").lastName("Watson").build();
        Vendor v3 = Vendor.builder().firstName("Jessie").lastName("Waters").build();
        Vendor v4 = Vendor.builder().firstName("Bill").lastName("Narshi").build();
        Vendor v5 = Vendor.builder().firstName("Jimmy").lastName("Buffett").build();

        vendorRepository.save(v1).block();
        vendorRepository.save(v2).block();
        vendorRepository.save(v3).block();
        vendorRepository.save(v4).block();
        vendorRepository.save(v5).block();

        log.info("Vendors Loader finished : " + vendorRepository.count().block());
    }

}
