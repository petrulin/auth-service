package com.otus.authservice.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "role", schema = "auth_service", catalog = "postgres")
public class Role extends BaseIdEntity implements Serializable {
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_role", schema = "auth_service", catalog = "postgres", joinColumns =  {
        @JoinColumn(name = "role_id", referencedColumnName = "id")
    }
    , inverseJoinColumns =  {
        @JoinColumn(name = "permission_id", referencedColumnName = "id")
    }
    )
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
