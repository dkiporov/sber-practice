package ru.sbrf.practice.transaction.entity;

import javax.persistence.*;

@Entity
public class Mentor {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    public Mentor() {
    }

    public Mentor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
