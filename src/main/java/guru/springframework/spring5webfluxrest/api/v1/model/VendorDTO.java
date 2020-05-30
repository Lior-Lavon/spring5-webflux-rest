package guru.springframework.spring5webfluxrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    private String id;

    private String firstName;

    private String lastName;
}
