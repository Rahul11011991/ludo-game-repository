package com.ludo.play.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "move")
public class Move {

  @Column
  @Id
  private int moveId;

  @Column
  private String status;

  @ManyToOne(optional = false)
  @JoinColumn(name = "participant_id")
  private Participant participant;


  @ManyToOne(optional = false)
  @JoinColumn(name = "game_id")
  private Game game;

  @OneToOne(targetEntity = Step.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Step step;

  @OneToOne(targetEntity = Token.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Token token;

  @OneToMany(targetEntity = MoveEventJunction.class, cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  private List<MoveEventJunction> moveEventJunction;

  @Column
  @CreatedBy
  private String createdBy;

  @Column
  @CreatedDate
  private Timestamp createdDate;

  @Column
  private String updatedBy;

  @Column
  @UpdateTimestamp
  private Timestamp updatedDate;
}
