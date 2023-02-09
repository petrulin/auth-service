package com.otus.authservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "permission", schema = "auth_service", catalog = "postgres")
public class Permission extends BaseIdEntity implements Serializable {
    private String name;

    public Permission() {
    }

    public Permission(long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
