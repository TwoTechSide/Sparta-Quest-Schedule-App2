package com.scheduleapp2.entity;

import com.scheduleapp2.dto.schedule.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void assignUser(User user) {
        this.user = user;
    }

    public void updateTitleAndContent(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        this.title = scheduleUpdateRequestDto.title();
        this.content = scheduleUpdateRequestDto.content();
    }
}