package com.ludo.play.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "game")
public class Game {
    
   @Id
   @Column
   private int gameId;
   
   @Column
   private Timestamp startTime;
   
   @Column
   private Timestamp endTime;
   
   @Column
   private String status;
   
   @Column
   private int noOfPlayers;
   
   @Column
   private int winnerParticipantId;
   
   @Column
   @CreatedBy
   private String createdBy;

   @Column
   @CreatedDate
   private Timestamp createdDate;
    
   
}
