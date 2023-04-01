import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var http = new ClienteHttp();
        String json =  http.buscaDados(url);

        // exibir e manipular os dados
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        for (int index = 0; index < 3; index++) {
            Conteudo  conteudo = conteudos.get(index);

                System.out.println("\033[1;34mTítulo:\033[0m \033[1;31m" + conteudo.getTitulo() + "\033[0m");
                            
                // calcula a porcentagem correspondente da classificação
                double porcentagem = Double.parseDouble(conteudo.getClassificacao()) * 10;
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
                String frase_meme = "";
                InputStream imgrank;
                if (porcentagem >= 70) {
                    frase_meme = "TOPZERA";
                    imgrank = new FileInputStream(new File("imgrank/otimo.png"));
                    porcentagemFormatada += " \uD83D\uDE00"; // emoji sorrindo (ótimo)
                } else if (porcentagem >= 40) {
                    frase_meme = "VALE PELA PIPOCA";
                    imgrank = new FileInputStream(new File("imgrank/bom.png"));
                    porcentagemFormatada += " \uD83D\uDE42"; // emoji neutro (bom)
                } else {
                    frase_meme = "DORMI ASSISTINDO";
                    imgrank = new FileInputStream(new File("imgrank/ruim.png"));
                    porcentagemFormatada += " \uD83D\uDE41"; // emoji triste (ruim)
                }
                System.out.println(frase_meme);
                System.out.println(porcentagemFormatada);
                
                var geradora = new GeradoraDeFigurinhas();
                
                String titulo = conteudo.getTitulo();   
                InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
                geradora.cria(inputStream, titulo, frase_meme, imgrank);
            
                // calcular a classificação arredondada
                int classificacao_star = (int) Math.round(Double.parseDouble(conteudo.getClassificacao()));
            
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
