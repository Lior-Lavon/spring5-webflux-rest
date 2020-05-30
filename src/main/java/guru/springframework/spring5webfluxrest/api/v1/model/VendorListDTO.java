package guru.springframework.spring5webfluxrest.api.v1.model;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorListDTO {

    private Flux<VendorDTO> vendors;
}
