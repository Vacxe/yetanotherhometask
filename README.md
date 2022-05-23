# Yet Another Home Task

### Status
![Master](https://github.com/vacxe/yetanotherhometask/actions/workflows/master-unit-tests.yml/badge.svg)
![Master](https://github.com/vacxe/yetanotherhometask/actions/workflows/master-assemble.yml/badge.svg)

### Description:

This project will contains best practices for solving generic home task such as "Display list of <SMTH> then show <SMTH> full info"

Project targeted for open API [OMDB](http://www.omdbapi.com), but you need to generate your own token and set is as parameter into `gradle.properties`
as `omdbToken=<TOKEN>` or use `omdbToken` ENV

| List of movies             |  Details for specific movie |
:-------------------------:|:-------------------------:
![List of movies](/.github/pics/list.png?raw=true "List of movies")  |  ![Details for movie](/.github/pics/details.png?raw=true "Details for movie")

Used in project:
* Kotlin
* Jetpack Compose + MVVM
* Coroutines
* Dependency Injection based on Dagger + Hilt
* Network communication based on Retrofit + Calls interception
* Sample of external API library
* Integration test for API library

CI (Github Flow): 
* Library unit tests
* App unit tests
