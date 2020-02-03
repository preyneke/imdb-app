package com.imdbapp.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrewDto {
    private String nconst;
    private String primaryName;
    private String creditedAs;
}
