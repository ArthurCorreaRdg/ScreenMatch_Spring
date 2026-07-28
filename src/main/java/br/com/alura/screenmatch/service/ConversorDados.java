package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDados implements IConversorDados {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> tipoClasse) {
        try {
            return objectMapper.readValue(json, tipoClasse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
