package br.com.alura.screenmatch.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    public List<SerieDTO> obterTodasAsSeries() {
        return converteDados(repositorio.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(repositorio.lancamentosMaisRecentes());
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serieOptional = repositorio.findById(id);
        if(serieOptional.isPresent()) {
            Serie serie = serieOptional.get();
            return serie.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(
                            e.getTemporada(),
                            e.getTitulo(),
                            e.getNumeroEpisodio()
                    )).collect(Collectors.toList());
        }
        return null;
    }

    
    public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numeroTemporada) {
        return repositorio.obterEpisodiosPorTemporada(id, numeroTemporada).stream()
                .map(e -> new EpisodioDTO(
                        e.getTemporada(),
                        e.getTitulo(),
                        e.getNumeroEpisodio()
                )).collect(Collectors.toList());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serieOptional = repositorio.findById(id);
        if(serieOptional.isPresent()) {
            Serie serie = serieOptional.get();
            return new SerieDTO(
                    serie.getId(),
                    serie.getTitulo(),
                    serie.getTotalTemporadas(),
                    serie.getAvaliacao(),
                    serie.getGenero().toString(),
                    serie.getAtores(),
                    serie.getPoster(),
                    serie.getSinopse()
            );
        }
        return null;
    }

    

    public List<SerieDTO> obterSeriesPorCategoria(String genero) {
        Categoria categoria = Categoria.fromPortugues(genero);
        return converteDados(repositorio.findByGenero(categoria));
    }

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getAvaliacao(),
                        s.getGenero().toString(),
                        s.getAtores(),
                        s.getPoster(),
                        s.getSinopse()
                )).collect(Collectors.toList());
    }



}
