package com.scheduleapp2.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void withUserAndSchedule(User user, Schedule schedule) {
        this.user = user;
        this.schedule = schedule;
    }
}
