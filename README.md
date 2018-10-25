# Desafio Squad: Buscador de Notas de Filmes no OMDB
## OpenMovieDbSearch

### Visão Geral
O OpenMovieDbSearch é um app para buscar filmes na [API do The Open Movie Database](https://www.omdbapi.com/), de acordo com as notas de cada filme segundo a [Internet Movie Database (IMDb)](https://www.imdb.com/), retornadas pela própria API. Parte do processo seletivo da [Squad](https://squad.com.br/), segundo o requisito original disponível na commit ae76b3c.

### Especificações
 - **Idioma:** Como o Git SCM, a linguagem Kotlin e o Android framework como um todo são escritos em inglês, o código-fonte do projeto, bem como o controle de versões (*commit messages* e *branches*), será impreterivelmente escrito em inglês, visando coerência com as tecnologias que utiliza. Este README, excepcionalmente, é escrito em português, por ser este o idioma do requisito do desafio.

 - **Layouts:** Serão adaptados alguns layouts de um outro projeto de minha autoria, chamado [BackInTheDay](https://github.com/gabrielfeo/BackInTheDay), que é similar a este, ao menos em termos de layout. 

 - **Arquitetura:** O projeto será estruturado no padrão arquitetural MVVM (Model-View-ViewModel);
 - **Bibliotecas:** As principais bibliotecas empregadas serão as seguintes: 
   - [Support Library](https://developer.android.com/topic/libraries/support-library/) (Android), para compatibilidade com dispositivos com versão antiga do Android, adaptação para o Material Design, e layouts mais flexíveis e performáticos com o `ConstraintLayout`.
   - [Lifecyle Components](https://developer.android.com/reference/android/arch/lifecycle/package-summary) (Android), para uma arquitetura MVVM coordenada com o ciclo de vida especificado pelo Android Framework;
   - [Retrofit](https://square.github.io/retrofit/) (Square), para o consumo de APIs RESTful;
   - [Retrofit GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) (Square), para a conversão automática de respostas em JSON da API para classes modelo;
   - [Picasso](https://square.github.io/picasso/) (Square), para o carregamento de imagens com caching e outras otimizações;