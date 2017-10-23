package newKNN;

public class testKNN {

	public static void main(String[] args) {

		testKNN t = new testKNN();
		NewKnn kk = new NewKnn();
		// kk.getDistance("HSSGFRDFLLKPELLRAIVDC","KIKVIGNCDAKDFPIKYKERH");
		String path_all = "E:\\experiment_variants\\0species\\Homo sapiens\\feature\\test\\knn\\";

		try {
			// BufferedReader brt = new BufferedReader(new FileReader(new
			// File(path_all+"test.txt")));
			String path_t = path_all + "test1.txt";
			String path_p = path_all + "train_p.txt";
			String path_n = path_all + "train_n.txt";
			int num_p = 5;
			int num_n = 6;
			kk.knn(path_t, path_p, num_p);
			kk.knn(path_t, path_n, num_n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
