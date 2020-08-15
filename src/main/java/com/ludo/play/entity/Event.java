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
@Table(name = "event_master")
public class Event {
  
  @Id
  @Column
  private int eventId;
  
  @Column
  private String eventName;
  
  @Column
  private String eventDescription;
  
  @Column
  private String eventOperation;
  
  @Column
  private int eventValue;
  
  @Column
  @CreatedBy
  private String createdBy;

  @Column
  @CreatedDate
  private Timestamp createdDate;


}
