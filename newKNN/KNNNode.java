package newKNN;

/**
 * KNN结点类，用来存储最近邻的k个元组相关的信息
 */
public class KNNNode {
	private int index; // 元组标号
	private double distance; // 与测试元组的距离
	private String seq;//滑动窗口motif序列片段
	
	public KNNNode(int index, double distance, String seq) {
		super();
		this.index = index;
		this.distance = distance;
		this.seq = seq;
		
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
}
