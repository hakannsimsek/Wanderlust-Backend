package com.example.signinandup.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String username;
    //TODO: ProfilPhoto
    private String profilPhoto;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserFriendship",
            joinColumns = {@JoinColumn(name = "UserId")},
            inverseJoinColumns = {@JoinColumn(name = "FriendId")})
    private List<User> friends = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "friends")
    private List<User> followed = new ArrayList<>();

    public void addFriend(User friend){
        if(friends==null)
            friends = new ArrayList<>();
        friends.add(friend);
    }

    public User(String name, String surname,String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
