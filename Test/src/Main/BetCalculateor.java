package Main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * 模拟猜大小游戏得出结论：没有必赢方法
 * @author WZF
 *https://my.oschina.net/u/1432859/blog/913866
 */
public class BetCalculateor {// 模拟投标 计算赢率（10倍） 12次-1！ 幂函数太可怕了 谁的家产都抵不过指数增长
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
		// 普通加倍投：赢10%是9：1 赢50%是6：4 （接近7：3）
		// 按输赢加倍换投：赢10%是9：1 赢50%是6：4（接近5：5）
		// 赔率0.8 赢10%是8：2 （接近7：3） 赢50%是3：7 （接近4：6）
		// 赔率0.7 赢10%是7：3 （接近6：4） 赢50%是2：8 （接近3：7）
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
		System.out.println(String.format("总额[%f] 最低投额[%f] 赔率[%f] 计划赢的倍数[%f]",
				100d * 10, 1d * 10, 0.8, x));
		System.out.println(String.format("赌[%d]次   赢：[%d]  输：[%d]", 100,
				winNum, loseNum));
	}

	/**
	 * @description : 每次玩游戏的结果
	 * @param allMoney
	 *            身上带的淘金币
	 * @param initMoney
	 *            最少投入的额度
	 * @param zhekou
	 *            赔率
	 * @param x
	 *            想要赢的倍数
	 * @return true 赢 false 输 boolean
	 */
	public static boolean winorlose(Double allMoney, Double initMoney,
			Double zhekou, double x) {
		double money = allMoney;
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		list.add(new BigDecimal(initMoney));
		String str = String.format("总金额[%s] 初投金额[%s]\r\n", allMoney, initMoney);
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
	 * @description :计算规定赔率的保赢的投标额度序列
	 * @param initMoney
	 *            起投额度
	 * @param zhekou
	 *            赔率
	 * @param num
	 *            第几次赢（假定）
	 * @param size
	 *            与实际相差的倍数 void
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
	 * @description : 得到“必赢”的投入额度序列
	 * @param list
	 *            额度序列
	 * @param zhekou
	 *            赔率
	 * @param num
	 *            序列长度 void
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
	 * @description : 获得下一个能翻本的投入额度
	 * @param list
	 *            序列
	 * @param zhekou
	 *            赔率
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
