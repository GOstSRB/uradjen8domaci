package jwd.wafepa.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.User;

public class RecordDTO {

	private Long id;
	private String time;
	
	@Min(value=1)
	@Max(value=200)
	private int duration;
	private String intensity;
	private User user;
	private Activity activity;
	
	
	
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
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
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
}
