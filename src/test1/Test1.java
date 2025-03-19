package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) {
		try (BufferedWriter bos = new BufferedWriter(new FileWriter("ras/prac.txt"));) {
			bos.write("박시우,171,60");
			bos.newLine();
			bos.write("홍길동,182,76");
			bos.newLine();
			bos.write("최완빈,178,62");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader("ras/prac.txt"))) {
			String data;
			while ((data = br.readLine()) != null) {

				String[] datas = data.split(",");
				System.out.printf("%s님의 키는 %s센치미터이고 몸무게는 %s 입니다\n", datas[0], datas[1], datas[2]);
				System.out.println(datas[0] + datas[1] + datas[2]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
