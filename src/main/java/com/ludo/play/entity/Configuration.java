package com.ludo.play.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "configuration_master")
public class Configuration {
  
  @Id
  @Column
  private int configurationId;
  
  @Column
  private String configurationKey;
  
  @Column
  private String configurationValue;
    
}