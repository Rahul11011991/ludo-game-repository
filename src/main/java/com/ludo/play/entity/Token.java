package com.ludo.play.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "token_master")
public class Token {

  @Column
  @Id
  private int tokenId;
    
  @Column
  private int tokenNo;
  
}