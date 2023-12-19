package com.techelevator.dao;

import com.techelevator.controller.IGDBController;
import com.techelevator.model.IGDBModels.*;
import com.techelevator.model.TEVGDBModels.TEVGDBGame;
import com.techelevator.model.TEVGDBModels.TEVGDBInvolvedCompany;
import com.techelevator.model.TEVGDBModels.TEVGDBSearchGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IGDBControllerTests {
    //Arrange
    //Act
    //Assert
    IGDBController igdbController = new IGDBController();

    @Test
    public void getGameById_returns_correct_game_structure() {
        //Arrange
        List<IGDBWebsite> websites = new ArrayList<>();
        websites.add(new IGDBWebsite(6258, 3, "http://en.wikipedia.org/wiki/Pok%C3%A9mon_Yellow"));
        websites.add(new IGDBWebsite(6259, 1, "http://www.pokemon.com/us/pokemon-video-games/pokemon-yellow-special-pikachu-edition/"));
        websites.add(new IGDBWebsite(6260, 4, "https://www.facebook.com/Pokemon"));
        websites.add(new IGDBWebsite(6261, 5, "https://twitter.com/pokemon"));
        websites.add(new IGDBWebsite(6262, 2, "http://pokemon.wikia.com/"));
        websites.add(new IGDBWebsite(204968, 9, "https://www.youtube.com/user/pokemon"));
        websites.add(new IGDBWebsite(204969, 8, "https://www.instagram.com/pokemon"));
        websites.add(new IGDBWebsite(204970, 18, "https://discord.gg/invite/pokemon"));
        websites.add(new IGDBWebsite(204971, 14, "https://www.reddit.com/r/pokemon"));
        websites.add(new IGDBWebsite(332952, 6, "https://www.twitch.tv/directory/game/Pokémon%20Yellow:%20Special%20Pikachu%20Edition"));


        List<IGDBPlatform> platforms = new ArrayList<>();
        platforms.add(new IGDBPlatform(33, "Game Boy"));
        platforms.add(new IGDBPlatform(37, "Nintendo 3DS"));

        List<IGDBGenre> genres = new ArrayList<>();
        genres.add(new IGDBGenre(12, "Role-playing (RPG)"));
        genres.add(new IGDBGenre(16, "Turn-based strategy (TBS)"));
        genres.add(new IGDBGenre(31, "Adventure"));

        IGDBLogo[] logos = new IGDBLogo[3];
        logos[0] = new IGDBLogo(10782, "cl8bi");
        logos[1] = new IGDBLogo(7567, "cl5u7");
        logos[2] = new IGDBLogo(9832, "cl7l4");

        IGDBCompany[] companies = new IGDBCompany[3];
        companies[0] = new IGDBCompany(70, logos[0], "Nintendo", "Nintendo is a multinational corporation located in Kyoto, Japan. Founded by Fusajiro Yamauchi, it first produced handmade hanafuda cards and also tried several small niche businesses such as a cab company and a love hotel. Nintendo developed into a video game company, becoming one of the most influential in the industry and Japan's third most valuable listed company. The name Nintendo translated from Japanese to English means \"Leave luck to Heaven\".");
        companies[1] = new IGDBCompany(1617, logos[1], "Game Freak", "Game Freak is the Japanese video game developer behind the Pokémon RPGs as well as several other games. Although primarily working with Nintendo, Game Freak is an independent company and has occasionally worked with other publishers on non-Nintendo platforms.");
        companies[2] = new IGDBCompany(812, logos[2], "Gradiente", "Gradiente is a Brazilian consumer electronics company founded in 1964 and based in São Paulo. The company designs and markets many product lines, including video (including televisions and DVD players), audio, home theatre, high-end acoustics, office and mobile stereo, and wireless.\n\nIn late 1983, via their subsidiary Polyvox, they launched the official Atari 2600 in Brazil (Vader model). At the time there were already clones or imported 2600s being sold.\n\nIn 1993 they founded Playtronic, a fully-owned subsidiary that licensed the manufacturing of Nintendo consoles in Brazil, and while publishing games for various systems they also did the Portuguese translations of some games (among them, South Park and Shadow Man for the Nintendo 64). They ceased partnership with Nintendo in 2003 due to the high price of the dollar at the time. Before 1993, they were unlicensed and sold the NES clone Phantom System, which resembles the Atari 7800.");

        IGDBInvolvedCompany[] involvedCompanies = new IGDBInvolvedCompany[3];
        involvedCompanies[0] = new IGDBInvolvedCompany(companies[0], false, true);
        involvedCompanies[1] = new IGDBInvolvedCompany(companies[1], true, false);
        involvedCompanies[2] = new IGDBInvolvedCompany(companies[2], false, true);

        List<TEVGDBInvolvedCompany> TEinvolvedCompanies = new ArrayList<>();
        for (int i = 0; i < involvedCompanies.length; i++) {
            TEinvolvedCompanies.add(new TEVGDBInvolvedCompany(involvedCompanies[i]));
        }

        //Act
        TEVGDBGame actual = igdbController.getGameById(1512);
        TEVGDBGame expected = new TEVGDBGame(1512,
                "Pokémon Yellow Version: Special Pikachu Edition",
                8,
                905558400,
                "https://images.igdb.com/igdb/image/upload/t_cover_big/co5pih.jpg",
                "Pokémon Yellow is an enhanced version of Pokémon Red and Blue that allows you to start the game with your very own Pikachu following you around on-foot and collect the 3 other starters from strangers you meet around the region. However, your goal remains the same: catch them all and become the ultimate Pokémon Master.",
                websites,
                platforms,
                genres,
                TEinvolvedCompanies);

        //Assert
        Assert.assertEquals("Game stored does not equal expected.", expected, actual);
    }

    @Test
    public void getGamesByTitleSearch_returns_correct_game_structure() {
        //Arrange
        IGDBCover[] covers = new IGDBCover[3];
        covers[0] = new IGDBCover(200501, "co4aph");
        covers[1] = new IGDBCover(155707, "co3c57");
        covers[2] = new IGDBCover(124578, "co2o4i");
        List<TEVGDBSearchGame> games = new ArrayList<>();
        games.add(new TEVGDBSearchGame(3485, "Extreme-G", 875577600, covers[0].getCoverUrl()));
        games.add(new TEVGDBSearchGame(3486, "Extreme-G: XG2", 911260800, covers[1].getCoverUrl()));
        games.add(new TEVGDBSearchGame(4580, "Extreme-G 3", 998352000, covers[2].getCoverUrl()));
        //Act
        List<TEVGDBSearchGame> actual = igdbController.getGamesByTitleSearch("extreme-g");
        List<TEVGDBSearchGame> expected = games;
        //Assert
        Assert.assertEquals("Game stored does not equal expected.", expected, actual);
    }

    @Test
    public void getRandomGame_returns_a_game() {
        //Arrange
        //Act
        TEVGDBGame actual = igdbController.getRandomGame();
        //Assert
        Assert.assertNotNull(actual);
    }
}
