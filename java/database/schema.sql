BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, esrb_rating, games, genre, games_genre, company, games_company, platforms,
games_platforms, website_type, website, games_website, user_ratings, user_reviews, user_comments, user_lists, user_lists_games;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE esrb_rating(
	id integer NOT NULL,
	rating_name varchar(200) NOT NULL,
	CONSTRAINT PK_esrb_rating_id PRIMARY KEY (id)
);

CREATE TABLE games(
	id integer NOT NULL,
	game_name varchar(200),
	esrb_rating integer,
	release_date int,
	cover_url varchar(200),
	summary varchar(5000),
	num_players integer,
	CONSTRAINT PK_game_id PRIMARY KEY (id),
	CONSTRAINT FK_esrb_rating FOREIGN KEY (esrb_rating) REFERENCES esrb_rating (id)
);

CREATE TABLE genre(
	id integer NOT NULL,
	genre_name varchar(200) NOT NULL,
	CONSTRAINT PK_genre_id PRIMARY KEY (id)
);

CREATE TABLE games_genre(
	game_id integer NOT NULL,
	genre_id integer NOT NULL,
	CONSTRAINT FK_gamegenre_id FOREIGN KEY (game_id) REFERENCES games (id),
	CONSTRAINT FK_genregame_id FOREIGN KEY (genre_id) REFERENCES genre (id)

);

CREATE TABLE company(
	id integer NOT NULL,
	logo varchar(200),
	company_name varchar(200),
	company_description varchar(5000),
	CONSTRAINT PK_company_id PRIMARY KEY (id)
);

CREATE TABLE games_company(
	game_id integer NOT NULL,
	company_id integer NOT NULL,
	is_developer boolean,
	is_publisher boolean,
	CONSTRAINT FK_gamecomp_id FOREIGN KEY (game_id) REFERENCES games (id),
	CONSTRAINT FK_compgame_id FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE platforms(
	id integer NOT NULL,
	platform_name varchar(200) NOT NULL,
	CONSTRAINT PK_platform_id PRIMARY KEY (id)
);

CREATE TABLE games_platforms(
	game_id integer NOT NULL,
	platform_id integer NOT NULL,
	CONSTRAINT FK_gameplat_id FOREIGN KEY (game_id) REFERENCES games (id),
	CONSTRAINT FK_platgame_id FOREIGN KEY (platform_id) REFERENCES platforms (id)
);

CREATE TABLE website_type(
	id integer NOT NULL,
	type_name varchar(200) NOT NULL,
	CONSTRAINT PK_website_type_id PRIMARY KEY (id)
);

CREATE TABLE website(
	id integer NOT NULL,
	website_url varchar(200) NOT NULL,
	website_type integer,
	CONSTRAINT PK_website_id PRIMARY KEY (id),
	CONSTRAINT FK_website_type FOREIGN KEY (website_type) REFERENCES website_type (id)
);

CREATE TABLE games_website(
	game_id integer NOT NULL,
	website_id integer NOT NULL,
	CONSTRAINT FK_gamewebsite_id FOREIGN KEY (game_id) REFERENCES games (id),
	CONSTRAINT FK_websitegame_id FOREIGN KEY (website_id) REFERENCES website (id)
);

CREATE TABLE user_reviews(
	id SERIAL,
	num_stars integer,
	review_text varchar(5000),
	replayability_score integer,
	diversity_score integer,
	aggro_score integer,
	game_id integer NOT NULL,
	user_id integer NOT NULL,
	CONSTRAINT PK_review_id PRIMARY KEY (id),
	CONSTRAINT FK_game_id FOREIGN KEY (game_id) REFERENCES games (id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT CHK_num_stars CHECK (num_stars>=1 AND num_stars<=5),
	CONSTRAINT CHK_replayability_score CHECK (replayability_score>=1 AND replayability_score<=5),
	CONSTRAINT CHK_diversity_score CHECK (diversity_score>=1 AND diversity_score<=5),
	CONSTRAINT CHK_aggro_score CHECK (aggro_score>=1 AND aggro_score<=5)
);

CREATE TABLE user_comments(
	id SERIAL,
	comment_text varchar(2000) NOT NULL,
	review_id integer NOT NULL,
	user_id integer NOT NULL,
	CONSTRAINT PK_comment_id PRIMARY KEY (id),
	CONSTRAINT FK_review_id FOREIGN KEY (review_id) REFERENCES user_reviews (id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE TABLE user_list_type(
	id integer NOT NULL,
	type_name varchar(50) NOT NULL,
	CONSTRAINT PK_list_type_id PRIMARY KEY (id)
);

CREATE TABLE user_lists(
	id SERIAL,
	list_type integer NOT NULL,
	custom_type varchar(50),
	user_id integer NOT NULL,
	CONSTRAINT PK_lists_id PRIMARY KEY (id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_list_type FOREIGN KEY (list_type) REFERENCES user_list_type (id)
);

CREATE TABLE user_lists_games(
	list_id integer NOT NULL,
	game_id integer NOT NULL,
	CONSTRAINT FK_list_id FOREIGN KEY (list_id) REFERENCES user_lists (id),
	CONSTRAINT FK_game_id FOREIGN KEY (game_id) REFERENCES games (id)
);

INSERT INTO esrb_rating (id, rating_name) VALUES (0, 'No ESRB Rating');
INSERT INTO esrb_rating (id, rating_name) VALUES (6, 'RP');
INSERT INTO esrb_rating (id, rating_name) VALUES (7, 'EC');
INSERT INTO esrb_rating (id, rating_name) VALUES (8, 'E');
INSERT INTO esrb_rating (id, rating_name) VALUES (9, 'E10');
INSERT INTO esrb_rating (id, rating_name) VALUES (10, 'T');
INSERT INTO esrb_rating (id, rating_name) VALUES (11, 'M');
INSERT INTO esrb_rating (id, rating_name) VALUES (12, 'AO');

INSERT INTO user_list_type(id, type_name) VALUES (1, 'Played');
INSERT INTO user_list_type(id, type_name) VALUES (2, 'Currently Playing');
INSERT INTO user_list_type(id, type_name) VALUES (3, 'Want to Play');
INSERT INTO user_list_type(id, type_name) VALUES (4, 'Custom');

INSERT INTO website_type(id, type_name) VALUES (1, 'official');
INSERT INTO website_type(id, type_name) VALUES (2, 'wikia');
INSERT INTO website_type(id, type_name) VALUES (3, 'wikipedia');
INSERT INTO website_type(id, type_name) VALUES (4, 'facebook');
INSERT INTO website_type(id, type_name) VALUES (5, 'twitter');
INSERT INTO website_type(id, type_name) VALUES (6, 'twitch');
INSERT INTO website_type(id, type_name) VALUES (8, 'instagram');
INSERT INTO website_type(id, type_name) VALUES (9, 'youtube');
INSERT INTO website_type(id, type_name) VALUES (10, 'iphone');
INSERT INTO website_type(id, type_name) VALUES (11, 'ipad');
INSERT INTO website_type(id, type_name) VALUES (12, 'android');
INSERT INTO website_type(id, type_name) VALUES (13, 'steam');
INSERT INTO website_type(id, type_name) VALUES (14, 'reddit');
INSERT INTO website_type(id, type_name) VALUES (15, 'itch');
INSERT INTO website_type(id, type_name) VALUES (16, 'epicgames');
INSERT INTO website_type(id, type_name) VALUES (17, 'gog');
INSERT INTO website_type(id, type_name) VALUES (18, 'discord');


COMMIT TRANSACTION;