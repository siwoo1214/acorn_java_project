package Tosstest;

import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Stock {
	private static final Random random = new Random();
	private static final Object fileLock = new Object(); // ✅ 동기화 객체

	private String stockName;
	private int price;

	public Stock(String stockName, int price) {
		this.stockName = stockName;
		this.price = price;
		startPriceUpdate();
	}

	// ✅ 10초마다 가격 변동 & 파일 저장 실행 (동일한 주기로 변경)
	private void startPriceUpdate() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updatePrice();
				saveToFile(); // ✅ 10초마다 가격 변동 후 파일 저장
			}
		}, 10000, 10000); // ✅ 10초마다 실행
	}

	// ✅ 가격 변동 (±10%)
	private void updatePrice() {
		double changeRate = (random.nextDouble() * 0.2) - 0.1;
		this.price = (int) Math.round(price * (1 + changeRate));
	}

	// ✅ 파일을 동기화하여 업데이트 (쓰기 충돌 방지)
	private void saveToFile() {
		synchronized (fileLock) { // ✅ 여러 스레드가 동시에 파일 수정 못하게 동기화
			File file = new File("res/stocks.txt");

			try (BufferedReader reader = new BufferedReader(new FileReader(file));
					BufferedWriter writer = new BufferedWriter(new FileWriter("res/stocks_temp.txt"))) {

				String line;
				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",");

					if (data.length < 2)
						continue; // ✅ 데이터 오류 방지

					if (data[0].equals(this.stockName)) {
						writer.write(this.stockName + "," + this.price + "\n");
					} else {
						writer.write(line + "\n"); 
					}
				}

				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// ✅ 파일 교체 (res/stocks_temp.txt → res/stocks.txt)
			File originalFile = new File("res/stocks.txt");
			File tempFile = new File("res/stocks_temp.txt");

			if (originalFile.exists())
				originalFile.delete();
			tempFile.renameTo(originalFile);
		}
	}

	public String getStockName() {
		return stockName;
	}

	public int getPrice() {
		return price;
	}
}
