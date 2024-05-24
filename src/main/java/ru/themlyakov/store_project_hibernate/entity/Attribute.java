package ru.themlyakov.store_project_hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attribute implements EntitySupport<Attribute> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = false)
    private Key key;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = false)
    private Value value;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Attribute> subAttributes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attribute parent;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @Override
    public Number getId() {
        return id;
    }
}
