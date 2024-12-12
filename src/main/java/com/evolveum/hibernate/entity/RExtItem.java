package com.evolveum.hibernate.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "m_ext_item")
public class RExtItem {

    private Integer id;

    private String name;

    private String type;

    public static class Key {

        public final String name;

        public final String type;

        public Key(String name, String type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Key)) {
                return false;
            }
            Key key = (Key) o;
            return Objects.equals(name, key.name) &&
                    Objects.equals(type, key.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type);
        }
    }

    // required by hibernate
    @SuppressWarnings("unused")
    public RExtItem() {
    }

    private RExtItem(Key key) {
        this.name = key.name;
        this.type = key.type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "itemName")
    public String getName() {
        return name;
    }

    @Column(name = "itemType")
    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RExtItem)) {
            return false;
        }

        RExtItem rExtItem = (RExtItem) o;
        return id.equals(rExtItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
