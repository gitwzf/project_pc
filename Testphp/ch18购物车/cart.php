<?php
//购物车类cart.php
class Cart{
	//存放商品数据，二维数组
	var $cartitem;
	//商品数量
	var $count ;
	//用户
	var $owner;
	//时间
	var $time;
	//购物车编号
	var $cartid;
	
	//初始化结构函数
	function  Cart(){
		$this->count = 0;
		if(isset($userid)){
			$this->owner = $userid;
		}else {
			$this->owner = 0;
		}
		$this->time = date("Y-m-d");
		$this->cartid = time();
	}
	
	//添加商品
	function  add_item($productId){
		//设置标志
		$flag = false;
		for($i = 1 ; $i <= $this->count; $i ++){
			//检查在购物车理是否已经有相同的商品
			//如果有的话，设置标志为真
			//相应商品数量增加1
			if($this->cartitem[$i]["productid"] == $productId){
				$this->cartitem[$i]["quantity"] ++ ;
				$flag = true;
				break;
			}
		}
		//如果标志变量为假，说明在购物车中没有相同的商品
		//向购物车中加入该商品的相关信息
		if (!$flag) {
			$conn = mysql_connect("localhost","root", "");
			mysql_select_db("phpbook_ch18");
			$str = "select price,productname from product where productid=".$productId;
			$result = mysql_query($str);
			$res = mysql_fetch_object($result);
			$price = $res->price;
			$pname = $res->productname;
			mysql_close($conn);
			$this->count ++;
			//添加商品数据
			$this->cartitem[$this->count]["productid"] = $productId;
			$this->cartitem[$this->count]["quantity"] = 1;
			$this->cartitem[$this->count]["price"] = $price;
			$this->cartitem[$this->count]["productname"] = $pname;
			$flag = true;
		}
	}
	
	//删除购物车中的某一商品
	function  removeItem($productId){
		//设置标识变量
		$flag = false;
		for($i = 1 ; $i <= $this->count; $i ++){
			//循环寻找删除商品的ID.
			if ($this->cartitem[$i]["productid"] == $productId)
				$j = $i;
			$flag = true;
			break;
		}
		if ($flag) {
			//执行删除操作，实际上就是把被删除之后的每一项往前移动。
			for ($i = $j ; $i <=$this->count-1; $i ++){
				$this->cartitem[$i] = $this->cartitem[$i+1];
			}
			$this->count -- ;
		}
	}	
	//设定用户ID函数，其作用就是用于用户在购物后才登录的情况
	function setOwner($userid){
		$this->owner = $userid;
	}
	
	//显示购物车
	function showCart($userid){
		echo "<form method='post' action='showall.php'>
				<table border='1' width='500' cellspacing=0 cellpadding='0'>
					<tr align='center'>
						<td width='100' height='19'>商品名称</td>
						<td width='100' height='19'>数量</td>
						<td width='100' height='19'>商品价格</td>
						<td width='100' height='19'>商品总计</td>
						<td width='100' height='19'>删除</td>
					</tr>
					<tr align='center'>";
			$total = 0;
			for ($i = 1; $i <= $this->count; $i ++){
				echo "<td width='100'>".$this->cartitem[$i]["productname"]."</td>";
				echo "<td width='100'>
						<input type='text' name='a".$i."' size='7' maxlength='7'
						value='".$this->cartitem[$i]["quantity"]."'>
						</td>";
				echo "<td width='100'>".$this->cartitem[$i]["price"]."</td>";
				$sum = $this->cartitem[$i]["quantity"] * $this->cartitem[$i]["price"];
				$total += $sum;
				echo "<td width='100'>".$total."</td>";
				echo "<td width='100'><a href='showall.php?job=del&id="
						.$this->cartitem[$i]["productid"]."'>删除</a></td>";
				echo "</tr>";
			}
			echo "</table>";
			echo "<p>总金额：".$total."元。</p>";
			echo "<p><input type='hidden' name='job' value='update'>
					<input type='submit' name='submit' value='更改商品数目'>
					<a href='".$_SERVER['PHP_SELF']."'>刷新购物车</a>
					<a href='showproduct.php'>继续购物</a>
					<a href='check.php?total=".$total."&userid=".$userid."'>去结算中心</a>
					<a href='showall.php?job=clear'>清空购物车</a>
				</p>";
			echo "</form>";
	}	
	//更新购物车
	function updateCart($cartInfo){
		//这个变量用来统计要删除的商品的数目
		$delcount = 0 ;
		for ($i = 1; $i <= $this->count; $i ++){
			//改变商品的数量
			$this->cartitem[$i]["quantity"] = $cartInfo[$i];
			//如果数量为0的话，记录下来
			if($cartInfo[$i] == 0){
				$delcount ++;
				$del[$delcount] = $this->cartitem[$i]["productid"];
			}
		}		
		//删除数量为零的商品
		for($j = 1; $j <= $delcount ; $j ++){
			$this->removeItem($del[$j]);
		}
	}
}
?>