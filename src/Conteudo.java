public class Conteudo {
    
    private final String titulo;
    private final String urlImagem;
    private final String classificacao;
    
    public Conteudo(String titulo, String urlImagem, String classificacao) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
        this.classificacao = classificacao;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getUrlImagem() {
        return urlImagem;
    }
    public String getClassificacao() {
        return classificacao;
    }

}
