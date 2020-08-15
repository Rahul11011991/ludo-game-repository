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

import lombok.Data;

@Data
@Entity
@Table(name = "move_event_juntion")
public class MoveEventJunction {

	@Column
	@Id
	private int moveEventJunctionId;

	@Column
	private int moveId;

	@OneToOne(targetEntity = Event.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Event event;

	@Column
	@CreatedBy
	private String createdBy;

	@Column
	@CreatedDate
	private Timestamp createdDate;

}
