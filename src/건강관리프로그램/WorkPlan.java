package 건강관리프로그램;

public class WorkPlan {
	String woName;
	String woType;
	int woDuration;
	int woSets;
	
	// 생성자
	
	public WorkPlan() {
		
	}
	
	public WorkPlan (String woName, String woType, int woDuration, int woSets) {
		this.woName = woName;
		this.woType = woType;
		this.woDuration = woDuration;
		this.woSets = woSets;
	}
	
	// getter매서드
	
	public String getWoName() {
		return woName;
	}

	public String getWoType() {
		return woType;
	}

	public int getWoDuration() {
		return woDuration;
	}
	
	public int getWoSets() {
		return woSets;
	}

	@Override
	public String toString() {
		return " 운동 : " + woName + "  | 종류 : " + woType + "   | 시간 / 횟수 : " + woDuration +" 분 / 회   "+ "   | 세트 = " + woSets;
	}
	
	
	
}
