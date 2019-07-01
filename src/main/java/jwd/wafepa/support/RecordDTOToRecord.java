package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import jwd.wafepa.model.Record;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.web.dto.RecordDTO;

@Component
public class RecordDTOToRecord implements Converter<RecordDTO, Record>{
	@Autowired
	RecordService recordService;

	@Override
	public Record convert(RecordDTO dto) {
		Record record = new Record();
		
		if(dto.getId()!=null){
			record = recordService.findOne(dto.getId());
			
			if(record == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant activity");
			}
		}
		
		record.setId(dto.getId());
		record.setIntensity(dto.getIntensity());
		
		return record;
	}
	
	public List<Record> convert (List<RecordDTO> dtoRecords){
		List<Record> records = new ArrayList<>();
		
		for(RecordDTO dto : dtoRecords){
			records.add(convert(dto));
		}
		
		return records;
	}
}
