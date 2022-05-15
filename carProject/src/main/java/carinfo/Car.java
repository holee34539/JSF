package carinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {
	private final String pattern = "MM-dd-yyyy";
	
	private String product_name;
	private String drivetrain;
	private String manufactured;
	private Integer horsepower;
	private Integer engine_size;
	private String comments;
	private String colour;
	private Integer rating;
	private String image_name;
	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getDrivetrain() {
		return drivetrain;
	}
	public void setDrivetrain(String drivetrain) {
		this.drivetrain = drivetrain;
	}
	public String getManufactured() {
		return manufactured;
	}
	public void setManufactured(String manufactured) {
		
		this.manufactured = dateTransform(manufactured);
	}
	public Integer getHorsepower() {
		return horsepower;
	}
	public void setHorsepower(Integer horsepower) {
		this.horsepower = horsepower;
	}
	public Integer getEngine_size() {
		return engine_size;
	}
	public void setEngine_size(Integer engine_size) {
		this.engine_size = engine_size;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	
	private String dateTransform(String date) {
		SimpleDateFormat dateParse = new SimpleDateFormat(pattern);
		try {
			Date newDate = dateParse.parse(date);
			return dateFormat(newDate.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String dateFormat(String date) {
		String[] arrOfDate = date.split(" ");
		return arrOfDate[1] + " " + arrOfDate[2] + ", " + arrOfDate[5];
	}
	
	public String toString() {
		return product_name;
	}
}
