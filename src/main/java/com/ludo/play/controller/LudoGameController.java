package com.ludo.play.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ludo.play.enums.ErrorMessageEnum;
import com.ludo.play.response.ParticipantActionResponse;
import com.ludo.play.service.LudoSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/ludo-game-service")
public class LudoGameController {


  @Autowired
  private LudoSearchService ludoSearchService;

  @ApiOperation(value = "Reads the last 5 event of the participant")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Read"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 405, message = "Method Not Allowed"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @GetMapping(value = "/game/{gameId}/participant/{participantId}")
  public ResponseEntity<ParticipantActionResponse> readEvents(@PathVariable("gameId") int gameId,
      @PathVariable("participantId") int participantId) {

    Optional<ParticipantActionResponse> participantActionResponseOptional =
        ludoSearchService.getRecentEvents(gameId, participantId);

    if (participantActionResponseOptional.isPresent()) {

      return ResponseEntity.status(HttpStatus.OK).body(participantActionResponseOptional.get());

    } else {

      ParticipantActionResponse participantActionResponse = ParticipantActionResponse.builder()
          .errorMessage(
              String.format(ErrorMessageEnum.EXC_EVENT_NOT_FOUND.getValue(), gameId, participantId))
          .responseCode(HttpStatus.NOT_FOUND.value()).build();

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(participantActionResponse);

    }


  }

}
