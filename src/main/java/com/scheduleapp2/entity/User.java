package com.scheduleapp2.entity;

import com.scheduleapp2.dto.user.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules;

    public void updateNameAndEmail(UserUpdateRequestDto userUpdateRequestDto) {
        this.name = userUpdateRequestDto.name();
        this.email = userUpdateRequestDto.email();
    }
}
