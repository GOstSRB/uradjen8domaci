package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Record;
import jwd.wafepa.web.dto.RecordDTO;


@Component
public class RecordToRecordDTO implements Converter<Record, RecordDTO>{
	
	@Override
	public RecordDTO convert(Record record) {
		RecordDTO dto = new RecordDTO();
		
		dto.setId(record.getId());
		dto.setTime(record.getTime());
		dto.setDuration(record.getDuration());
		dto.setIntensity(record.getIntensity());
		
		
		return dto;
	}
	
	public List<RecordDTO> convert(List<Record> records){
		List<RecordDTO> ret = new ArrayList<>();
		
		for(Record record : records){
			ret.add(convert(record));
		}
		
		return ret;
	}

}
