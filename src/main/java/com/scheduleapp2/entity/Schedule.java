package com.scheduleapp2.entity;

import com.scheduleapp2.dto.ScheduleUpdateRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;
    private String title;
    private String content;

    public void updateTitleAndContent(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        this.title = scheduleUpdateRequestDto.getTitle();
        this.content = scheduleUpdateRequestDto.getContent();
    }
}