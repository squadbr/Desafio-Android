# Movie Score Seeker

Aplicativo para consulta de notas de filmes foi desenvolvido na plataforma Android utilizando a API [OMDb](http://www.omdbapi.com).

## Dependências

As dependências estão informadas no script Gradle `build.gradle` (projeto and app). Todas elas estão descritas abaixo:

1. [Retrofit/OKhttp](http://square.github.io/retrofit/): Network Request (REST).
2. [Glide](https://github.com/bumptech/glide): An image loading and caching library for Android focused on smooth scrolling.
3. [Lottie](https://airbnb.design/lottie/): Easily add high-quality animation to any native app.
4. [Glide](https://github.com/bumptech/glide): An image loading and caching library.
5. [Koin](https://insert-koin.io/): A pragmatic lightweight dependency injection framework for Kotlin.
6. [Anko](https://github.com/Kotlin/anko): Anko is a Kotlin library which makes Android application development faster and easier.
7. [Coroutines](https://github.com/Kotlin/kotlin-coroutines): Allow us to create asynchronous programs in a very fluent way.

## Implementação

A arquitetura utilizada é MVVM (Model-View-Presenter) juntamente com o padrão Repository.
Segue abaixo a estrutura de pacotes utilizada:

- model: Classes TO/VO/POJO (Transfer Object/Value Object/Plain Old Java Objects).
- search/details/onboarding/splash: Classes responsáveis pela UI.
- data: Classes responsáveis pela camada model, utilizando o padrão Repository.
- ui: Classes responsáveis pela apresentação (Activities, Fragments, Presenter, etc).
- di: Classes utilizadas para injeção de dependências.
- util: Classes utilitárias.

## Próximos passos

- Adicionar mais testes
- Adicionar animações
- Adicionar tratamento offline utilizando banco de dados
