package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Address;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.RecordDTOToRecord;
import jwd.wafepa.support.RecordToRecordDTO;
import jwd.wafepa.web.dto.AddressDTO;
import jwd.wafepa.web.dto.RecordDTO;
import jwd.wafepa.web.dto.UserDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;


@Controller
@RequestMapping(value="/api/records")
public class ApiRecordController {
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private RecordDTOToRecord toRecord;
	
	@Autowired
	private RecordToRecordDTO toDto;

	@RequestMapping(method=RequestMethod.GET) ResponseEntity<List<Record>> getRecords( @RequestParam(defaultValue="0") int page) {
		
		Page<Record> recordPage = recordService.findAll(page);
		List<Record> record = recordPage.getContent();
		
		if(record == null || record.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(record, HttpStatus.OK);
	}
	
//	@RequestMapping(method=RequestMethod.GET) ResponseEntity<List<Record>> getRecordsOfUser( @PathVariable Long userId, @RequestParam(defaultValue="0") int page) {
//		
//		List<Record> recordPage = recordService.findByUser(userId);
//		List<Record> record = recordPage.getContent();
//		
//		if(recordPage == null || recordPage.isEmpty()){
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(recordPage, HttpStatus.OK);
//	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) ResponseEntity<Record> getRecord (@PathVariable Long id) {
		
		Record record = recordService.findOne(id);
		
		if(record== null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<> (record, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)	ResponseEntity<Record> delete(@PathVariable Long id){
		recordService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")public ResponseEntity<RecordDTO> add(
	@RequestBody  @Validated RecordDTO newRecord, @PathVariable Long userId, @PathVariable Long id){

		if(newRecord.getDuration()> 200) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
//		User user = userService.findOne(userId);
//		Activity activity = activityService.findOne(id);
		
//		if(user == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
Record record = new Record();
record.setTime(newRecord.getTime());
record.setDuration(newRecord.getDuration());
record.setIntensity(newRecord.getIntensity());
record.setActivity(newRecord.getActivity());
record.setUser(newRecord.getUser());

Record savedRecord = recordService.save(record);

return new ResponseEntity<>(
		toDto.convert(savedRecord), 
		HttpStatus.CREATED);
}
//	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
//public ResponseEntity<RecordDTO> add(
//	@Validated @RequestBody RecordDTO newRecord){
//
//Record savedRecord = recordService.save(
//		toRecord.convert(newRecord));
//
//return new ResponseEntity<>(
//		toDto.convert(savedRecord), 
//		HttpStatus.CREATED);
//}

	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<RecordDTO> edit(
			@RequestBody RecordDTO record,
			@PathVariable Long id){
		
		if(id!=record.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Record persisted = recordService.save(toRecord.convert(record));
		
		return new ResponseEntity<>(
				toDto.convert(persisted),
				HttpStatus.OK);
	}
		
}
