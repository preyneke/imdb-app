package com.imdbapp.app.services;

import com.imdbapp.app.DTO.CastDto;

import java.util.List;

public interface PrincipalsService {
    List<CastDto> principalsByTconst(String tconst);
}
