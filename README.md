# Sink Or Swim - "Conquer the seas, or go down trying"

### Summary:
This project outlines the backend code for a battleships game that can be played in singleplayer against a computer, and online multiplayer.
The frontend of this project is required to play the game, it can be found here: https://github.com/tharahan04/Battleships_Frontend

### Diagrams:
<Add ERD, UML, Components and Wireframing here!>


## Tech Stack:
- Java 17 (Intellij IDEA)
- Spring Boot (Maven)
- PostgresSQL
- Postico

## Spring Boot Dependencies:
- Spring Data JPA
- Spring Boot DevTools
- Spring Web
- Websockets

## SetUp Instructions:
1. Ensure that the following are installed on your machine:
- IntelliJ (JDK 17) - with Maven
- PostgresSQL
- Postman

2. Fork the repository in GitHub (and also the react frontend: https://github.com/tharahan04/Battleships_Frontend ), so you can make changes without affecting the original project. To fork the repository, click Fork in the top-right corner of the page. Fill out the details and then press <pre><code>Create fork.</code></pre>

3. Clone both repos locally using <pre><code>Git clone</code></pre>. To do this, at the top of forked repository page, click on the green `< > Code` button. Make sure that SSH is selected and then copy the link provided. Then within your terminal, in the working directory where you want the cloned directory, type the `git clone` command and paste the copied URL, then press Enter. It should look something like this:
    <pre><code> git clone git@github.com:sujansriram/Battleships_Backend.git</code></pre>

4. Create a new database by running the following line anywhere in your terminal: createdb battleships
 - In resources.application.properties, copy in the following:
<pre><code>
spring.datasource.url=jdbc:postgresql://localhost:5432/battleships
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true 
</code></pre>

5. Once you have installed all the necessary applications and dependencies for this project, run the BattleShipsBackendApplication via Intellij IDEA. Ensure that there are no errors and that the API is running on port 8080. On the frontend react app besure to do an `npm i` in your terminal to install all the required dependencies. `npm run` to start the react application.

## Model Description

### Game <br/>
This represents the entire battleships game.
- Properties:
  - isStarted - boolean that represents whether the game has started. Added so that players could edit their grid first.
  - isFinished - boolean that represents whether the game has finished.
  - playerOneTurn - boolean that controls which player's turn it is.
  - isSinglePlayer - boolean that controls whether the game is SinglePlayer or Multiplayer.
  - List<Grid> - list that holds 8x8 grid models required for the game.

### Grid <br/>
The grid model represents each player's grid.
- Properties:
  - playerName - modifiable string that represents the player's name.
  - List<Cell> - list that holds 64 cell models that make up the grid.
  - Game - represents the game that the grid belongs to.
  
### Cell <br/>
This represents the cells that make up the grid.
- Properties:
  - xCoordinate - integer that represents the X-Coordinate of the Cell.
  - yCoordinate - integer that represents the Y-Coordinate of the Cell.
  - hasBeenHit - boolean that represents whether the cell has been hit.
  - Ship - ship object that represents the Ship that is contained within the cell.
  - Grid - grid object that represents the Grid that the cell belongs to.

### Ship <br/>
This represents the battleships of the game.
- Properties:
    - name - string that represents that name of the battleship.
    - size - integer that represents how many cells long the ship is.
    - hasSunk - boolean that represents whether the ship has sunk.
    - numberOfTimesHit - integer that represents how many times the ship has been hit.
    - playerOne - boolean that represents if the set of ships belong to player one or two.
    - List<Cell> - List of cell models that represent the cells that the ship is occupying.

### Reply <br/>
Reply object to send back the game and cell object once a guess is created.
- Properties:
    - Game - represents the game object of which a guess is created.
    - Cell - represents the cell on which a guess was made.


## EndPoints

| Action                               | Method | Request Path (Endpoint)                          | Request Body Required and Example Request Bodies                                                                       | Expected return from Postman                                                                                                                                                                                                                   |  
|--------------------------------------|:------:|--------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| getGame                             |  GET   | `/game`                                         |                                                                                                                        | This endpoint returns all the games that are in the database.                                                                                                   |
| createGame           |  POST   | `/games?isSinglePlayer={boolean}`                     |                                                                                                                        | This endpoint creates a new game object. `?Singplayer` denotes whether the game is singleplayer or not.                                                    |

## Collaborators
- Sujan - (Github - https://github.com/sujansriram)
- Will - (Github - https://github.com/williamdorling)
- Thibyaa - (Github - https://github.com/thibyaa)
- Tharahan - (Github -https://github.com/tharahan04)
- Vincent - (Github - https://github.com/dir-V)
  

