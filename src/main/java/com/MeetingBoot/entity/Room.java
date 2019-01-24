package com.MeetingBoot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {

	@Id
	@Column(name = "roomID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long roomID;
	private String roomName;

    public Room() {
    }

	public long getRoomID() {
		return roomID;
	}

	public void setRoomID(long roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}	
	
}
