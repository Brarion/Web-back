package ru.brarion.steamlikeappapi.business.entity;

import liquibase.pro.packaged.C;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity(name = "app_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "UserUUIDGenerator")
    @GenericGenerator(name = "UserUUIDGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;

    @Column(unique = true)
    @EqualsAndHashCode.Include
    String username;

    @Column
    String password;

    @NotNull
    @Column
    Integer cache;

    @Column
    boolean accountNonExpired;

    @Column
    boolean accountNonLocked;

    @Column
    boolean credentialsNonExpired;

    @Column
    boolean enabled;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_user_authority",
            joinColumns = @JoinColumn(name = "app_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
    Set<Authority> authorities;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "app_user_game",
            joinColumns = @JoinColumn(name = "app_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    Set<Game> boughtGames;

    @Override
    public Collection<Authority> getAuthorities() {
        return authorities;
    }
}
