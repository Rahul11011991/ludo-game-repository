package com.ludo.play.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ludo.play.entity.Move;

@Repository
public interface MoveRepository extends JpaRepository<Move, Integer> {

  @Query("SELECT m FROM Move m INNER JOIN m.game g INNER JOIN m.participant p INNER JOIN m.moveEventJunction mej "
      + " INNER JOIN mej.event WHERE g.gameId =:gameId AND p.participantId =:participantId ORDER BY mej.createdDate DESC")
  List<Move> getRecentEventsForGivenGameAndParticipant(@Param("gameId") int gameId,
      @Param("participantId") int participantId);

}
