import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        //String url = System.getenv("API_URL");
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\033[1;34mTítulo:\033[0m \033[1;31m" + filme.get("title") + "\033[0m");
            System.out.println("\uD83C\uDF10 " + filme.get("image"));
        
            // calcula a porcentagem correspondente da classificação
        
            String classificacao = filme.get("imDbRating");
            double porcentagem = Double.parseDouble(classificacao) * 10;
            int numBarras = (int) Math.round(porcentagem / 10.0);
            String barra = "\033[38;2;255;255;0m█\033[0m";
            String barraVazia = "░";
            StringBuilder barraDegrade = new StringBuilder();
            for (int i = 1; i <= numBarras; i++) {
                barraDegrade.append(barra);
            }
            for (int i = numBarras + 1; i <= 10; i++) {
                barraDegrade.append(barraVazia);
            }
        
            // exibe a classificação com a barra degradê e emojis correspondentes
            String porcentagemFormatada = String.format("%s %s%%", barraDegrade.toString(), Math.round(porcentagem));
        
            if (porcentagem >= 70) {
                porcentagemFormatada += " \uD83D\uDE00"; // emoji sorrindo (ótimo)
            } else if (porcentagem >= 40) {
                porcentagemFormatada += " \uD83D\uDE42"; // emoji neutro (bom)
            } else {
                porcentagemFormatada += " \uD83D\uDE41"; // emoji triste (ruim)
            }
            System.out.println(porcentagemFormatada);
        
            // calcular a classificação arredondada
            int classificacao_star = (int) Math.round(Double.parseDouble(filme.get("imDbRating")));
        
            // exibir as estrelas correspondentes
            //System.out.print("\033[1;33mClassificação:\033[0m ");
            for (int i = 1; i <= 10; i++) {
                if (i <= classificacao_star) {
                    System.out.print("\033[38;2;255;255;0m★\033[0m ");
                } else {
                    System.out.print("☆ ");
                }
            }
            System.out.println("\n");
        }
        
    }
}
