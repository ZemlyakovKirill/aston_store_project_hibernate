package ru.themlyakov.store_project_hibernate.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Consumer implements EntitySupport<Consumer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String contactInfo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id")
    private Set<Order> orderedProducts;

    @Builder
    public Consumer(Long id,String firstName, String lastName, String middleName, String address, String contactInfo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.contactInfo = contactInfo;
    }
}
