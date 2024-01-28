package com.example.maplocationsreviews.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewReviewDTO {
    private float score;
    private String description;
}
