package carproject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name="cp")
public class ColorPickerBean {

	private String popupColor;
	
	public void setPopupColor(String popupColor) {
		this.popupColor = popupColor;
	}
	
	public String getPopupColor() {
		return popupColor;
	}
}
