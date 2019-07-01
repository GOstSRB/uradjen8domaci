package jwd.wafepa;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Address;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;

@Component
public class TestData {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private RecordService recordService;
	
	@PostConstruct
	public void init() {
		
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity("Running"));
		activityService.save(new Activity("Boxing"));
		
		//pravimo 5 korisnika
		for (int i = 1; i <= 5; i++) {
			User user = new User();
			
			user.setFirstName("First name " + i);
			user.setLastName("Last name " + i);
			user.setEmail("email" + i + "@example.com");
			user.setPassword("123");
			userService.save(user);

			//za svakog korisnika pravimo po 3 adrese i 3 recorda
			for (int j = 1; j <= 3; j++) {
				Activity activity = new Activity();
				Address address = new Address();
				
				address.setNumber(Integer.toString(j));
				address.setStreat("Laze nancica");
				
				addressService.save(address);
				address.setUser(user);
				
				addressService.save(address);
				userService.save(user);
			
				Record record = new Record();
	            record.setDuration(j*5);
	            record.setTime(Integer.toString(i));
	            record.setIntensity(Integer.toString(i));
	            
	            recordService.save(record);
	            
//	            record.setActivity(activity);
	            record.setUser(user);
	            recordService.save(record);
	            
	            
	
	            
	          
	            
			}
//			Activity activity1 = activityService.save(new Activity("Swimmingh"));
//			Activity activity2 = activityService.save(new Activity("Runningh"));
//			
//			User user1 = new User();
//			user1.setFirstName("First name " + 10);
//			user1.setLastName("Last name " + 10);
//			user1.setEmail("email" + 10 + "@example.com");
//			user1.setPassword("1230");
//			user1 = userService.save(user1);
//
//			User user2 = new User();
//			user2.setFirstName("First name " + 20);
//			user2.setLastName("Last name " + 20);
//			user2.setEmail("email" + 20 + "@example.com");
//			user2.setPassword("1230");
//			user2 = userService.save(user2);
//			Record record1 = new Record();
//			record1.setTime("20/11/2018 08:00");
//			record1.setDuration(60);
//			record1.setIntensity("Low");
//			record1.setUser(user1);
//			record1.setActivity(activity);
//			recordService.save(record1);
			
//			Record record2 = new Record();
//			record2.setTime("21/11/2018 08:00");
//			record2.setDuration(60);
//			record2.setIntensity("Moderate");
//			record2.setUser(user2);
//			record2.setActivity(activity2);
//			recordService.save(record2);
		}
		
		}	
	}

