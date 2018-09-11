import java.util.*;
import java.io.*;
import java.lang.*;

public class image {
	pixel [][] thisImage;
	int height;
	int width;

	image (String fileName) {
		try{
			File file = new File (fileName);
			Scanner scanner = new Scanner(file);
			scanner.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");
			String trash = scanner.next();
			width = scanner.nextInt();
			height = scanner.nextInt();
			int trash2 = scanner.nextInt();
			//System.out.println(height);
			//System.out.println(width);
			thisImage = new pixel [height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width ; j++) {
					int red = scanner.nextInt();
					int green = scanner.nextInt();
					int blue = scanner.nextInt();
					pixel tempPixel = new pixel(red, green, blue);
					thisImage [i][j] = tempPixel;
					//System.out.println(thisImage[i][j]);
				}
			}
			scanner.close();
		}
		catch (FileNotFoundException exception){
			System.out.println("USAGE: file note found");
		}
	}

	int getHeight(){
		return height;
	}

	int getWidth(){
		return width;
	}

	pixel [][] getArray(){
		return thisImage;
	}

	void emboss(){
		System.out.println(height);
		System.out.println(width);
		System.out.println(width);

		for (int i = width - 1; i > -1; i--) {
			for (int j = height - 1; j > -1; j--) {
				if (i == 0 || j == 0) {
					thisImage[i][j].setRedValue(128);
					thisImage[i][j].setGreenValue(128);
					thisImage[i][j].setBlueValue(128);
				}
				else{
					int redDiff = thisImage[i][j].getRedValue() - thisImage[i-1][j-1].getRedValue();
					int greenDiff = thisImage[i][j].getGreenValue() - thisImage[i-1][j-1].getGreenValue();
					int blueDiff = thisImage[i][j].getBlueValue() - thisImage[i-1][j-1].getBlueValue();

					//System.out.println("RED DIFF: " + redDiff + "GREEN DIFF: " + greenDiff + "BLUE DIFF: " + blueDiff);

					int maxDiff = redDiff;
					if (Math.abs(maxDiff) < Math.abs(greenDiff)) {
						maxDiff = greenDiff;
					}
					if (Math.abs(maxDiff) < Math.abs(blueDiff)) {
						maxDiff = blueDiff;
					}

					int v = 128 + maxDiff;
					if (v < 0) {
						v = 0;
					}
					else if (v > 255) {
						v = 255;
					}

					thisImage[i][j].setRedValue(v);
					thisImage[i][j].setGreenValue(v);
					thisImage[i][j].setBlueValue(v);
				}
			}
			
		}
	}
}