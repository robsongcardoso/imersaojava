package br.com.alura.linguagens.api;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PrincipaisLinguagens")
public class Linguagem {
    
    @id
    private String id;
    private String title;
    private String image;
    private int ranking;

    public Linguagem(String title, String image, int ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }

    public Linguagem(){}

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public int getRanking() {
        return ranking;
    }

    public void setId(String id) {
        this.id = id;
    }
    



}
