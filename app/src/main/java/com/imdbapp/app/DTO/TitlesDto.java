package com.imdbapp.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TitlesDto {
    private String tconst;
    private String originalTitle;
    private Long releaseYear;
    private List<String> genres;

}
