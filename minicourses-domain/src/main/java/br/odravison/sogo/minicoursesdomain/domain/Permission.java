package br.odravison.sogo.minicoursesdomain.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Permission {

    private Long id;

    private String name;

    private String description;

}
