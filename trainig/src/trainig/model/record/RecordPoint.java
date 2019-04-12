package trainig.model.record;

public class RecordPoint {
	private Integer point;

	public RecordPoint(int point) {
		this.point = point;
	}

	public Integer getPoint() {
		return point;
	}

	public boolean isEmpty() {
		if(point == null) {
			return true;
		}
		return false;
	}
//成績評価点の確認
	public static boolean rangeValidation(int point) {
		if(point < 1) {
			return false;
		}
		if(point > 100) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return String.format("RecordPoint [recordPoint=%s]", point);
	}


}
