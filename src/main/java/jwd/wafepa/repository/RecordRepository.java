package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{
	
	List<Record> findByUserId(Long id);
//	List<Record> findByUser(User user);
	List<Record> findByUser(Long id);
	
	List<Record> findByActivityId(Long activityId);
	List<Record> findByActivity(Activity activity);
}
