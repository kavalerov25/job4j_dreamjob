package ru.job4j.dreamjob.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post implements Serializable {
    private int id;
    private String name;
    private String description;
    private LocalDate created;
    private City city;
    private boolean visible;
    private int cityID;

    public Post(int id, String name, String description, LocalDate created, boolean visible,  int cityID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.visible = visible;
        this.cityID = cityID;
    }


    public Post(int id, String name, int cityID) {
        this.id = id;
        this.name = name;
        this.cityID = cityID;
    }

    private Post() {
    }

    public Post(int id, String name, String description, LocalDate created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Post(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Post(int id, String name, String description, boolean visible, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.city = city;

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && Objects.equals(name, post.name) && Objects.equals(description, post.description) && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }

    @Override
    public String toString() {
        return "Post{"
               + "id=" + id
               + ", name='" + name + '\''
               + ", description='" + description + '\''
               + ", created=" + created + '\''
               + ", city=" + city + '\''
               + ", visible=" + visible + '\''
               + ", cityID=" + cityID + '\''
               + '}';
    }
}