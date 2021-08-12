package workspace;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		
		
		String cmd, index_file;
		cmd = args[0];
		index_file = args[1];
		
		if(cmd.equals("-c")) // Data creation. program -c index_file m
		{
			//args[2] -> degree;
			PrintWriter outputStream;
			try {
				outputStream = new PrintWriter(new FileOutputStream(index_file));
				outputStream.println(args[2]);
				outputStream.close(); 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(cmd.equals("-i"))// program -i index_file data_file
		{
			//args[2] -> data_file.
			String data_file = args[2];
			Scanner inputStream = null;
			Scanner indexFileInputStream = null;
			
			try {
				indexFileInputStream = new Scanner(new FileInputStream(index_file));
				inputStream = new Scanner(new FileInputStream(data_file)); // datafile 연것.
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String m = indexFileInputStream.nextLine();
			Bplustree bplus = new Bplustree(Integer.parseInt(m)); // m차 bplustree 생성.
			
			dataInsert(bplus, indexFileInputStream);
			dataInsert(bplus, inputStream);
			
			bplus.saveBplustree(index_file); // index_file을 저장.
			
			indexFileInputStream.close();
			inputStream.close();
			
		}
		else if(cmd.equals("-d")) // program -d index_file data_file
		{
			Scanner inputStream = null;
			Scanner indexFileInputStream = null;
			String data_file = args[2];
			
			try {
				indexFileInputStream = new Scanner(new FileInputStream(index_file));
				inputStream = new Scanner(new FileInputStream(data_file));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			String m = indexFileInputStream.nextLine();
			
			Bplustree bplus = new Bplustree(Integer.parseInt(m));
			
			dataInsert(bplus, indexFileInputStream);
			String line = null;
			
//			bplus.print();
			
			while(inputStream.hasNextLine()) 
			{
				line = inputStream.nextLine();
				bplus.delete(Integer.parseInt(line));
				
//				System.out.println();
//				System.out.println("After" + line);
//				bplus.print();
			}
			
			bplus.saveBplustree(index_file); // index_file에 저장.
			
			
			indexFileInputStream.close();
			inputStream.close();
		}
		else if(cmd.equals("-s")) // program -s index_file key
		{
			Scanner indexFileInputStream = null;
			String key = args[2];
			
			try {
				indexFileInputStream = new Scanner(new FileInputStream(index_file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String m = indexFileInputStream.nextLine();
			Bplustree bplus = new Bplustree(Integer.parseInt(m)); // m차 bplustree 생성.
			dataInsert(bplus, indexFileInputStream);

			bplus.searchKey(Integer.parseInt(key));			
			indexFileInputStream.close();
		}
		else if(cmd.equals("-r")) // program -r index_file start_key end_key.
		{
			Scanner indexFileInputStream = null;
			String start = args[2];
			String end = args[3];
			
			try {
				indexFileInputStream = new Scanner(new FileInputStream(index_file));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String m = indexFileInputStream.nextLine();
			
			Bplustree bplus = new Bplustree(Integer.parseInt(m));
			
			dataInsert(bplus, indexFileInputStream);

			bplus.rangedSearch(Integer.parseInt(start), Integer.parseInt(end));
			
			
			indexFileInputStream.close();
		}
	}
	
	public static void dataInsert(Bplustree b,Scanner inputStream)
	{
		String line = null;
		
		while(inputStream.hasNextLine())
		{
			line = inputStream.nextLine();
			String[] data = line.split(",") ;
			
			int key = Integer.parseInt(data[0]);
			int value = Integer.parseInt(data[1]);
			b.insert(key, value); //data[0] ->key data[1] -> value; 
		}
	}
}
