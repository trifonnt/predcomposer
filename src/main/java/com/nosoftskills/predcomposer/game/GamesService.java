package com.nosoftskills.predcomposer.game;

import com.nosoftskills.predcomposer.model.Competition;
import com.nosoftskills.predcomposer.model.Game;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
@Stateless
public class GamesService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Game> getFutureGamesForCompetition(Competition competition) {
        TypedQuery<Game> gamesQuery = entityManager
                .createNamedQuery("getFutureGamesForCompetition", Game.class);
        gamesQuery.setParameter("competition", competition);
        gamesQuery.setParameter("after", LocalDate.now().atStartOfDay());
        return gamesQuery.getResultList();
    }

    public List<Game> getCompletedGamesForCompetition(Competition competition) {
        TypedQuery<Game> gamesQuery = entityManager
                .createNamedQuery("getCompletedGamesForCompetition", Game.class);
        gamesQuery.setParameter("competition", competition);
        return gamesQuery.getResultList();
    }

    public Game toggleLockedMode(Game game) {
        Game changedGame = entityManager.merge(game);
        changedGame.setLocked(!game.isLocked());
        return changedGame;
    }

    public Game storeGame(Game game) {
        if (game.getId() == null) {
            entityManager.persist(game);
            return game;
        } else {
            return entityManager.merge(game);
        }
    }
}
