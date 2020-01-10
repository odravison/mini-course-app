package br.odravison.sogo.minicoursesapplication.config.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private long timestamp;
    private String error, message, path;

}
