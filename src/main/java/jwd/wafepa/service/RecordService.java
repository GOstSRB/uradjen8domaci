package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Record;


public interface RecordService {
//	Pomoću radnog okvira Spring Boot implementirati sledeći REST API:
//		GET /api/records - preuzimanje svih beleški (paginirano)
//		GET /api/records/{id} - preuzimanje beleške po zadatom id-u
//		POST /api/records - dodavanje nove beleške
//		PUT /api/records/{id} - izmena postojeće beleške
//		DELETE /api/records/{id} - brisanje postojeće beleške
//		GET /api/users/{user_id}/records - preuzimanje svih beleški jednog korisnika
	
	
	
	
	
	List<Record> findAll();
	Record findOne(Long id);
	Record save(Record record);
	Page<Record> findAll(int page);
	
	
	
	void delete(Long id);

	List<Record> findByUser(Long id);
	List<Record> findByUserId(Long id);
}
