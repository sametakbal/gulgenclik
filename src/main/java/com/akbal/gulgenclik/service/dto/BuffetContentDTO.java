package com.akbal.gulgenclik.service.dto;

import com.akbal.gulgenclik.domain.BuffetContent;

import java.io.Serializable;

public class BuffetContentDTO implements Serializable {

    private Long id;
    private String name;
    private Float price;

    public BuffetContentDTO() {
    }

    public BuffetContentDTO(Long id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public BuffetContentDTO(BuffetContent entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
