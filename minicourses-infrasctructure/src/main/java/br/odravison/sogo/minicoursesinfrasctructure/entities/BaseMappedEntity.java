package br.odravison.sogo.minicoursesinfrasctructure.entities;

public abstract class BaseMappedEntity {

    public static final String WHERE_DELETED_CLAUSE = "deleted = false";

    public abstract Long getId();

    public abstract void setId(Long id);
}
