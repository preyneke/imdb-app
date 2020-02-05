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
public class KnownForActorsByTitleDto {
    private String primaryTitle;
    private List<String> knownCastAndCrew;
}
