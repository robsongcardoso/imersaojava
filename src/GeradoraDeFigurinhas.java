import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Base64;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public String cria(InputStream inputStream, String titulo) throws Exception {
        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100);

        // transformar a nova imagem em um array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(novaImagem, "png", baos);
        byte[] imagemBytes = baos.toByteArray();

        // normalizar o título do arquivo e remover caracteres especiais
        titulo = Normalizer.normalize(titulo, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "")
            .replaceAll("[^a-zA-Z0-9]", "")
            .toLowerCase();

        // salvar a imagem na pasta figurinhas
        File pasta = new File("figurinhas");
        pasta.mkdir();
        File arquivo = new File(pasta, titulo + ".png");
        ImageIO.write(novaImagem, "png", arquivo);

        // codificar a imagem em base64
        String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);

        // gerar o link para visualização da imagem
        String link = "data:image/png;base64," + imagemBase64;

        return link;
    }

}
