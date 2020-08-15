package com.ludo.play.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.ludo.play.enums.ErrorMessageEnum;
import com.ludo.play.response.MoveResponse;
import com.ludo.play.response.ParticipantActionResponse;
import com.ludo.play.service.LudoSearchService;

@SpringBootTest
@AutoConfigureMockMvc
public class LudoGameControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  LudoSearchService ludoSearchService;


  @Test
  public void givenInvalidGameIdAndParticipant_ThenShouldThrowNotFoundResponse() throws Exception {

    Mockito
        .when(
            ludoSearchService.getRecentEvents(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        .thenReturn(Optional.empty());

    mockMvc
        .perform(get("/v1/ludo-game-service/game/{gameId}/participant/{participantId}", 1, 2)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(handler().handlerType(LudoGameController.class))
        .andExpect(handler().methodName("readEvents")).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.responseCode").value(404)).andExpect(jsonPath("$.errorMessage")
            .value(String.format(ErrorMessageEnum.EXC_EVENT_NOT_FOUND.getValue(), 1, 2)));

    Mockito.verify(ludoSearchService).getRecentEvents(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt());

  }


  @Test
  public void givenValidGameIdAndParticipant_ThenSuccessResponse() throws Exception {


    MoveResponse moveResponse = MoveResponse.builder().eventDescription("event-description")
        .eventName("event-name").stepNo(1).tokenNo(1).build();

    List<MoveResponse> moves = Arrays.asList(moveResponse);

    Mockito
        .when(
            ludoSearchService.getRecentEvents(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        .thenReturn(Optional.of(ParticipantActionResponse.builder().moves(moves).build()));

    mockMvc
        .perform(get("/v1/ludo-game-service/game/{gameId}/participant/{participantId}", 1, 2)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(handler().handlerType(LudoGameController.class))
        .andExpect(handler().methodName("readEvents")).andExpect(status().isOk());


    Mockito.verify(ludoSearchService).getRecentEvents(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt());

  }


}
