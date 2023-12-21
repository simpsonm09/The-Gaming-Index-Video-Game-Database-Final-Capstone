# The Gaming Index
​
This directory contains all of the code necessary to run The Gaming Index Website and database:

All Java server code and SQL scripts are located in the java folder.

All Client vue code is located in the vue folder.


​To properly run the application you must: 
1. run the create.sh script in java/database/
2. run the Application.java file in java/src/main/java/com/techelevator/ with IntelliJ IDEA
3. navigate to the vue folder in the windows terminal and run "npm install" and then "npm run dev"
4. navigate to localhost:5173 in your web browser (program was developed using Google Chrome)

Description:
Server developed in Java using PostgreSQL, MVC fundamentals, and implementing a RESTful API. Client website developed using Vue.js and Axios adhering to HTML/CSS/JavaScript principles. Full stack application allowed users to register/login with a website, search for child-friendly games by name or display a random game, and view detailed information. Users were also able to manage reviews, comments, and personal lists for a game. All game data was retrieved from a 3rd party API (IGDB) on the backend and saved to the database to reduce network traffic.


