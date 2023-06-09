<h1>Imersão Java - Alura</h1> 

<p align="center">
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>
</p>

## Descrição do projeto: 

<p align="justify">
  Projeto desenvolvido durante a Imersão Java, da plataforma Alura, com o objetivo de utilizar uma API externa para mostrar os filmes mais bem avaliados da IMDb.
  Devido as instabilidades da IMDb, URLs alternativos foram oferecidos, a usada neste projeto foi a https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json 
</p>

## Funcionalidades:
:heavy_check_mark: Imprimir os nomes dos filmes mais avaliados da IMDb.

:heavy_check_mark: Imprimir a Url que leva ao poster de cada filme  

:heavy_check_mark: Imprimir grafico de barras com a representação visual relativa a porcetagem que os filmes receberam em sua classificação.

:heavy_check_mark: Imprimir a classificação dos filmes em porcentagem.

:heavy_check_mark: Imprimir um emoji Triste, Neutro e Sorrindo de acordo com a classificação que o filme recebeu.

:heavy_check_mark: Imprimir o número de estrelas que os filmes foram classificados.

## Resultado: 

![image](https://user-images.githubusercontent.com/17829051/228121400-46bbae73-04ed-42d8-89f1-627e4e2ab4a9.png)

## Tecnologias utilizadas :books:

- Java
- Visual Studio Code
- Consumo de API

## Conexão com MongoDB ##
MongoDB Atlas: https://cloud.mongodb.com/
Adicionar o driver de conexão com o servidor do MongoDB
Caminho do arquivo: linguagens-api\src\main\resources\application.properties
Conteudo do arquivo: spring.data.mongodb.uri=mongodb+srv://<usuario>:<senha>@cluster0.fnocyij.mongodb.net/<nome_banco_de_dados>?retryWrites=true&w=majority
