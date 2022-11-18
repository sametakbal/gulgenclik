package com.akbal.gulgenclik.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import org.hibernate.annotations.Cache;

@Entity
@Table(name = "session")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Session  extends AbstractAuditingEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="table_id", nullable=false)
    private BilliardTable billiardTable;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "session_buffet_contents",
        joinColumns = { @JoinColumn(name = "session_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "buffet_content_id", referencedColumnName = "id") }
    )
    private List<BuffetContent> buffetContents;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BilliardTable getBilliardTable() {
        return billiardTable;
    }

    public void setBilliardTable(BilliardTable billiardTable) {
        this.billiardTable = billiardTable;
    }

    public List<BuffetContent> getBuffetContents() {
        return buffetContents;
    }

    public void setBuffetContents(List<BuffetContent> buffetContents) {
        this.buffetContents = buffetContents;
    }
}
