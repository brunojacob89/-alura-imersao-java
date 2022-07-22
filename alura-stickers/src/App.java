import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	
	public static void main(String[] args) throws Exception {
		
		
		// fazer uma conexao HTTP e busca os top 250 filmes;
		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient(); // metodo que devolve um cliente novo, pode ser var ou HTTPCLIENT
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		
		//extrair apenas os dados que interessam (Titulo, poster, classificação);
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
		// exibir e manipular os dados
		for (Map<String, String> filme : listaDeFilmes) {
			
			String urlImagem = filme.get("image");
			String titulo = filme.get("title");
			
			InputStream inputStream = new URL(urlImagem).openStream();
			
			String nomeArquivo = titulo + ".png";
			
			GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
			geradora.Cria(inputStream, nomeArquivo);
			
			System.out.println(filme.get("title"));
			System.out.println();
						
		}
		
		
		
	}

}
