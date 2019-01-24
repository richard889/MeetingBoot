package com.MeetingBoot.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MeetingBoot.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

}
