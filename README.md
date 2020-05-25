# News
This project shows how to fetch data from local database using ROOM and if there is no local data saved, fetch data from web server api using RxJAVA and RETROFIT.

## Functionality
The app is composed of 2 main screens:
1. News List Screen: It allows to fetch data from web server and save it in local database for future use and intents data to next screen.
2. New Detail Screen: The 2nd screen is Details screen. It shows details of the articles.

## Libraries
* Kotlin
* Room
* Dagger
* RxJava
* Retrofit
* Lifecycle
* JUnit
* Mockito
* Material design 

## Testing
### UI Test
#### Repository Test: 
Repository is tested using local unit tests with mockito.
### Unit Tests
#### ViewModel Test: 
ViewModel class is unit tested to test if right message is displayed to user from apis response and if data is being fetched from web server only when local database is empty.
