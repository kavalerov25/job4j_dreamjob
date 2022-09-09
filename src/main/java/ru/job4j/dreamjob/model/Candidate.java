package ru.job4j.dreamjob.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Candidate implements Serializable {

    private int id;
    private String name;
    private String description;
    private LocalDate created;
    private City city;
    private byte[] photo;
    private boolean visible;
    private int cityID;

    public Candidate() {
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate(int id, String name, String description, LocalDate created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Candidate(int id, String name, String description, LocalDate created, boolean visible, int city_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.visible = visible;
        this.cityID = cityID;
    }

    public Candidate(int id, String name, String description, LocalDate created, byte[] photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.photo = photo;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Candidate{"
               + "id=" + id
               + ", name='" + name + '\''
               + ", description='" + description + '\''
               + ", created=" + created
               + '}';
    }
}