package br.com.alura.screenmatch.service;

public interface IConversorDados {

    <T> T obterDados(String json, Class<T> tipoClasse);
}
