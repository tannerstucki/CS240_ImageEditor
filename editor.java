import java.util.*;
import java.io.*;
import java.lang.*;

public class editor {

	editor(){}

	image grayscaleImage(image thisImage){
		for (int i = 0; i < thisImage.getHeight(); i++) {
			for (int j = 0; j < thisImage.getWidth(); j++) {
				int totalValue = thisImage.getArray()[i][j].getRedValue() + thisImage.getArray()[i][j].getGreenValue() + thisImage.getArray()[i][j].getBlueValue();
				totalValue = totalValue / 3;
				thisImage.getArray()[i][j].setRedValue(totalValue);
				thisImage.getArray()[i][j].setGreenValue(totalValue);
				thisImage.getArray()[i][j].setBlueValue(totalValue);
			}			
		}
		return thisImage;
	}

	image invertImage(image thisImage){
			int newRedValue = 0;
			int newGreenValue = 0;
			int newBlueValue = 0;

			for (int i = 0; i < thisImage.getHeight(); i++) {
				for (int j = 0; j < thisImage.getWidth(); j++) {
					if (thisImage.getArray()[i][j].getRedValue() > 127.5) {
						double tempValue = (thisImage.getArray()[i][j].getRedValue() - 127.5);
						double tempRedValue = 127.5 - tempValue;
						newRedValue = (int) tempRedValue;
					}
					else{
						double tempValue = (127.5 - thisImage.getArray()[i][j].getRedValue());
						double tempRedValue = 127.5 + tempValue;
						newRedValue = (int) tempRedValue;	
					}
					if (thisImage.getArray()[i][j].getGreenValue() > 127.5) {
						double tempValue = (thisImage.getArray()[i][j].getGreenValue() - 127.5);
						double tempGreenValue = 127.5 - tempValue;
						newGreenValue = (int) tempGreenValue;
					}
					else{
						double tempValue = (127.5 - thisImage.getArray()[i][j].getGreenValue());
						double tempGreenValue = 127.5 + tempValue;
						newGreenValue = (int) tempGreenValue;
					}
					if (thisImage.getArray()[i][j].getBlueValue() > 127.5) {
						double tempValue = (thisImage.getArray()[i][j].getBlueValue() - 127.5);
						double tempBlueValue = 127.5 - tempValue;
						newBlueValue = (int) tempBlueValue;
					}
					else{
						double tempValue = (127.5 - thisImage.getArray()[i][j].getBlueValue());
						double tempBlueValue = 127.5 + tempValue;
						newBlueValue = (int) tempBlueValue;	
					}
					thisImage.getArray()[i][j].setRedValue(newRedValue);
					thisImage.getArray()[i][j].setGreenValue(newGreenValue);
					thisImage.getArray()[i][j].setBlueValue(newBlueValue);
				}			
			}
		return thisImage;
	}

	image embossImage(image thisImage){
		int v = 0;
		int maxDifference = 0;
		int redDiff = 0;
		int greenDiff = 0;
		int blueDiff = 0;

		for (int i = thisImage.getHeight()-1; i > -1; i--) {
			for (int j = thisImage.getWidth()-1; j > -1; j--) {
				if (i <= 0 || j <= 0) {
					thisImage.getArray()[i][j].setRedValue(128);
					thisImage.getArray()[i][j].setGreenValue(128);
					thisImage.getArray()[i][j].setBlueValue(128);
				}
				else {
					redDiff = thisImage.getArray()[i][j].getRedValue() - thisImage.getArray()[i-1][j-1].getRedValue();
					greenDiff = thisImage.getArray()[i][j].getGreenValue() - thisImage.getArray()[i-1][j-1].getGreenValue();
					blueDiff = thisImage.getArray()[i][j].getBlueValue() - thisImage.getArray()[i-1][j-1].getBlueValue();

					maxDifference = redDiff;
					if (i == 1 && j < 10) {
						/*System.out.println(thisImage.getArray()[i][j].getRedValue());
						System.out.println(thisImage.getArray()[i-1][j-1].getRedValue());
						System.out.println(thisImage.getArray()[i][j].getRedValue() - thisImage.getArray()[i-1][j-1].getRedValue());
						System.out.println(Math.abs(redDiff));
						System.out.println("\n");
						System.out.println(thisImage.getArray()[i][j].getGreenValue());
						System.out.println(thisImage.getArray()[i-1][j-1].getGreenValue());
						System.out.println(thisImage.getArray()[i][j].getGreenValue() - thisImage.getArray()[i-1][j-1].getGreenValue());
						System.out.println(Math.abs(greenDiff));
						System.out.println("\n");
						System.out.println(thisImage.getArray()[i][j].getBlueValue());
						System.out.println(thisImage.getArray()[i-1][j-1].getBlueValue());
						System.out.println(thisImage.getArray()[i][j].getBlueValue() - thisImage.getArray()[i-1][j-1].getBlueValue());
						System.out.println(Math.abs(blueDiff));
						System.out.println("\n");*/
						//System.out.println(redDiff);
						//System.out.println(greenDiff);
						//System.out.println(blueDiff);	
					}
					if (Math.abs(maxDifference) < Math.abs(greenDiff)) {
						maxDifference = greenDiff;
					}
					if (Math.abs(maxDifference) < Math.abs(blueDiff)) {
						maxDifference = blueDiff;
					}

					/*if (i == 1 && j < 10) {
						System.out.println(maxDifference);
					}*/
					
					v = 128 + maxDifference;
					
					/*if (i == 1 && j < 10) {
						System.out.println(v);
					}*/

					if (v < 0) {
						v = 0;
					}
					else if (v > 255) {
						v = 255;
					}
				}

				/*if (i == 1 && j < 10) {
					System.out.println(v);
				}*/
				/*if (i == 1 && j < 10) {
					System.out.println(v);
					System.out.println("\n");
					System.out.println("\n");
				}*/

				thisImage.getArray()[i][j].setRedValue(v);
				thisImage.getArray()[i][j].setGreenValue(v);
				thisImage.getArray()[i][j].setBlueValue(v);
				/*System.out.println(v);
				System.out.println(thisImage.getArray()[i][j].getRedValue());
				System.out.println(thisImage.getArray()[i][j].getGreenValue());
				System.out.println(thisImage.getArray()[i][j].getBlueValue());*/
			}			
		}
		return thisImage;
	}

	image motionblurImage(image thisImage, String blurValue){
		int redAve = 0;
		int greenAve = 0;
		int blueAve = 0;
		int totalPixels = 0;
		int n = Integer.parseInt(blurValue);

		for (int i = 0; i < thisImage.getHeight(); i++) {
			for (int j = 0; j < thisImage.getWidth(); j++) {
				redAve = 0;
				greenAve = 0;
				blueAve = 0;
				totalPixels = 0;

				for (int k = 0; k < n ; k++) {
					if (j + k < thisImage.getWidth()) {
						redAve += thisImage.getArray()[i][j+k].getRedValue();
						greenAve += thisImage.getArray()[i][j+k].getGreenValue();
						blueAve += thisImage.getArray()[i][j+k].getBlueValue();
						totalPixels++;
					}
				}

				redAve = redAve / totalPixels;
				greenAve = greenAve / totalPixels;
				blueAve = blueAve / totalPixels;

				thisImage.getArray()[i][j].setRedValue(redAve);
				thisImage.getArray()[i][j].setGreenValue(greenAve);
				thisImage.getArray()[i][j].setBlueValue(blueAve);
			}			
		}
		return thisImage;
	}

}