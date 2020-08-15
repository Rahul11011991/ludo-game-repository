package com.ludo.play.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.ludo.play.entity.Event;
import com.ludo.play.entity.Move;
import com.ludo.play.entity.MoveEventJunction;
import com.ludo.play.enums.ErrorMessageEnum;
import com.ludo.play.exception.GameException;
import com.ludo.play.repository.MoveRepository;
import com.ludo.play.response.MoveResponse;
import com.ludo.play.response.ParticipantActionResponse;
import com.ludo.play.service.LudoSearchService;

@Service
public class LudoSearchServiceImpl implements LudoSearchService {

  @Autowired
  private MoveRepository moveRepository;

  @Override
  public Optional<ParticipantActionResponse> getRecentEvents(int gameId, int participantId) {

    ParticipantActionResponse participantActionResponse = null;
    List<MoveResponse> moveResponseList = new ArrayList<>();

    List<Move> moves =
        moveRepository.getRecentEventsForGivenGameAndParticipant(gameId, participantId);

    if (!CollectionUtils.isEmpty(moves)) {

      Map<Integer, List<Move>> movesMap =
          moves.stream().collect(Collectors.groupingBy(Move::getMoveId));

      List<MoveEventJunction> moveEventJunctionList =
          moves.stream().map(Move::getMoveEventJunction).flatMap(List<MoveEventJunction>::stream)
              .sorted((m1, m2) -> m2.getCreatedDate().compareTo(m1.getCreatedDate())).limit(5)
              .collect(Collectors.toList());

      for (MoveEventJunction moveEventJunction : moveEventJunctionList) {

        Event event = moveEventJunction.getEvent();


        event.getEventOperation();

        Move move = null != movesMap.get(moveEventJunction.getMoveId())
            ? movesMap.get(moveEventJunction.getMoveId()).get(0) : null;
            
        if (Objects.nonNull(move)) {

          MoveResponse moveResponse = MoveResponse.builder().stepNo(move.getStep().getStepNo())
              .tokenNo(move.getToken().getTokenNo()).eventName(event.getEventName())
              .eventDescription(event.getEventDescription()).build();

          moveResponseList.add(moveResponse);

        }
      }
      participantActionResponse =
          ParticipantActionResponse.builder().moves(moveResponseList).build();
      return Optional.of(participantActionResponse);
    } else {
      throw new GameException(
          String.format(ErrorMessageEnum.EXC_EVENT_NOT_FOUND.getValue(), participantId, gameId));
    }


  }

}
