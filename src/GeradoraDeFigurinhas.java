import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.Normalizer;


import javax.imageio.ImageIO;


public class GeradoraDeFigurinhas {

    public String cria(InputStream inputStream, String titulo, String frase_meme, InputStream imgrank) throws Exception {
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

        //Adicionar imagem Meme
        BufferedImage imagemSobreposicao = ImageIO.read(imgrank);
        int YPosicaoImagemSobreposicao = novaAltura - imagemSobreposicao.getHeight();
        graphics.drawImage(imagemSobreposicao, 0, YPosicaoImagemSobreposicao, null);

        // configurar a fonte
        var fonte = new Font("Comic Sans MS", Font.BOLD, 64);
        graphics.setFont(fonte);

        // obter o tamanho do texto em pixels
        //String frase_meme = "MELHOR ASSISTIR TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int larguraTexto = fontMetrics.stringWidth(frase_meme);

        // calcular a posição horizontal centralizada
        int posicaoX = (largura - larguraTexto) / 2;

        // configurar o contorno do texto
        graphics.setStroke(new BasicStroke(8));
        graphics.setColor(Color.BLACK);

        // desenhar o contorno do texto
        graphics.drawChars(frase_meme.toCharArray(), 0, frase_meme.length(), posicaoX - 2, novaAltura - 98);

        // configurar a cor do texto
        graphics.setColor(Color.YELLOW);

        // desenhar o texto preenchido
        graphics.drawString(frase_meme, posicaoX, novaAltura - 100);

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
        File pasta = new File("saida");
        pasta.mkdir();
        File arquivo = new File(pasta, titulo + ".png");
        ImageIO.write(novaImagem, "png", arquivo);
        return frase_meme;
       
    }

}
