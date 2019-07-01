package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.repository.RecordRepository;
import jwd.wafepa.service.RecordService;

@Service
public class JpaRecordService implements RecordService{
	
	@Autowired
	private RecordRepository recordRepository; 

	@Override
	public List<Record> findAll() {
		
		return recordRepository.findAll();
	}

	@Override
	public Record findOne(Long id) {
		
		return recordRepository.findOne(id);
	}

	@Override
	public Record save(Record record) {
		
		return recordRepository.save(record);
	}

	@Override
	public void delete(Long id) {
		recordRepository.delete(id);
		
	}
	@Override
	public Page<Record> findAll(int page) {
		
		return recordRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public List<Record> findByUser(Long id) {
		
		return recordRepository.findByUser(id);
	}

	@Override
	public List<Record> findByUserId(Long id) {
		
		return recordRepository.findByUserId(id);
	}
	

}
