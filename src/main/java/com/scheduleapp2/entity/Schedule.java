package com.scheduleapp2.entity;

import com.scheduleapp2.dto.schedule.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseDateTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments;

    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void assignUser(User user) {
        this.user = user;
    }

    public void updateTitleAndContent(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        this.title = scheduleUpdateRequestDto.title();
        this.content = scheduleUpdateRequestDto.content();
    }
}