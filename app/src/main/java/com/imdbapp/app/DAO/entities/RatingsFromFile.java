package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingsFromFile {
    private String tconst;
    private String averageRating;
    private String numVotes;
}
