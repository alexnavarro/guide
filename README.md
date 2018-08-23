# Guide

> Show users comments of Tempelhof Airport, which is a famous spot in Berlin 

[Guide](https://github.com/alexnavarro/guide)  presents a list of comments provided by a service, indeed it shows the user's rating and their nationalities

It was written in Kotlin and follow clean architecture combined with some [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/). The main used libs are: 
* [dagger 2](https://google.github.io/dagger/) for dependency injection
* [retrofit](https://square.github.io/retrofit/) for handling HTTP requests

## Install

I recommend you use [Android Studio](https://developer.android.com/studio/index.html) because the development is easier than the command line. 

It's necessary create a file named api.properties and add a key called api_client with the service key, without this file the build will break.

For generate the apk using command line use this command: `./gradlew assembleDebug`
