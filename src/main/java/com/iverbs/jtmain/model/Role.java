package com.iverbs.jtmain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="roles")
@Data
public class Role extends BaseEntity {

    @Column(name="name")
    private String name;
    
    @ManyToMany(mappedBy="roles", fetch=FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role [name=" + name + ", getId()=" + getId() + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
