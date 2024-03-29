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

import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.UserDTOToUser;
import jwd.wafepa.support.UserToUserDTO;
import jwd.wafepa.web.dto.UserDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@Controller
@RequestMapping(value="/api/users")
public class ApiUserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDTOToUser toUser;
	
	@Autowired
	private UserToUserDTO toDto;
	
	@Autowired
	private RecordService recordService;
	

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUser(
			@RequestParam(defaultValue="0") int page){
		
		Page<User> usersPage = userService.findAll(page);
		List<User> users = usersPage.getContent();
		
		if(users == null || users.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(users), HttpStatus.OK);
	}
	
@RequestMapping(value="/{id}/records",method=RequestMethod.GET) ResponseEntity<List<Record>> getRecordsOfUser( @PathVariable Long id, @RequestParam(defaultValue="0") int page) {
		
		List<Record> recordPage = recordService.findByUserId(id);
//		List<Record> record = recordPage.getContent();
		
		if(recordPage == null || recordPage.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(recordPage, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		User user = userService.findOne(id);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				toDto.convert(user),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<User> delete(@PathVariable Long id){
		userService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.GET, params= {"firstName", "lastName"}) 
	ResponseEntity<List<UserDTO>> getByNameAndLast(
			@RequestParam (value="firstName", required=false ) String firstName,
			@RequestParam (value="lastName", required=false) String lastName,
			@RequestParam (defaultValue="0") int page) {
		List<User> users;
		
		if(firstName != null && lastName !=null) {
			users = userService.findByFirstnameContainingAndLastnameContaining(firstName, lastName);
		} else {
			users = userService.findAll();
		}
		
		return new ResponseEntity<>(toDto.convert(users), HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<UserDTO> add(
			@RequestBody
			@Validated
			UserRegistrationDTO newUser){
		if(newUser.getPassword()==null 
				|| newUser.getPassword().isEmpty()
				|| !newUser.getPassword().equals(newUser.getPasswordConfirm())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		user.setFirstName(newUser.getFirstname());
		user.setLastName(newUser.getLastname());
		
		User savedUser = userService.save(user);
		
		return new ResponseEntity<>(
				toDto.convert(savedUser), 
				HttpStatus.CREATED);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<UserDTO> edit(
			@RequestBody UserDTO user,
			@PathVariable Long id){
		
		if(id!=user.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User persisted = userService.save(toUser.convert(user));
		
		return new ResponseEntity<>(
				toDto.convert(persisted),
				HttpStatus.OK);
	}
	
	
	
}
