package com.ludo.play.service;

import java.util.Optional;
import com.ludo.play.response.ParticipantActionResponse;

@FunctionalInterface
public interface LudoSearchService {

  public Optional<ParticipantActionResponse> getRecentEvents(int gameId, int participantId);

}
