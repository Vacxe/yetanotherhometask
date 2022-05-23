# Yet Another Home Task

### Description:

This project will contains best practices for solving generic home task such as "Display list of <SMTH> then show <SMTH> full info"

Project targeted for open API [OMDB](http://www.omdbapi.com), but you need to generate your own token an set is as parameter into `gradle.properties`
such as `omdbToken=<TOKEN>` or use ENV `omdbToken`

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
