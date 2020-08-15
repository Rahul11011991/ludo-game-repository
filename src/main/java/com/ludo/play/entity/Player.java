package com.ludo.play.entity;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@Table(name = "player")
public class Player {

  @Id
  @Column
  private int playerId;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private int currentAge;

  @Column
  private boolean gender;

  @OneToMany(targetEntity = Participant.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Participant> participant;

  @Column
  @CreatedBy
  private String createdBy;

  @Column
  @CreatedDate
  private Timestamp createdDate;


}