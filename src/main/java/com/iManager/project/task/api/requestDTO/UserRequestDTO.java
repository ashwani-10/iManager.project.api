package com.iManager.project.task.api.requestDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
    UUID id;
    String name;
    String Email;
    String password;
    String role;
    UUID orgId;

    public UserRequestDTO() {
    }

    public UserRequestDTO(UUID id, String name, String email, String password,
                          String role, UUID orgId) {
        this.id = id;
        this.name = name;
        Email = email;
        this.password = password;
        this.role = role;
        this.orgId = orgId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getOrgId() {
        return orgId;
    }

    public void setOrgId(UUID orgId) {
        this.orgId = orgId;
    }
}
