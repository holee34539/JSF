package carproject;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="rb")
public class RatingBean {
	
	private Integer rating;
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
}
