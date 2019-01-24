package com.MeetingBoot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MeetingBoot.entity.Room;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	public List<Room> findAll(){		
		return roomRepository.findAll();
	}
	
	public Room findOne(long roomID) {
		return roomRepository.findById(roomID).get();
	}
	
	public Room save(Room room) {
		return roomRepository.save(room);
	}	

	public void delete(long roomID) {
		roomRepository.deleteById(roomID);
	}
}
