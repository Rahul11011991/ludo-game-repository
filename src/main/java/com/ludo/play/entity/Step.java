package com.ludo.play.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "step_master")
public class Step {

  @Id
  @Column
  private int stepId;

  @Column
  private int stepNo;


}