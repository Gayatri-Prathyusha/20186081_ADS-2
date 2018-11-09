import java.awt.Color;
import java.awt.image.BufferedImage;
public class SeamCarver {
	private Picture picture;
    private double[] energy; 
    private int[] pathTo;
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
	
	    private double gradient(java.awt.Color x, java.awt.Color y) { 
        double r = x.getRed() - y.getRed(); 
        double g = x.getGreen() - y.getGreen(); 
        double b = x.getBlue() - y.getBlue(); 
        return r*r + g*g + b*b; 
    } 
  
    private double energy(int x, int y, int flag) { 
        if (flag == 1) 
            return energy(y, x); 
        else 
            return energy(x, y); 
    } 
 
    private void getEnergies(int w, int h, int flag) { 
        //double maxE = 0; 
        energy = new double[w*h]; 
        for (int r = 0; r < h; r++) 
            for (int c = 0; c < w; c++) { 
                energy[r*w + c] = energy(c, r, flag); 
            } 
    } 
 
    private int[] computePath(int w, int h) { 
        pathTo = new int[w*h]; 
        for (int i = 0; i < w; i++) 
            pathTo[i] = -1; 
        for (int r = 1, i = w; r < h; r++) { 
            if (energy[i-w] <= energy[i-w+1]) pathTo[i] = i-w; 
            else pathTo[i] = i-w+1; 
            energy[i] += energy[pathTo[i]]; i++; 
            for (int c = 1; c < w-1; c++, i++) { 
                if (energy[i-w-1] <= energy[i-w]) { 
                    if (energy[i-w-1] <= energy[i-w+1]) pathTo[i] = i-w-1; 
                    else pathTo[i] = i-w+1; 
                } else { 
                    if (energy[i-w] <= energy[i-w+1]) pathTo[i] = i-w; 
                    else pathTo[i] = i-w+1; 
                } 
                energy[i] += energy[pathTo[i]]; 
            } 
            if (energy[i-w-1] <= energy[i-w]) pathTo[i] = i-w-1; 
            else pathTo[i] = i-w; 
            energy[i] += energy[pathTo[i]]; i++; 
        } 
 
        int pathEnd = w*(h-1); 
        double minE = energy[w*(h-1)]; 
        for (int i = w*(h-1); i < w*h; i++) { 
            if (minE > energy[i]) { 
                minE = energy[i]; 
                pathEnd = i; 
            } 
        } 
 
        int[] path = new int[h]; 
        for (int p = pathEnd; p >= 0; p = pathTo[p]) 
            path[p/w] = p % w;
            path[0] = path[1];
        return path; 
    } 
 
    // sequence of indices for horizontal seam 
    public   int[] findHorizontalSeam() { 
        int w = height(), h = width(); 
        getEnergies(w, h, 1); 
        return computePath(w, h); 
    } 
 
    // sequence of indices for vertical seam 
    public   int[] findVerticalSeam() { 
        int w = width(), h = height(); 
        getEnergies(w, h, 0); 
        return computePath(w, h); 
    } 
 
    // remove horizontal seam from picture 
    public    void removeHorizontalSeam(int[] a) { 
        if (height() <= 1) 
            throw new IllegalArgumentException(); 
        if (a.length != width()) 
            throw new IllegalArgumentException("Wrong Length"); 
 
        Picture p = new Picture(width(), height()-1); 
        //Picture seamPic = new Picture(pic); 
        int prerow = a[0]; 
        for (int c = 0; c < width(); c++) { 
            if (Math.abs(a[c] - prerow) > 1) 
                throw new IllegalArgumentException("Non-valid seam"); 
            if (a[c] < 0 || a[c] >= height()) 
                throw new IndexOutOfBoundsException(); 
            //seamPic.set(c, a[c], java.awt.Color.red); 
            prerow = a[c]; 
            for (int r = 0; r < height()-1; r++) 
                if (r < a[c]) 
                    p.set(c, r, picture.get(c, r)); 
                else 
                    p.set(c, r, picture.get(c, r+1)); 
        } 
        picture = p; 
        energy = null; 
        pathTo = null; 
        //seamPic.show(); 
    } 
 
    // remove vertical seam from picture 
    public    void removeVerticalSeam(int[] a) { 
        if (width() <= 1) 
            throw new IllegalArgumentException(); 
        if (a.length != height()) 
            throw new IllegalArgumentException("Wrong Length"); 
 
        Picture p = new Picture(width()-1, height()); 
        //Picture seamPic = new Picture(pic); 
        int precol = a[0]; 
        for (int r = 0; r < height(); r++) { 
            if (Math.abs(a[r] - precol) > 1) 
                throw new IllegalArgumentException("Non-valid seam"); 
            if (a[r] < 0 || a[r] >= width()) 
                throw new IndexOutOfBoundsException(); 
            //seamPic.set(a[r], r, java.awt.Color.red); 
            precol = a[r]; 
            for (int c = 0; c < width()-1; c++) 
                if (c < a[r]) 
                    p.set(c, r, picture.get(c, r)); 
                else 
                    p.set(c, r, picture.get(c+1, r)); 
        } 
        picture = p; 
        energy = null; 
        pathTo = null; 
    }
}