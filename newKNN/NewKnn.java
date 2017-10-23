package newKNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewKnn {

	/*
	 * 计算序列距离
	 * 
	 * @param d1 测试集合一条数据
	 * 
	 * @param d2 训练集合一条数据
	 * 
	 * @return （double）距离值
	 */
	public double getDistance(String str1, String str2) {
		double[][] blosum = new double[21][21];
		double distance = 0.0;
		String path = "E:\\experiment_variants\\0species\\Homo sapiens\\feature\\test\\knn\\blosum.txt";
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			int count = -1;
			int N = 21;// 窗口长度
			// 读取blosum62矩阵
			while ((str = br.readLine()) != null) {
				count++;
				for (int i = 0; i < 21; i++) {
					String[] temp;
					temp = str.split("	");
					blosum[count][i] = Integer.parseInt(temp[i]);
				}
			}
			String amino = "CSTPAGNDEQHRKMILVFYW*";
			// System.out.println(amino.indexOf("C"));--> 0
			double sim = 0.0;
			int min = -4;
			int max = 11;
			char one, two;// 两个氨基酸
			int o1, t2;// 两个氨基酸对应到string amino上的位置
			// 计算str1，str2的距离
			for (int i = 0; i < 21; i++) {// 滑动窗口的长度21
				one = str1.charAt(i);
				two = str2.charAt(i);
				o1 = amino.indexOf(one);
				t2 = amino.indexOf(two);
				sim += (blosum[o1][t2] - min) / (max - blosum[o1][t2]);
				// System.out.println(sim);
			}
			distance = 1 - (sim / N);
			 System.out.println("cal distance ing"+distance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return distance;

	}

	/**
	 * 对结点数组输出
	 * 
	 * @param nodelist
	 *            结点数组
	 */
	public void display(List<KNNNode> nodelist) {
		for (int i = 0; i < nodelist.size(); i++) {
			System.out.println("index:" + nodelist.get(i).getIndex());
			System.out.println("distance:" + nodelist.get(i).getDistance());
			System.out.println("seq:" + nodelist.get(i).getSeq());
		}

	}

	/**
	 * 根据distace进行排序 从小到大
	 */
	Comparator<KNNNode> comparator = new Comparator<KNNNode>() {

		@Override
		public int compare(KNNNode o1, KNNNode o2) {
			// TODO Auto-generated method stub
			if (o1.getDistance() >= o2.getDistance()) {
				return 1;
			} else {
				return -1;
			}
		}

	};

	/**
	 * knn算法 调用计算距离函数、对距离排序函数、
	 * 
	 * @param path1
	 *            测试集路径
	 * @param path2
	 *            训练集路径
	 * @param num
	 *            邻居数量
	 */
	public int[] knn(String path1, String path2, int num) {
		NewKnn k = new NewKnn();
		double distance = 0.0;
		List<KNNNode> nodelist = new ArrayList<KNNNode>();
		//存测试样本  num个邻居 的index
		int[] count= new int[num];
		try {
			FileReader fr = new FileReader(path1);
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			// 读test文件 循环查询
			while ((str = br.readLine()) != null) {
				FileReader frp = new FileReader(path2);
				BufferedReader brp = new BufferedReader(frp);
				String strp = null;
				int countp = 0;
				// 读train 没读一行test 都要读一遍train
				while ((strp = brp.readLine()) != null) {
					countp++;
					distance = k.getDistance(str, strp);
					// System.out.println("读取时" + distance);
					KNNNode node = new KNNNode(countp, distance, strp);
					nodelist.add(node);

				}
				// 对 nodelist内结点按距离排序,这里就会自动根据规则进行排序
				Collections.sort(nodelist, comparator);// 调用
				// display(nodelist);
				
				for( int i=0;i<num;i++){
					count[i]=nodelist.get(i).getIndex();
					System.out.println(count[i]);
				}
				

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

}
