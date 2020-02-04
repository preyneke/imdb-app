package com.imdbapp.app.services;

import com.imdbapp.app.DTO.PrincipalsDto;

import java.util.List;

public interface PrincipalsService {
    List<PrincipalsDto> principalsByTconst(String tconst);
}
