import java.util.*;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;

public class ImageEditor {
	public static void main (String[] args) throws IOException{
		image thisImage = new image (args[0]);
		editor thisEditor = new editor();
		if (args[2].equals("grayscale")) {
			thisImage = thisEditor.grayscaleImage(thisImage);
			writer(thisImage, args[1]);
		}
		else if (args[2].equals("invert")) {
			thisImage = thisEditor.invertImage(thisImage);
			writer(thisImage, args[1]);
		}
		else if (args[2].equals("emboss")) {
			thisImage = thisEditor.embossImage(thisImage);
			writer(thisImage, args[1]);
		}
		/*else if (args[2].equals("emboss")) {
			thisImage.emboss();
			writer(thisImage, args[1]);
		}*/
		else if (args[2].equals("motionblur")) {
			thisImage = thisEditor.motionblurImage(thisImage, args[3]);
			writer(thisImage, args[1]);
		}
		else if (args[2].equals("nothing")) {
			writer(thisImage, args[1]);
		}
	}

	public static void writer(image thisImage, String outFileName) throws IOException{
		PrintWriter pw = new PrintWriter(new File(outFileName));
		pw.println("P3"); 
		pw.println(thisImage.getWidth() + " " + thisImage.getHeight());
		pw.println("255");
		for (int i = 0; i < thisImage.getHeight(); i++) {
			for (int j = 0; j < thisImage.getWidth(); j++) {
				pw.println(thisImage.getArray()[i][j].getRedValue() + " ");
				pw.println(thisImage.getArray()[i][j].getGreenValue() + " ");
				pw.println(thisImage.getArray()[i][j].getBlueValue());
				if (j < thisImage.getWidth() - 1) {
					pw.println(" ");
				}
			}
		}
		pw.close();
	}
}