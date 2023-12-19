BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('asdf','$2a$10$PfPsmAH2LS7lh3u19LLcGeWa.dPTMNG1vDIB.jRLfRnYCMDC/.Riq','ROLE_USER');


INSERT INTO games (id, game_name, esrb_rating, release_date, cover_url, summary) 
VALUES (1, 'Mario 1', 8, 1, 'https://upload.wikimedia.org/wikipedia/en/thumb/0/03/Super_Mario_Bros._box.png/220px-Super_Mario_Bros._box.png', 'its a me mario');
INSERT INTO genre (id, genre_name) VALUES (1, 'Platformer');
INSERT INTO games_genre (game_id, genre_id) VALUES (1, 1);
INSERT INTO company (id, company_name, company_description, logo) VALUES (1, 'Nintendo', 'Yup it is Nintendo', 'https://bootcamp-os-lms-prd-public.s3.us-west-2.amazonaws.com/content/tech-elevator-nav.png');
INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (1, 1, true, false);
INSERT INTO company (id, company_name, company_description, logo) VALUES (2, 'Other Nintendo', 'The other one', 'https://bootcamp-os-lms-prd-public.s3.us-west-2.amazonaws.com/content/tech-elevator-nav.png');
INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (1, 2, false, true);
INSERT INTO platforms (id, platform_name) VALUES (1, 'NES');
INSERT INTO games_platforms(game_id, platform_id) VALUES (1, 1);
INSERT INTO platforms (id, platform_name) VALUES (2, 'GBA');
INSERT INTO games_platforms(game_id, platform_id) VALUES (1, 2);
INSERT INTO website (id, website_url, website_type) VALUES (1, 'https://en.wikipedia.org/wiki/Super_Mario_Bros.', 3);
INSERT INTO games_website (game_id, website_id) VALUES (1, 1);

INSERT INTO games (id, game_name, esrb_rating, release_date, cover_url, summary) 
VALUES (2, 'Sonic 3', 8, 2, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co1zut.png', 'This is Sonic 3');
INSERT INTO games_genre (game_id, genre_id) VALUES (2, 1);
INSERT INTO company (id, company_name, company_description, logo) VALUES (3, 'Sonic Team', 'This is Sonic Team', 'https://bootcamp-os-lms-prd-public.s3.us-west-2.amazonaws.com/content/tech-elevator-nav.png');
INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (2, 3, true, false);
INSERT INTO company (id, company_name, company_description, logo) VALUES (4, 'Sega', 'This is Sega', 'https://bootcamp-os-lms-prd-public.s3.us-west-2.amazonaws.com/content/tech-elevator-nav.png');
INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (2, 4, false, true);
INSERT INTO platforms (id, platform_name) VALUES (3, 'Sega Genesis');
INSERT INTO games_platforms(game_id, platform_id) VALUES (2, 3);
INSERT INTO platforms (id, platform_name) VALUES (4, 'Wii');
INSERT INTO games_platforms(game_id, platform_id) VALUES (2, 4);
INSERT INTO website (id, website_url, website_type) VALUES (2, 'https://www.igdb.com/games/sonic-the-hedgehog-3', 1);
INSERT INTO games_website (game_id, website_id) VALUES (2, 2);

INSERT INTO games (id, game_name, esrb_rating, release_date, cover_url, summary) 
VALUES (3, 'Mega Man 3', 8, 3, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co55ce.png', 'This is Mega Man 3');
INSERT INTO games_genre (game_id, genre_id) VALUES (3, 1);
INSERT INTO company (id, company_name, company_description, logo) VALUES (5, 'Capcom', 'This is Capcom', 'https://bootcamp-os-lms-prd-public.s3.us-west-2.amazonaws.com/content/tech-elevator-nav.png');
INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (3, 5, true, true);
INSERT INTO games_platforms(game_id, platform_id) VALUES (3, 1);
INSERT INTO games_platforms(game_id, platform_id) VALUES (3, 4);
INSERT INTO website (id, website_url, website_type) VALUES (3, 'https://www.igdb.com/games/mega-man-3', 1);
INSERT INTO games_website (game_id, website_id) VALUES (3, 3);

INSERT INTO games (id, game_name, esrb_rating, release_date, cover_url, summary) 
VALUES (4, 'Street Fighter 6', 10, 4, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co5vst.png', 'Newest Street Fighter');
INSERT INTO genre (id, genre_name) VALUES (2, 'Fighting Game');
INSERT INTO genre (id, genre_name) VALUES (3, '2.5D');
INSERT INTO games_genre (game_id, genre_id) VALUES (4, 2);
INSERT INTO games_genre (game_id, genre_id) VALUES (4, 3);
INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (4, 5, true, true);
INSERT INTO platforms (id, platform_name) VALUES (5, 'PC');
INSERT INTO games_platforms(game_id, platform_id) VALUES (4, 5);
INSERT INTO platforms (id, platform_name) VALUES (6, 'PS5');
INSERT INTO games_platforms(game_id, platform_id) VALUES (4, 6);
INSERT INTO platforms (id, platform_name) VALUES (7, 'Xbox One');
INSERT INTO games_platforms(game_id, platform_id) VALUES (4, 7);
INSERT INTO website (id, website_url, website_type) VALUES (4, 'https://www.igdb.com/games/street-fighter-6', 1);
INSERT INTO games_website (game_id, website_id) VALUES (4, 4);


INSERT INTO user_lists (list_type, custom_type, user_id) VALUES (1, NULL, 3); --Played
INSERT INTO user_lists (list_type, custom_type, user_id) VALUES (2, NULL, 3); --Currently Playing
INSERT INTO user_lists (list_type, custom_type, user_id) VALUES (3, NULL, 3); --Want to PLay
INSERT INTO user_lists (list_type, custom_type, user_id) VALUES (4, 'Holiday Ideas', 3); --Custom

INSERT INTO user_lists_games (list_id, game_id) VALUES (1, 1);
INSERT INTO user_lists_games (list_id, game_id) VALUES (2, 2);
INSERT INTO user_lists_games (list_id, game_id) VALUES (3, 3);
INSERT INTO user_lists_games (list_id, game_id) VALUES (4, 4);


INSERT INTO user_reviews (num_stars, review_text, replayability_score, diversity_score, aggro_score, game_id, user_id) VALUES (3, 'This game is alright', 2, 2, 2, 1, 3);
INSERT INTO user_reviews (num_stars, review_text, replayability_score, diversity_score, aggro_score, game_id, user_id) VALUES (4, 'This game is pretty good', 3, 3, 3, 2, 3);
INSERT INTO user_reviews (num_stars, review_text, replayability_score, diversity_score, aggro_score, game_id, user_id) VALUES (1, 'This game is awful', 4, 4, 4, 3, 3);
INSERT INTO user_reviews (num_stars, review_text, replayability_score, diversity_score, aggro_score, game_id, user_id) VALUES (5, 'This game is amazing even though I haven''t played it ', 5, 5, 5, 4, 3);


INSERT INTO user_comments (comment_text, review_id, user_id) VALUES ('I made this review by accident', 1, 3);

COMMIT TRANSACTION;
