package Main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * ģ��´�С��Ϸ�ó����ۣ�û�б�Ӯ����
 * @author WZF
 *https://my.oschina.net/u/1432859/blog/913866
 */
public class BetCalculateor {// ģ��Ͷ�� ����Ӯ�ʣ�10���� 12��-1�� �ݺ���̫������ ˭�ļҲ����ֲ���ָ������
// 12 1 20.0 =100044.0
// 13 -1 10.0 =100034.0
// 14 -1 20.0 =100014.0
// 15 -1 40.0 =99974.0
// 16 -1 90.0 =99884.0
// 17 -1 200.0 =99684.0
// 18 -1 450.0 =99234.0
// 19 -1 1020.0 =98214.0
// 20 -1 2290.0 =95924.0
// 21 -1 5150.0 =90774.0
// 22 -1 11590.0 =79184.0
// 23 -1 26080.0 =53104.0
// 24 -1 53104.0 =0.0
	public static void main(String[] args) {
		win(1d, 0.8, 8, 10);
		// winorlose(1000d,1d,0.8,1.5);
		// batchwinorlose(100d,1d,0.7,1.1);
		// BigDecimal big=BigDecimal.valueOf(Math.pow(0.5, 10));
		// System.out.println(big);
	}

	public static void batchwinorlose(Double allMoney, Double initMoney,
			Double zhekou, double x) {
		// ��ͨ�ӱ�Ͷ��Ӯ10%��9��1 Ӯ50%��6��4 ���ӽ�7��3��
		// ����Ӯ�ӱ���Ͷ��Ӯ10%��9��1 Ӯ50%��6��4���ӽ�5��5��
		// ����0.8 Ӯ10%��8��2 ���ӽ�7��3�� Ӯ50%��3��7 ���ӽ�4��6��
		// ����0.7 Ӯ10%��7��3 ���ӽ�6��4�� Ӯ50%��2��8 ���ӽ�3��7��
		int winNum = 0;
		int loseNum = 0;
		for (int i = 0; i < 100; i++) {
			boolean end = winorlose(allMoney, initMoney, zhekou, x);
			if (end) {
				winNum++;
			} else {
				loseNum++;
			}
		}
		System.out.println(String.format("�ܶ�[%f] ���Ͷ��[%f] ����[%f] �ƻ�Ӯ�ı���[%f]",
				100d * 10, 1d * 10, 0.8, x));
		System.out.println(String.format("��[%d]��   Ӯ��[%d]  �䣺[%d]", 100,
				winNum, loseNum));
	}

	/**
	 * @description : ÿ������Ϸ�Ľ��
	 * @param allMoney
	 *            ���ϴ����Խ��
	 * @param initMoney
	 *            ����Ͷ��Ķ��
	 * @param zhekou
	 *            ����
	 * @param x
	 *            ��ҪӮ�ı���
	 * @return true Ӯ false �� boolean
	 */
	public static boolean winorlose(Double allMoney, Double initMoney,
			Double zhekou, double x) {
		double money = allMoney;
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		list.add(new BigDecimal(initMoney));
		String str = String.format("�ܽ��[%s] ��Ͷ���[%s]\r\n", allMoney, initMoney);
		int i = 0;
		int betType = -1;
		while (allMoney >= initMoney && allMoney < x * money) {
			i++;
			int type = (Math.random() > 0.5 ? 1 : -1);
			int addordel = (type == betType ? 1 : -1);
			double nmoney = list.get(list.size() - 1).doubleValue();
			double nextMoney = nmoney > allMoney ? allMoney : nmoney;
			if (addordel < 0) {
				allMoney = allMoney - (nextMoney);
			} else {
				allMoney = allMoney + (nextMoney) * zhekou;
			}
			str += i + " " + addordel + "  " + nextMoney + "  =" + allMoney
					+ "\r\n";
			if (type < 0) {
				BigDecimal bigDecimal = getNextDecimal(list, BigDecimal
						.valueOf(zhekou));
				list.add(bigDecimal);
			} else if (type > 0) {
				betType = -1 * betType;
				list.clear();
				list.add(new BigDecimal(initMoney));
			}
		}
		if (allMoney < x * money) {
			// System.out.println(str);
		}

		return allMoney >= x * money;

	}

	/**
	 * @description :����涨���ʵı�Ӯ��Ͷ��������
	 * @param initMoney
	 *            ��Ͷ���
	 * @param zhekou
	 *            ����
	 * @param num
	 *            �ڼ���Ӯ���ٶ���
	 * @param size
	 *            ��ʵ�����ı��� void
	 */
	private static void win(Double initMoney, Double zhekou, int num, int size) {
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		list.add(new BigDecimal(initMoney));
		getSeqList(list, new BigDecimal(zhekou), num);
		for (BigDecimal bigDecimal : list) {
			System.out.print(bigDecimal.multiply(new BigDecimal(size))
					.doubleValue()
					+ "  ");
		}
	}

	/**
	 * @description : �õ�����Ӯ����Ͷ��������
	 * @param list
	 *            �������
	 * @param zhekou
	 *            ����
	 * @param num
	 *            ���г��� void
	 */
	public static void getSeqList(List<BigDecimal> list, BigDecimal zhekou,
			int num) {
		if (num == 0)
			return;
		BigDecimal bigDecimal = getNextDecimal(list, zhekou);
		list.add(bigDecimal);
		getSeqList(list, zhekou, --num);
	}

	/**
	 * @description : �����һ���ܷ�����Ͷ����
	 * @param list
	 *            ����
	 * @param zhekou
	 *            ����
	 * @return BigDecimal
	 */
	private static BigDecimal getNextDecimal(List<BigDecimal> list,
			BigDecimal zhekou) {
		BigDecimal bigDecimal = new BigDecimal(0);
		for (BigDecimal decimal : list) {
			bigDecimal = bigDecimal.add(decimal);
		}
		return bigDecimal.divide(zhekou, BigDecimal.ROUND_UP);
	}

}
