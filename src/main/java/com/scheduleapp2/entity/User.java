package com.scheduleapp2.entity;

import com.scheduleapp2.dto.user.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateNameAndEmail(UserUpdateRequestDto userUpdateRequestDto) {
        this.name = userUpdateRequestDto.name();
        this.email = userUpdateRequestDto.email();
    }
}
