package com.ludo.play.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ludo.play.entity.Event;
import com.ludo.play.entity.Game;
import com.ludo.play.entity.Move;
import com.ludo.play.entity.MoveEventJunction;
import com.ludo.play.entity.Participant;
import com.ludo.play.entity.Step;
import com.ludo.play.entity.Token;
import com.ludo.play.enums.ErrorMessageEnum;
import com.ludo.play.exception.GameException;
import com.ludo.play.repository.MoveRepository;
import com.ludo.play.response.ParticipantActionResponse;

@ExtendWith(MockitoExtension.class)
public class LudoSearchServiceImplTest {

	@InjectMocks
	LudoSearchServiceImpl ludoSearchServiceImpl;

	@Mock
	MoveRepository moveRepository;

	@Test
	public void givenInvalidGameIdAndParticipantId_ThenShouldThrowGameException() {

		Mockito.when(moveRepository.getRecentEventsForGivenGameAndParticipant(ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt())).thenReturn(new ArrayList<>());

		GameException gameException = Assertions.assertThrows(GameException.class, () -> {
			ludoSearchServiceImpl.getRecentEvents(1, 1);
		});

		Assertions.assertEquals(String.format(ErrorMessageEnum.EXC_EVENT_NOT_FOUND.getValue(), 1, 1),
				gameException.getMessage());

		Mockito.verify(moveRepository).getRecentEventsForGivenGameAndParticipant(ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt());

	}

	@Test
	public void givenValidGameIdAndParticipantId_ThenShouldReturnResponse() {

		Participant participant = Participant.builder().participantId(1).status("playing").build();

		Event event = Event.builder().eventId(1).eventName("ADD_1").eventOperation("PLUS").eventValue(1).build();

		Step step = new Step();
		step.setStepId(1);
		step.setStepNo(1);

		Token token = new Token();
		token.setTokenId(1);
		token.setTokenNo(1);

		Game game = Game.builder().gameId(1).createdBy("SYTEM").noOfPlayers(2)
				.createdDate(Timestamp.from(Instant.now())).build();

		List<MoveEventJunction> moveEventJunctionList1 = new ArrayList<>();

		List<MoveEventJunction> moveEventJunctionList2 = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {

			MoveEventJunction moveEventJunction = new MoveEventJunction();
			moveEventJunction.setMoveEventJunctionId(1);
			moveEventJunction.setEvent(event);
			moveEventJunction.setMoveId(1);
			moveEventJunction.setCreatedBy("SYTEM");
			moveEventJunction.setCreatedDate(
					Timestamp.valueOf(LocalDateTime.of(LocalDate.now().plusDays(i), LocalTime.of(0, 0))));
			moveEventJunctionList1.add(moveEventJunction);
		}

		for (int i = 1; i <= 10; i++) {

			MoveEventJunction moveEventJunction = new MoveEventJunction();
			moveEventJunction.setMoveEventJunctionId(1);
			moveEventJunction.setEvent(event);
			moveEventJunction.setMoveId(2);
			moveEventJunction.setCreatedBy("SYTEM");
			moveEventJunction.setCreatedDate(
					Timestamp.valueOf(LocalDateTime.of(LocalDate.now().minusDays(i), LocalTime.of(0, 0))));
			moveEventJunctionList2.add(moveEventJunction);
		}

		Move move1 = Move.builder().moveId(1).participant(participant).moveEventJunction(moveEventJunctionList1)
				.step(step).token(token).build();

		Move move2 = Move.builder().moveId(2).participant(participant).moveEventJunction(moveEventJunctionList2)
				.step(step).token(token).game(game).build();

		List<Move> moves = Arrays.asList(move1, move2);

		Mockito.when(moveRepository.getRecentEventsForGivenGameAndParticipant(ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt())).thenReturn(moves);

		Optional<ParticipantActionResponse> participantActionResponse = ludoSearchServiceImpl.getRecentEvents(1, 1);

		Assertions.assertNotNull(participantActionResponse.get());

		Assertions.assertEquals(5, participantActionResponse.get().getMoves().size());

		Mockito.verify(moveRepository).getRecentEventsForGivenGameAndParticipant(ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt());
	}

}
