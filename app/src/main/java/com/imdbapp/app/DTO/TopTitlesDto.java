package com.imdbapp.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopTitlesDto {
private Long number;
    private String tconst;
    private String originalTitle;
    private Double averageRating;
    private Long numberOfVotes;

}
