package com.akbal.gulgenclik.service.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionDTO implements Serializable {
    private Long id;
    private UserDTO user;
    private BilliardTableDTO billiardTable;
    private List<BuffetContentDTO> buffetContents;

    public SessionDTO() {
    }

    public SessionDTO(Long id, UserDTO user, BilliardTableDTO billiardTable, List<BuffetContentDTO> buffetContents) {
        this.id = id;
        this.user = user;
        this.billiardTable = billiardTable;
        this.buffetContents = buffetContents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BilliardTableDTO getBilliardTable() {
        return billiardTable;
    }

    public void setBilliardTable(BilliardTableDTO billiardTable) {
        this.billiardTable = billiardTable;
    }

    public List<BuffetContentDTO> getBuffetContents() {
        return buffetContents == null ? new ArrayList<>() : buffetContents;
    }

    public void setBuffetContents(List<BuffetContentDTO> buffetContents) {
        this.buffetContents = buffetContents;
    }
}
