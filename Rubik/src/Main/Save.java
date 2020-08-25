package Main;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Save {
	public File file;
	public int[] data;
	public Save(File file) {
		this.file = file;
	}
	
	public void read(int[] data) {
		try {
		Scanner scanner = new Scanner(file);
		String s = scanner.nextLine();
		
		for(int i=0;i<6*9;i++)
			{
			System.out.println(Integer.parseInt(""+s.charAt(i)));
			data[i] = Integer.parseInt(""+s.charAt(i));
			}
		
		scanner.close();
		}
		catch(FileNotFoundException e) {
			
		}
	}
	public void save(int[] data) {
		try {
			FileWriter write = new FileWriter(file);
			int red = Color.red.getRGB();
			int orange = Color.orange.getRGB();
			int yellow = Color.yellow.getRGB();
			int white = Color.white.getRGB();
			int green = Color.green.getRGB();
			int blue = Color.blue.getRGB();
			int e = 0;
			
			for(int  i=0;i<6*9;i++)
			{
				if(data[i] == red) {
					write.write('1');
				}
				else if(data[i] == orange) {
					write.write('2');
				}
				else if(data[i] == yellow) {
					write.write('3');
				}
				else if(data[i] == white) {
					write.write('4');
				}
				else if(data[i] == green) {
					write.write('5');
				}
				else if(data[i] == blue) {
					write.write('6');
				}
				
				
			}
			System.out.println("done save");
			write.close();
		}
		catch(IOException n) {
			System.out.println("error");
		}
	}

}
