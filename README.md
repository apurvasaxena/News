# News
This project shows how to fetch data from local database using **ROOM**. If there is no local data saved in app then fetch data from web server api using **RxJAVA** and **RETROFIT**.

## Functionality
The app is composed of 2 main screens:
1. News List Screen: It allows users to fetch data from web server and save it in local database for future use. On click of card view, user can transit to news detail screen with **Scene transition animation**. 
![device-2020-05-25-175313](https://user-images.githubusercontent.com/11437902/82812539-d7318180-9eb0-11ea-9b54-65b15ae7e32a.png)
2. New Detail Screen: This screen shows details of the news articles.
![device-2020-05-25-174252](https://user-images.githubusercontent.com/11437902/82812568-e7496100-9eb0-11ea-992a-8d83d9e416ba.png)

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

## Apk
Please find apk to install https://www.dropbox.com/s/uzbuad69h3i54m1/news.apk?dl=0
