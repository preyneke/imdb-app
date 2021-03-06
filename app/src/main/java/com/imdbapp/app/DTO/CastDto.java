package com.imdbapp.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CastDto {

   private String nconst;
   private String name;
   private String character;
   private String job;
}
