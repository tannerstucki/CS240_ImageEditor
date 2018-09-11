import java.util.*;
import java.io.*;
import java.lang.*;

public class pixel {
	
	int redValue;
	int greenValue;
	int blueValue;

	pixel (int red, int green, int blue) {
		redValue = red;
		greenValue = green;
		blueValue = blue;
	}

	int getRedValue(){
		return redValue;
	}

	int getGreenValue(){
		return greenValue;
	}

	int getBlueValue(){
		return blueValue;
	}

	void setRedValue(int red){
		redValue = red;
	}

	void setGreenValue(int green){
		greenValue = green;
	}

	void setBlueValue(int blue){
		blueValue = blue;
	}

}