package com.akbal.gulgenclik.service.dto;

import com.akbal.gulgenclik.domain.BilliardTable;

import java.io.Serializable;

public class BilliardTableDTO implements Serializable {
    private Long id;
    private String name;
    private Float price;
    private Float totalSessionCost;
    private Boolean isUsing;
    private Integer hour;
    private Integer minute;

    public BilliardTableDTO(BilliardTable entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.isUsing = entity.getUsing();
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

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean using) {
        isUsing = using;
    }

    public Float getTotalSessionCost() {
        return totalSessionCost;
    }

    public void setTotalSessionCost(Float totalSessionCost) {
        this.totalSessionCost = totalSessionCost;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }
}
