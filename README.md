# Pawpal <img src="https://github.com/user-attachments/assets/cf819a59-42f2-4030-8726-654f1f8eb78a" width="50" />

An app about cats.


https://github.com/user-attachments/assets/629b07f4-d28d-4ea3-bc1d-73796474116f


- Development followed a **clean design approach with MVVM** and Repository.
The repository was directly injected into the viewmodels with dagger hilt. 
Use cases were deliberately omitted, because the scope of the project does not require it.

- The breeds list is requested on **first time usage and then persisted in cache using Room**. After this moment, the Repository always gets the data from memory to avoid multiple and unnecessary network requests.

- This also allows the app to **work in offline**, as well as preserving the favorite cat breeds for future app launches.

- The UI is accomplished using **Compose**. Ui events are sent to the Viewmodel that in turn update the **state Flows and trigger UI recomposition**.
The images are loaded using **Coil**, using the url retrieved asynchronously from the endpoints using kotlin coroutines.

Tests are implemented to:
 - the api response to validate the dtos are correctly parsed into the models;
 - the database, to ensure the breeds were correctly added and retrieved;
 - the repository, to tests the favorite behavior.

The 3 main screens: Home, Favorites, Details.
<p align="left">
  <img src="https://github.com/user-attachments/assets/24160762-daa5-4794-9ba9-f11bb54cdf91" alt="Home" width="20%" />
  <img src="https://github.com/user-attachments/assets/b6c57fd2-d133-41a3-a108-96697b4bf32f" alt="Favorites" width="20%" />
  <img src="https://github.com/user-attachments/assets/66308612-7247-49bc-addd-6fb0589df644" alt="Details" width="20%" />

In the Home screen, we can search for any breed using the Search box:

 <img src="https://github.com/user-attachments/assets/3ace9a9b-c6ba-4bd5-8586-bcd23f68d9ee" width="200" />
