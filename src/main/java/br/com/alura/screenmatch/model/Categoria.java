package br.com.alura.screenmatch.model;

/**
 * Categoria
 */
public enum Categoria {
    ACAO("Action"),
    AVENTURA("Adventure"),
    COMEDIA("Comedy"),
    DOCUMENTARIO("Documentary"),
    DRAMA("Drama"),
    ESPIONAGEM("Spy"),
    FAROESTE("Western"),
    FICCAO_CIENTIFICA("Sci-Fi"),
    MUSICAL("Musical"),
    ROMANCE("Romance"),
    SUSPENSE("Thriller"),
    TERROR("Horror"),
    CRIME("Crime");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String categoriaOmdb) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(categoriaOmdb)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a String fornecida");
    }
}
