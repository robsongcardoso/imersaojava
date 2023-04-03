import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // API
        API api = API.IMDB_TOP_MOVIES;
        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        //Cliente HTTP
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        for (int index = 0; index < 3; index++) {
            Conteudo conteudo = conteudos.get(index);

            System.out.println("\033[1;34mTítulo:\033[0m \033[1;31m" + conteudo.titulo() + "\033[0m");

            String frase_meme = "";
            InputStream imgrank = null;
            double porcentagem = 0;

            if (conteudo.classificacao() == null) {
                int numImagens = 3; // número de imagens disponíveis
                Random random = new Random();
                int indexImagem = random.nextInt(numImagens); // gera um índice aleatório
                String nomeImagem = "";
                switch (indexImagem) { // define o nome da imagem de acordo com o índice gerado
                    case 0:
                        frase_meme = "VOLTO LOGO";
                        nomeImagem = "imgrank/ruim.png";
                        break;
                    case 1:
                        frase_meme = "PERDIDOS NO ESPAÇO";
                        nomeImagem = "imgrank/otimo.png";
                        break;
                    case 2:
                        frase_meme = "AVE MIZERA";
                        nomeImagem = "imgrank/bom.png";
                        break;
                }
                System.out.println("\033[1;34mPassei aqui classificação nula:\033[0m \033[1;31m");
                imgrank = new FileInputStream(new File(nomeImagem));
            } else {
                // calcula a porcentagem correspondente da classificação
                System.out.println("\033[1;34mPassei aqui no else:\033[0m \033[1;31m");
                porcentagem = Double.parseDouble(conteudo.classificacao()) * 10;
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
                String porcentagemFormatada = String.format("%s %s%%", barraDegrade.toString(),
                        Math.round(porcentagem));

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

                // calcular a classificação arredondada
                int classificacao_star = (int) Math.round(Double.parseDouble(conteudo.classificacao()));

                // exibir as estrelas correspondentes
                for (int i = 1; i <= 10; i++) {
                    if (i <= classificacao_star) {
                        System.out.print("\033[38;2;255;255;0m★\033[0m ");
                    } else {
                        System.out.print("☆ ");
                    }
                }

            }
            var geradora = new GeradoraDeFigurinhas();
            String titulo = conteudo.titulo();
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            geradora.cria(inputStream, titulo, frase_meme, imgrank);

            System.out.println("\n");
        }

    }
}
