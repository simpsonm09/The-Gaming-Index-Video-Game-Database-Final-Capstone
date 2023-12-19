package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Comment;
import com.techelevator.model.Review;
import com.techelevator.model.TEVGDBModels.TEVGDBGame;
import com.techelevator.model.TEVGDBModels.TEVGDBSearchGame;
import com.techelevator.model.User;
import com.techelevator.model.UserList;
import com.techelevator.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class GameDatabaseController {

    private final UserService userService;
    private final TEVGDBService gameService;
    private final CommentService commentService;
    private final ReviewService reviewService;
    private final UserListService userListService;

    private final IGDBController igdbController;

    public GameDatabaseController(UserService userService, TEVGDBService gameService, CommentService commentService, ReviewService reviewService, UserListService userListService, IGDBController igdbController) {
        this.userService = userService;
        this.gameService = gameService;
        this.commentService = commentService;
        this.reviewService = reviewService;
        this.userListService = userListService;
        this.igdbController = igdbController;
    }

    //User endpoints unrelated to authentication

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
        return user;
    }

    //Game endpoints, create and update game not available, handled in getGameById

    @RequestMapping(path = "/game/{id}", method = RequestMethod.GET)
    public TEVGDBGame getGameById(@PathVariable int id) {
        TEVGDBGame game = gameService.getGameById(id);
        if (game == null) {
            game = igdbController.getGameById(id);
            if (game != null) {
                gameService.createGame(game);
            }
            game = gameService.getGameById(id);
        }
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game Not Found");
        }
        return game;
    }

    @RequestMapping(path = "/game", method = RequestMethod.GET)
    public List<TEVGDBSearchGame> getGamesBySearchTitle(@RequestParam String stringSearch) {
        List<TEVGDBSearchGame> games = igdbController.getGamesByTitleSearch(stringSearch);
        if (games == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Games Not Found");
        }
        return games;
    }

    @RequestMapping(path = "/randomGame", method = RequestMethod.GET)
    public TEVGDBGame getRandomGame() {
        TEVGDBGame game = igdbController.getRandomGame();
        TEVGDBGame dbGame = gameService.getGameById(game.getId());
        if (dbGame == null) {
            dbGame = gameService.createGame(game);
        }

        if (game == null || dbGame == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Random Game Not Found");
        }
        return dbGame;
    }

    @RequestMapping(path = "/game/{id}", method = RequestMethod.DELETE)
    public void deleteGameById(@PathVariable int id) {
        boolean result = gameService.deleteGameById(id);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Delete Game");
        }
    }

    //Comment endpoints

    @RequestMapping(path = "/review/{id}/comments", method = RequestMethod.GET)
    public List<Comment> getCommentsByReviewId(@PathVariable int id) {
        List<Comment> comments = commentService.getCommentsByReviewId(id);
        if (comments.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Comments Found for Review");
        }
        return comments;
    }

    @RequestMapping(path = "/comment/{id}", method = RequestMethod.GET)
    public Comment getCommentById(@PathVariable int id) {
        Comment comment = commentService.getCommentById(id);
        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment Not Found");
        }
        return comment;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/comment", method = RequestMethod.POST)
    public Comment createComment(@Valid @RequestBody Comment comment) {
        Comment newComment = commentService.createComment(comment);
        if (newComment == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Create Comment");
        }
        return newComment;
    }

    @RequestMapping(path = "/comment/{id}", method = RequestMethod.PUT)
    public Comment updateComment(@Valid @RequestBody Comment comment, @PathVariable int id) {
        if (comment.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch between URL and body");
        }
        try {
            return commentService.updateComment(comment);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment Not Found");
        }
    }

    @RequestMapping(path = "/comment/{id}", method = RequestMethod.DELETE)
    public void deleteCommentById(@PathVariable int id) {
        boolean result = commentService.deleteCommentById(id);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Delete Comment");
        }
    }

    //Review endpoints

    @RequestMapping(path = "/game/{id}/reviews", method = RequestMethod.GET)
    public List<Review> getReviewsByGameId(@PathVariable int id) {
        List<Review> reviews = reviewService.getReviewsByGameId(id);
        if (reviews.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Reviews Found for Game");
        }
        return reviews;
    }

    @RequestMapping(path = "/review/{id}", method = RequestMethod.GET)
    public Review getReviewById(@PathVariable int id) {
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        }
        return review;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/review", method = RequestMethod.POST)
    public Review createReview(@Valid @RequestBody Review review) {
        Review newReview = reviewService.createReview(review);
        if (newReview == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Create Review");
        }
        return newReview;
    }

    @RequestMapping(path = "/review/{id}", method = RequestMethod.PUT)
    public Review updateReview(@Valid @RequestBody Review review, @PathVariable int id) {
        if (review.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch between URL and body");
        }
        try {
            return reviewService.updateReview(review);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        }
    }

    @RequestMapping(path = "/review/{id}", method = RequestMethod.DELETE)
    public void deleteReviewById(@PathVariable int id) {
        boolean result = reviewService.deleteReviewById(id);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Delete Review");
        }
    }

    //UserLists endpoints

    @RequestMapping(path = "/user/{id}/userlists", method = RequestMethod.GET)
    public List<UserList> getUserListsByUserId(@PathVariable int id) {
        List<UserList> userLists = userListService.getUserListsByUserId(id);
        if (userLists.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No UserLists Found for User");
        }
        return userLists;
    }

    @RequestMapping(path = "/user/{userId}/game/{gameId}/userlists", method = RequestMethod.GET)
    public List<UserList> getUserListByGameIdUserId(@PathVariable int userId, @PathVariable int gameId) {
        List<UserList> userLists = userListService.getUserListByGameIdUserId(gameId, userId);
        if (userLists.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No UserLists Found for User and Game");
        }
        return userLists;
    }

    @RequestMapping(path = "/userlist/{id}", method = RequestMethod.GET)
    public UserList getUserListById(@PathVariable int id) {
        UserList userList = userListService.getUserListById(id);
        if (userList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserList Not Found");
        }
        return userList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/userlist", method = RequestMethod.POST)
    public UserList createUserList(@Valid @RequestBody UserList userList) {
        UserList newUserList = userListService.createUserList(userList);
        if (newUserList == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Create UserList");
        }
        return newUserList;
    }

    @RequestMapping(path = "/userlist/{id}", method = RequestMethod.PUT)
    public UserList updateUserList(@Valid @RequestBody UserList userList, @PathVariable int id) {
        if (userList.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mismatch between URL and body");
        }
        try {
            return userListService.updateUserList(userList);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/userlistAdd/{listId}/{gameId}", method = RequestMethod.POST)
    public UserList addGameToUserList(@PathVariable int listId, @PathVariable int gameId) {
        return userListService.addGameToUserList(listId, gameId);
    }

    @RequestMapping(path = "/userlistRemove/{listId}/{gameId}", method = RequestMethod.DELETE)
    public UserList removeGameFromUserList(@PathVariable int listId, @PathVariable int gameId) {
        return userListService.removeGameFromUserList(listId, gameId);
    }

    @RequestMapping(path = "/userlist/{id}", method = RequestMethod.DELETE)
    public void deleteUserListById(@PathVariable int id) {
        boolean result = userListService.deleteUserListById(id);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to Delete UserList");
        }
    }
}
