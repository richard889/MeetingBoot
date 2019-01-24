package com.MeetingBoot.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MeetingBoot.entity.Meetingdetail; 

public interface MeetingdetailRepository extends JpaRepository<Meetingdetail, Long>{
	
	@Query(value = "select A.* from Meetingdetail as A Where A.meetingID = ?1 And A.empID = ?2 ", nativeQuery = true)
	public List<Meetingdetail> findByMeetingdetail(long meetingID, long empID);	
	
//	@Query(value = "Select ROW_NUMBER() OVER () as id, A.meetingID, A.exceptDate, A.beginTime, A.endTime, count(1) as allowNum " +
//				   "  From Meetingdetail as A "+
//				   " where A.exceptDate >= now() "+
//				   " group by A.meetingID, A.exceptDate, A.beginTime, A.endTime "+
//				   " order by count(1) desc, A.exceptDate desc " , nativeQuery = true)
//	public List<Meetingcount> findByMeetingcount();	
	
	@Query(value = "Select A.meetingID, A.exceptDate, A.beginTime, A.endTime, count(1) as allowNum " +
				   "  From Meetingdetail as A "+
				   " where A.exceptDate >= now() "+
				   " group by A.meetingID, A.exceptDate, A.beginTime, A.endTime "+
				   " order by count(1) desc, A.exceptDate desc " , nativeQuery = true)
	public List<Object[]> findByMeetingcount();	
	
}
