package ru.themlyakov.store_project_hibernate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Supplier implements EntitySupport<Supplier> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @NonNull
    private String name;

    @NotEmpty
    @NotNull
    @NonNull
    private String address;

    @NotEmpty
    @NotNull
    @NonNull
    private String contact_info;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Supply> supplies;

    public Supplier(Long id, String name, String address, String contact_info) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact_info = contact_info;
    }
}
