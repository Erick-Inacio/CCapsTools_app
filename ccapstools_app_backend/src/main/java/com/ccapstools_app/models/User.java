package com.ccapstools_app.models;

import java.io.Serializable;
import java.util.Objects;

import com.ccapstools_app.utils.enums.Relationship;
import com.ccapstools_app.utils.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Schema(description = "Representa um usuário no sistema")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Identificador do usuário no firebase")
    @Column(name = "uid", unique = true)
    private String uid;

    @Schema(description = "Nome do usuário", maxLength=150)
    @Column(name = "name")
    private String name;

    @Schema(description = "Email do usuário", maxLength=100)
    @Column(name = "email", unique = true)
    private String email;

    @Schema(description = "Relação do usuário com a Instituição de Ensino")
    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name = "Relationship")
    private Relationship Relationship;

    @Schema(description = "Nivel de Acesso do Usuario")
    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Schema(description = "RA do aluno", maxLength = 13)
    @Column(name = "ra", unique = true, nullable = true)
    private String ra;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Relationship getRelationship() {
        return Relationship;
    }

    public void setRelationship(Relationship relationship) {
        Relationship = relationship;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((Relationship == null) ? 0 : Relationship.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((ra == null) ? 0 : ra.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(uid, user.uid) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Relationship == user.Relationship &&
                role == user.role &&
                Objects.equals(ra, user.ra);
    }

}
