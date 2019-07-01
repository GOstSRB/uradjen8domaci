package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="tblRecord")
public class Record {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="time", nullable=false)
	private String time;
	
	@Column(name="duration", nullable=false)
	private int duration;
	
	@Column(name="intensity")
	private String intensity;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	private Activity activity;

//	● Id - identifikator
//	● Time - tekstualna, obavezna vrednost
//	● Duration - celobrojna, obavezna vrednost
//	● Intensity - tekstualna vrednost
//	● User - veza sa instancom klase User (jedna beleška (o aktivnosti - record) može
//	pripadati samo jednom korisniku, dok jedan korisnik može imati više beleški. Veza je
//	dvosmerna, odnosno, trebalo bi kreirati drugi kraj veze u klasi User.
//	● Activity - veza sa instancom klase Activity (jedna beleška (record) može biti samo za
//	jedan tip aktivnosti, dok se za jednu aktivnost može vezati više beleški.Veza je
//	dvosmerna, odnosno, trebalo bi kreirati drugi kraj veze u klasi Activity.


	public Record() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

		public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if(!user.getRecords().contains(this)){
			user.getRecords().add(this);
		}
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
		if(!activity.getRecords().contains(this)){
			activity.getRecords().add(this);
		}
	}


}
