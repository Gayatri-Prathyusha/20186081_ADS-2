import java.awt.Color;
import java.awt.image.BufferedImage;
public class SeamCarver {
	private Picture picture;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		if(picture == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = picture;

	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return picture.width();
	}

	// height of current picture
	public int height() {
		return picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || picture.width() - 1 == x || picture.height() - 1 == y) {
			return 1000;
		}
		Color top = picture.get(x, y - 1);
		Color bottom = picture.get(x, y + 1);
		Color left = picture.get(x - 1, y);
		Color right = picture.get(x + 1, y);
		int red = right.getRed() - left.getRed();
		int blue = right.getBlue() - left.getBlue();
		int green = right.getGreen() - left.getGreen();
		int horizontal = red * red + blue * blue + green * green;
		int redv = top.getRed() - bottom.getRed();
		int bluev = top.getBlue() - bottom.getBlue();
		int greenv = top.getGreen() - bottom.getGreen();
		int vertical = redv * redv + bluev * bluev + greenv * greenv;
		double energy = Math.sqrt(horizontal + vertical);
		return energy;
	}
	public static double getMinValue(double[] numbers){  
		double minValue = numbers[0];
		for(int i=0;i<numbers.length;i++){  
			if(numbers[i] < minValue){  
				minValue = numbers[i];
			}  
		}
		return minValue;  
	}
	
	public double[][] getEnergies (){
		double[][] array = new double[picture.width()][picture.height()];
		for (int i = 1; i < picture.width(); i++) {
			for (int j = 1; j < picture.height(); j++)  {
				array[i][j] = energy(i, j);
			}
		}
		return array;
	}

	

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		Picture pic = picture();
		int[][] seam;
		double[][] energies = getEnergies();
		int width = energies.length;
		int height = energies[0].length;
		double[][] seamcarver = new double[width][height];
		int[][] tracker = new int[width][height];
		double min;
		seam = new int[height][2];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if( y == 0) {
					seamcarver[x][y] = energies[x][y];
					tracker[x][y] = -1;
				} else {
					if(x == 0) {
						min = Math.min(seamcarver[x][y - 1], seamcarver[x + 1][y + 1]);
						if(min == seamcarver[x][y - 1]) {
							tracker[x][y] = 1;
						} else {
							tracker[x][y] = 2;
						}
					} else if (x == (width -1)) {
						min = Math.min(seamcarver[x][y - 1], seamcarver[x - 1][y - 1]);
						if(min == seamcarver[x][y - 1]) {
							tracker[x][y] = 1;
						} else {
							tracker[x][y] = 0;
						}
					} else {
						min = Math.min(seamcarver[x - 1][y - 1], Math.min(seamcarver[x][y - 1], seamcarver[x + 1][y - 1]));
						if (min == seamcarver[x - 1][y - 1]) {
							tracker[x][y] = 0;							
						} else if(min == seamcarver[x][y - 1]) {
							tracker[x][y] = 1;
						} else {
							tracker[x][y] = 2;
						}
					}
					seamcarver[x][y] = energies[x][y] + min;

				}
				
			}
			
		}
		double minimumnumber = seamcarver[width][height - 1];
		int minimunindex = 0;
		for (int x = 0; x < width; x++) {
			if (minimumnumber > seamcarver[x][height - 1]) {
				minimunindex = x;
				minimumnumber = seamcarver[x][height - 1];			
			}
		}
		int indexofy = height - 1;
		int indexofx = minimunindex;
		seam[indexofy][0] = indexofx;
		seam[indexofy][1] = indexofy;
		int backtrack;
		while (indexofy > 0) {
			backtrack = tracker[indexofx][indexofy];
			if (backtrack != -1) {
				if (backtrack == 0) {
					indexofx = indexofx - 1;
				} else if(backtrack == 1) {
					indexofx = indexofx;
				} else {
					indexofx = indexofx + 1;
				}
				
			} else {
				indexofx = indexofx;
			}
			indexofy = indexofy - 1;
		}
		return null;
	}

	// remove horizontal seam from current picture.
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}