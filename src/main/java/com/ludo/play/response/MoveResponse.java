package com.ludo.play.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(Include.NON_DEFAULT)
public class MoveResponse {

  @JsonProperty
  private int tokenNo;

  @JsonProperty
  private int stepNo;

  @JsonProperty
  private String eventName;

  @JsonProperty
  private String eventDescription;


}
