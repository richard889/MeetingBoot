package com.MeetingBoot.test;


import java.util.Date;

import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.MeetingBoot.MeetingBootApplication;
import com.MeetingBoot.Service.MeetingRepository;
import com.MeetingBoot.Service.MeetingService;
import com.MeetingBoot.Service.RoomRepository;
import com.MeetingBoot.entity.Meeting;


import javafx.application.Application;


//@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class)

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes={Persistence.class})
//@RunWith(SpringJUnit4ClassRunner.class)   // 1
//@ContextConfiguration(classes={WebAppConfiguration.class})

//@RunWith(SpringRunner.class)
//@SpringBootConfiguration 
//@SpringBootTest(classes = MeetingBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes={Persistence.class})

//@SpringApplicationConfiguration(classes = ApplicationAndConfiguration.class)
//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MeetingBootApplication.class)
public class MeetingTest {
//
	//@MockBean
//	@Autowired
//	private MeetingRepository meetingRepository;
//	
	@Autowired
	private MeetingService meetingService;	
	
	@Test
	public void addSave() {
		Meeting meeting = new Meeting();

		
//		meeting.setDiscussItem("Item Test");
//		meeting.setBeginTime("1400");
//		meeting.setEndTime("1600");
//		meeting.setDiscussItem("Item Test");
//		meeting.setCreatePID(1);
//		meeting.setCreateDate(new Date());
//		meeting.setUpdatePID(1);
//		meeting.setUpdateDate(new Date());
		
		//meetingRepository.save(meeting);		
	}
}
