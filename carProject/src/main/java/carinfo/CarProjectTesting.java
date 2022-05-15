package carinfo;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.primefaces.event.SelectEvent;

import validation.DriveTrainValidate;
import validation.EngineSizeValidate;
import validation.HorsePowerValidate;
import validation.ProductNameValidate;



@ManagedBean(name="cpt")
@ViewScoped
public class CarProjectTesting{
	
	private List<Car> cars = null;
	
	@NotNull(message = "Name cannot be null")
	@Size(max = 254, message="No more than 254 characters")
	private String selectedCar;
	
	//@NotNull(message = "Drivetrain cannot be null")
	@Size(max = 254, message="No more than 254 characters")
	private String selectedDrivetrain;
	
	
	private String selectedDate;
	
	@Min(value = 0, message = "Horsepower should not be less than 0")
	private Integer selectedPower;
	
	@Min(value = 0, message = "Engine size should not be less than 0")
	private Integer selectedEngine;
	
	@Size(max = 200, message="No more than 200 characters")
	private String selectedComment;
	private Integer selectedRating;
	private String selectedImage;
	private String selectedMenu;
	private String selectType;
	
	private boolean validName = true;
	private boolean validDrivertrain = true;
	private boolean validPower = true;
	private boolean validEngine = true;
		
	private EngineSizeValidate esValidate;
	private HorsePowerValidate hpValidate;
	private DriveTrainValidate dtValidate;
	private ProductNameValidate pnValidate;
	
	
	private Date date;
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
	
	public CarProjectTesting() throws JAXBException, URISyntaxException {
		init();
	}
	
	public void init() throws JAXBException, URISyntaxException {
		unMarshalingExample();
		Car car = cars.get(0);
		selectType = car.getProduct_name();
		setSelectedCar(car.getProduct_name());
		setSelectedDrivetrain(car.getDrivetrain());
		setSelectedDate(car.getManufactured());
		setSelectedPower(car.getHorsepower().toString());
		setSelectedEngine(car.getEngine_size().toString());
		setSelectedComment(car.getComments());
		setSelectedRating(car.getRating());
		setSelectedImage(car.getImage_name());
	}
	
	private void unMarshalingExample() throws JAXBException, URISyntaxException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Cars.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		URL resource = getClass().getClassLoader().getResource("dataSource.xml");
//		System.out.println(resource.toURI().toString());
		
		Cars cars = (Cars) jaxbUnmarshaller.unmarshal(new File(resource.toURI()) );
//		Cars cars = (Cars) jaxbUnmarshaller.unmarshal(new File("xmlDataSource/dataSource.xml") );
		
//		for(Car car : cars.getCars()) {
//			System.out.println(car.getImage_name());
//			System.out.println(car.getProduct_name());
//			System.out.println(car.getManufactured());
//		}
		setCars(cars.getCars());
	}
	
	public void displayMessage(String Error_Type, String Error_Message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Error_Type + "Error", Error_Message));
	}
	
	public void onCarChange() {
		if (getValidMenu()) {
			System.out.println("current is " + selectedMenu);
			getCarInfo(selectedMenu);
		} else {
			selectedMenu = selectType;
			String Error_Type = "Selection ";
			String Error_Message = "Please Input Valid Data First.";
			displayMessage(Error_Type, Error_Message);
		}
	}
	
	public void getCarInfo(String carName) {
	
		for (Car car : cars) {
			if (carName.equals(car.getProduct_name())) {
				setSelectedCar(car.getProduct_name());
				setSelectedDrivetrain(car.getDrivetrain());
				setSelectedDate(car.getManufactured());
				setSelectedPower(car.getHorsepower().toString());
				setSelectedEngine(car.getEngine_size().toString());
				setSelectedComment(car.getComments());
				setSelectedRating(car.getRating());
				setSelectedImage(car.getImage_name());
			}
		}
		selectType = selectedMenu;
	}
	
	public void resetCar() {
		getCarInfo(selectType);
	}
	
	public List<Car> getCars() {
		return cars;
	}
	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public void setSelectedCar(String selectedCar) {
			
		pnValidate = new ProductNameValidate();
		if (!pnValidate.checkFormat(selectedCar.toString())) {
			System.out.println("error message");
			validName = false;
			this.selectedCar = "";
			String Error_Type = "Product Name ";
			String Error_Message = "Product Name cannot be Empty.";
			displayMessage(Error_Type, Error_Message);
		}
		else {
			validName = true;
			this.selectedCar = selectedCar;
		}
		
		
		this.selectedCar = selectedCar;
	}
	
	public String getSelectedMenu() {
		return selectedMenu;
	}
	
	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}
	
	public String getSelectedCar() {
		return selectedCar;
	}
	
	public String getSelectedDrivetrain() {
		return selectedDrivetrain;
	}
	
	public void setSelectedDrivetrain(String selectedDrivetrain) {
		
		dtValidate = new DriveTrainValidate();
		if (!dtValidate.checkFormat(selectedDrivetrain.toString())) {
			System.out.println("error message");
			validDrivertrain = false;
			this.selectedDrivetrain = "";
			String Error_Type = "Drivertrain ";
			String Error_Message = "Drivertrain cannot be Empty.";
			displayMessage(Error_Type, Error_Message);
		}
		else {
			validDrivertrain = true;
			this.selectedDrivetrain = selectedDrivetrain;
		}
	}
	
 	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getSelectedPower() {
		return selectedPower.toString();
	}

	public void setSelectedPower(String selectedPower) {
		
		hpValidate = new HorsePowerValidate();
		if (!hpValidate.checkFormat(selectedPower.toString())) {
			
			validPower = false;
			this.selectedPower = 0;
			String Error_Type = "Horsepower ";
			String Error_Message = "Please Input Number Greater than 0.";
			displayMessage(Error_Type, Error_Message);
		}
		else {
			validPower = true;
			this.selectedPower = Integer.valueOf(selectedPower);
		}
		
	}

	public String getSelectedEngine() {
		return selectedEngine.toString();
	}

	public void setSelectedEngine(String selectedEngine) {
		esValidate = new EngineSizeValidate();
		if (!esValidate.checkFormat(selectedEngine.toString())) {
			System.out.println("got null here");
			validEngine = false;
			this.selectedEngine = 0;
			String Error_Type = "Engine Size ";
			String Error_Message = "Please Input Number Greater than 0.";
			displayMessage(Error_Type, Error_Message);
		}
		else {
			validEngine = true;
			this.selectedEngine = Integer.valueOf(selectedEngine);
		}
	}

	public String getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(String selectedComment) {
		this.selectedComment = selectedComment;
	}

	public Integer getSelectedRating() {
		return selectedRating;
	}

	public void setSelectedRating(Integer selectedRating) {
		this.selectedRating = selectedRating;
	}

	public String getSelectedImage() {
		return selectedImage;
	}
	
	public void setSelectedImage(String selectedImage) {
		this.selectedImage = selectedImage;
	}
	
	public void setValidEngine(boolean validEngine) {
		this.validEngine = validEngine;
	}
	
	public boolean getValidEngine() {
		return validEngine;
	}
	
	
	public boolean getValidMenu() {
		return validDrivertrain && validPower && validEngine && validName;
	}
	
	public static void main(String[] args) throws JAXBException, URISyntaxException {
		// unMarshalingExample();
		CarProjectTesting cpt = new CarProjectTesting();
	}
}
