package ru.themlyakov.store_project_hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.themlyakov.store_project_hibernate.utils.EntitySupport;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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
    private List<Attribute> subAttributes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Attribute parent;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    public Attribute(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Attribute(Long id, Key key, Value value, Attribute attribute) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.subAttributes = attribute.subAttributes;
    }

    @Override
    public Number getId() {
        return id;
    }
}
