<?php
//���ﳵ��cart.php
class Cart{
	//�����Ʒ���ݣ���ά����
	var $cartitem;
	//��Ʒ����
	var $count ;
	//�û�
	var $owner;
	//ʱ��
	var $time;
	//���ﳵ���
	var $cartid;
	
	//��ʼ���ṹ����
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
	
	//�����Ʒ
	function  add_item($productId){
		//���ñ�־
		$flag = false;
		for($i = 1 ; $i <= $this->count; $i ++){
			//����ڹ��ﳵ���Ƿ��Ѿ�����ͬ����Ʒ
			//����еĻ������ñ�־Ϊ��
			//��Ӧ��Ʒ��������1
			if($this->cartitem[$i]["productid"] == $productId){
				$this->cartitem[$i]["quantity"] ++ ;
				$flag = true;
				break;
			}
		}
		//�����־����Ϊ�٣�˵���ڹ��ﳵ��û����ͬ����Ʒ
		//���ﳵ�м������Ʒ�������Ϣ
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
			//�����Ʒ����
			$this->cartitem[$this->count]["productid"] = $productId;
			$this->cartitem[$this->count]["quantity"] = 1;
			$this->cartitem[$this->count]["price"] = $price;
			$this->cartitem[$this->count]["productname"] = $pname;
			$flag = true;
		}
	}
	
	//ɾ�����ﳵ�е�ĳһ��Ʒ
	function  removeItem($productId){
		//���ñ�ʶ����
		$flag = false;
		for($i = 1 ; $i <= $this->count; $i ++){
			//ѭ��Ѱ��ɾ����Ʒ��ID.
			if ($this->cartitem[$i]["productid"] == $productId)
				$j = $i;
			$flag = true;
			break;
		}
		if ($flag) {
			//ִ��ɾ��������ʵ���Ͼ��ǰѱ�ɾ��֮���ÿһ����ǰ�ƶ���
			for ($i = $j ; $i <=$this->count-1; $i ++){
				$this->cartitem[$i] = $this->cartitem[$i+1];
			}
			$this->count -- ;
		}
	}	
	//�趨�û�ID�����������þ��������û��ڹ����ŵ�¼�����
	function setOwner($userid){
		$this->owner = $userid;
	}
	
	//��ʾ���ﳵ
	function showCart($userid){
		echo "<form method='post' action='showall.php'>
				<table border='1' width='500' cellspacing=0 cellpadding='0'>
					<tr align='center'>
						<td width='100' height='19'>��Ʒ����</td>
						<td width='100' height='19'>����</td>
						<td width='100' height='19'>��Ʒ�۸�</td>
						<td width='100' height='19'>��Ʒ�ܼ�</td>
						<td width='100' height='19'>ɾ��</td>
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
						.$this->cartitem[$i]["productid"]."'>ɾ��</a></td>";
				echo "</tr>";
			}
			echo "</table>";
			echo "<p>�ܽ�".$total."Ԫ��</p>";
			echo "<p><input type='hidden' name='job' value='update'>
					<input type='submit' name='submit' value='������Ʒ��Ŀ'>
					<a href='".$_SERVER['PHP_SELF']."'>ˢ�¹��ﳵ</a>
					<a href='showproduct.php'>��������</a>
					<a href='check.php?total=".$total."&userid=".$userid."'>ȥ��������</a>
					<a href='showall.php?job=clear'>��չ��ﳵ</a>
				</p>";
			echo "</form>";
	}	
	//���¹��ﳵ
	function updateCart($cartInfo){
		//�����������ͳ��Ҫɾ������Ʒ����Ŀ
		$delcount = 0 ;
		for ($i = 1; $i <= $this->count; $i ++){
			//�ı���Ʒ������
			$this->cartitem[$i]["quantity"] = $cartInfo[$i];
			//�������Ϊ0�Ļ�����¼����
			if($cartInfo[$i] == 0){
				$delcount ++;
				$del[$delcount] = $this->cartitem[$i]["productid"];
			}
		}		
		//ɾ������Ϊ�����Ʒ
		for($j = 1; $j <= $delcount ; $j ++){
			$this->removeItem($del[$j]);
		}
	}
}
?>