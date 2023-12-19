-- Done - Get User and Post User --


-- Get All Usernames
SELECT username FROM users;


-- Get Information About User
SELECT * FROM user_lists WHERE user_id = ?;

SELECT * FROM user_lists_games WHERE list_id = (SELECT id FROM user_lists WHERE user_id = ?);

SELECT * FROM user_ratings WHERE user_id = ?;

SELECT * FROM user_reviews WHERE user_id = ?;

SELECT * FROM user_comments WHERE user_id = ?;


-- Update User
UPDATE users SET username = ?, password_hash = ?, role = ? WHERE id = ?;


-- Delete User
DELETE FROM user_lists_games WHERE list_id IN (SELECT id FROM user_lists WHERE user_id = ?);

DELETE FROM user_lists WHERE user_id = ?;

DELETE FROM user_ratings WHERE user_id = ?;

DELETE FROM user_reviews WHERE user_id = ?;

DELETE FROM user_comments WHERE review_id IN (SELECT id FROM user_reviews WHERE user_id = ?);

DELETE FROM user_comments WHERE user_id = ?;

DELETE FROM users WHERE user_id = ?;


-- Get Game
SELECT g.id, g.game_name, e.rating_name, g.release_date, g.cover, g.game_description, g.website FROM games g JOIN esrb_rating e ON g.esrb_rating = e.id WHERE g.id = ?; --Game Info

SELECT c.*, gc.company_role FROM company c JOIN games_company gc ON c.id = gc.company_id JOIN games g ON gc.game_id = g.id WHERE g.id = ?; --Company Info

SELECT p.* FROM games g JOIN games_platforms gp ON g.id = gp.game_id JOIN platforms p ON gp.platform_id = p.id WHERE g.id = ?; --Platform Info

SELECT * FROM user_ratings WHERE game_id = ?; --Rating Info

SELECT * FROM user_reviews WHERE game_id = ?; --Review Info

SELECT * FROM user_comments WHERE review_id = (SELECT id FROM user_reviews WHERE game_id = ?) --Review Comments


-- Get Games Based on Name For Lookup
SELECT game_name, cover, id FROM games WHERE id = (SELECT id FROM games WHERE game_name ILIKE ?%); --Used for Search Box?


-- Add Game
INSERT INTO games (id, game_name, esrb_rating, release_date, cover, summary) VALUES (?, ?, ?, ?, ?, ?);

INSERT INTO company (id, logo, company_name, company_description) VALUES (?, ?, ?, ?);

INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (?, ?, ?, ?);

INSERT INTO platforms (id, platform_name) VALUES (?, ?);

INSERT INTO games_platforms (game_id, platform_id) VALUES (?, ?);

INSERT INTO genre (id, genre_name) VALUES (?, ?);

INSERT INTO games_genre (game_id, genre_id) VALUES (?, ?);

INSERT INTO website (id, website_url) VALUES (?, ?);

INSERT INTO games_website (game_id, website_id) VALUES (?, ?);


-- Update Game
UPDATE games SET id = ?, game_name = ?, esrb_rating = ?, release_date = ?, cover = ?, game_description = ?, website = ? WHERE id = ?;

UPDATE company SET id = ?, company_name = ?, company_description = ? WHERE id = ?;

UPDATE games_company SET game_id = ?, company_id = ?, company_role = ? WHERE game_id = ?;

UPDATE platforms SET id = ?, platform_name = ?;

UPDATE games_platforms SET game_id = ?, platform_id = ?;


-- Delete Game
DELETE FROM games_platforms WHERE game_id = ?;

DELETE FROM games_company WHERE game_id = ?;

DELETE FROM user_lists_games WHERE game_id = ?;

DELETE FROM user_ratings WHERE game_id = ?;

DELETE FROM user_reviews WHERE game_id = ?

DELETE FROM games WHERE game_id = ?;


-- Get All User Lists
SELECT * FROM user_lists WHERE user_id = ?


-- Get User List
SELECT * FROM user_lists WHERE id = ?;


-- Add User List
INSERT INTO user_lists (list_type, custom_type, user_id) VALUES (?, ?, ?) RETURNING id;


-- Update User List (only should be used to change name of custom list)
UPDATE user_lists SET custom_type = ? WHERE id = ?;


-- Delete User List
DELETE FROM user_lists WHERE id = ?;

DELETE FROM user_lists_games WHERE list_id = ?


-- Get All Games on User List
SELECT game_id FROM user_lists_games WHERE list_id = ?;


-- Add Game to User List
INSERT INTO user_lists_games (list_id, game_id) VALUES (?, ?);


-- Remove Game from User List
DELETE FROM user_lists_games WHERE game_id = ?;


-- Get All User Ratings
SELECT * FROM user_ratings WHERE user_id = ?;


-- Get User Rating for Game
SELECT * FROM user_ratings WHERE user_id = ? AND game_id = ?;


-- Add User Rating for Game
INSERT INTO user_ratings (num_stars, game_id, user_id) VALUES (?, ?, ?) RETURNING id;


-- Update User Rating for Game
UPDATE user_ratings SET num_stars = ? WHERE id = ?;


-- Delete User Rating for Game
DELETE FROM user_ratings WHERE user_id = ? AND game_id = ?;


-- Get All User Reviews
SELECT * FROM user_reviews WHERE user_id = ?;


-- Get User Review for Game
SELECT * FROM user_reviews WHERE user_id = ? AND game_id = ?;


-- Add User Review for Game
INSERT INTO user_reviews (review_text, game_id, user_id) VALUES (?, ?, ?) RETURNING id;


-- Update User Review for Game
UPDATE user_reviews SET review_text = ? WHERE id = ?;


-- Delete User Review for Game
DELETE FROM user_reviews WHERE user_id = ? AND game_id = ?;


--Get All Comments for Review
SELECT * FROM user_comments WHERE review_id = ?;


-- Get All Comments from User for All Reviews
SELECT * FROM user_comments WHERE user_id = ?;


-- Get User Comments for Review
SELECT * FROM user_comments WHERE user_id = ? AND review_id = ?;


-- Add User Comment for Review
INSERT INTO user_comments (comment_text, review_id, user_id) VALUES (?, ?, ?) RETURNING id;


-- Update User Comment for Review
UPDATE user_comments SET comment_text = ? WHERE id = ?;


-- Delete User Comment for Review
DELETE FROM user_comments WHERE user_id = ? AND review_id = ?;

