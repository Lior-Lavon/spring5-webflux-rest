package guru.springframework.spring5webfluxrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListDTO {

    private Flux<CategoryDTO> categories;
}
