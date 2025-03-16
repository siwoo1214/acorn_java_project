package Tosstest;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class StockManager {
	private static final Random random = new Random();
	private static final String FILE_PATH = "res/stocks.txt";

	private static final ArrayList<String> stockNames = new ArrayList<>();
	private static final ArrayList<Integer> stockPrices = new ArrayList<>();

	// ✅ 프로그램 시작 시 저장된 데이터 불러오기
	static {
		loadStocksFromFile();
		checkAndAddMissingStocks(); // ✅ 부족한 주식 자동 추가
		saveStocksToFile(); // ✅ 항상 20개를 맞춘 후 파일 저장
		startPriceUpdate(); // ✅ 가격 변동 시작
	}

	// ✅ 30초마다 가격 변동 & 즉시 파일 저장
	private static void startPriceUpdate() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updatePrices();
			}
		}, 30000, 30000);
	}

	// ✅ 가격 변동 (±10%) + 즉시 파일 저장
	private static void updatePrices() {
		for (int i = 0; i < stockPrices.size(); i++) {
			double changeRate = (random.nextDouble() * 0.2) - 0.1; // -10% ~ +10%
			int newPrice = (int) Math.round(stockPrices.get(i) * (1 + changeRate));
			stockPrices.set(i, newPrice);
		}
		saveStocksToFile(); // ✅ 변동된 가격을 즉시 파일에 저장
	}

	// ✅ 파일에서 주식 정보 불러오기 (파일이 깨져도 기본값 유지)
	private static void loadStocksFromFile() {
		stockNames.clear();
		stockPrices.clear();

		File file = new File(FILE_PATH);

		// ✅ 파일이 없으면 기본 주식 생성 후 저장
		if (!file.exists()) {
			createDefaultStocks();
			saveStocksToFile();
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length < 2)
					continue; // 데이터 오류 방지

				stockNames.add(data[0]);
				stockPrices.add(Integer.parseInt(data[1]));
			}
		} catch (IOException e) {
			System.out.println("❌ 파일 읽기 중 오류 발생! 기본 주식 데이터 사용");
			createDefaultStocks(); // ✅ 오류 발생 시 기본값으로 복원
		}
	}

	// ✅ 부족한 주식 자동 추가
	private static void checkAndAddMissingStocks() {
		String[] allStockNames = { "삼성전자", "카카오", "LG화학", "네이버", "현대차", "SK하이닉스", "POSCO", "삼성SDI", "셀트리온", "신한지주",
				"KB금융", "하나금융지주", "우리금융지주", "대한항공", "아시아나항공", "CJ제일제당", "롯데쇼핑", "LG전자", "삼성물산", "현대모비스" };
		int[] defaultPrices = { 70000, 90000, 850000, 120000, 190000, 110000, 300000, 600000, 150000, 40000, 48000,
				39000, 13000, 27000, 15000, 320000, 98000, 130000, 120000, 230000 };

		boolean modified = false;
		for (int i = 0; i < allStockNames.length; i++) {
			if (!stockNames.contains(allStockNames[i])) {
				stockNames.add(allStockNames[i]);
				stockPrices.add(defaultPrices[i]);
				modified = true;
			}
		}

		if (modified) {
			saveStocksToFile(); // ✅ 부족한 주식 추가 후 저장
		}
	}

	// ✅ 기본 주식 데이터 생성 (처음 실행할 때)
	private static void createDefaultStocks() {
		stockNames.clear();
		stockPrices.clear();

		checkAndAddMissingStocks(); // ✅ 20개를 자동 추가하도록 변경
	}

	// ✅ 파일에 주식 데이터 저장
	private static void saveStocksToFile() {
		checkAndAddMissingStocks(); // ✅ 항상 20개를 맞춘 후 저장

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (int i = 0; i < stockNames.size(); i++) {
				writer.write(stockNames.get(i) + "," + stockPrices.get(i));
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ✅ 주식 이름 가져오기
	public static ArrayList<String> getStockNames() {
		return stockNames;
	}

	// ✅ 주식 가격 가져오기
	public static ArrayList<Integer> getStockPrices() {
		return stockPrices;
	}
}
