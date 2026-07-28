
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConversorDados;
import br.com.alura.screenmatch.service.ObterDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite o nome da série que deseja buscar: ");
		String serie = leitura.nextLine();
		
		ObterDados obter = new ObterDados();
		String json = obter.obterDados("http://www.omdbapi.com/?i=" + serie + "&apikey=18f8a2a9");
		System.out.println(json);
		ConversorDados conversor = new ConversorDados();
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
		System.out.println("Título: " + dadosSerie.titulo());
		System.out.println("Avaliação: " + dadosSerie.avaliacao());
		System.out.println("Total de temporadas: " + dadosSerie.totalTemporadas());
	}
}
