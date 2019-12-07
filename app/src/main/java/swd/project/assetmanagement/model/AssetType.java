package swd.project.assetmanagement.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class AssetType implements Serializable {
    Long id;
    String name;
    String description;
    Department department;

    public AssetType() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
