package com.ludo.play.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(Include.NON_DEFAULT)
public class ParticipantActionResponse {
  
  @JsonProperty
  private List<MoveResponse> moves;
  
  @JsonProperty
  private int responseCode;
  
  @JsonProperty
  private String errorMessage;

}
