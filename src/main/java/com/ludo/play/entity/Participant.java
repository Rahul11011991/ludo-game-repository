package com.ludo.play.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "participant")
public class Participant {

  @Id
  @Column
  private int participantId;


  @Column
  private String status;

  @OneToOne(targetEntity = Game.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Game game;
  
  @Column
  @CreatedBy
  private String createdBy;

  @Column
  @CreatedDate
  private Timestamp createdDate;


}