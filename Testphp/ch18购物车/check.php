<?php
//结算的程序,check.php
include_once("cart.php");
session_start();
//echo "userid=".$_SESSION["userid"];
// .....
//输出页面开头,省略
//如果用户已经登录
if (isset($userid)) 
{
	//获取用户的信息
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	$query = "select * from user where userid=".$userid;
	$result = mysql_query($query);
	$res = mysql_fetch_object($result);
	mysql_close($conn);	
	//显示表单的开头
	echo "<form method='post' action='finish.php'>
			<table width='500' border='0' cellspacing='0' cellpadding='0'>
		";
	//显示用户名称
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>".$res->username.",你好.</font></p>
				<p>&nbsp;</p>
			</td>
		  </tr>
		";
	//输出用户选择购买的商品的总金额
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>您的购物总金额为".$total."元.</font></p>
				<p>&nbsp;</p>
			</td>
		  </tr>";
	//输出用户的地址
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>您的住址为
				<input type='text' name='addr' size='50' value='".$res->useraddr."'></font></p>
				</td>
		  </tr>";
	//输出用户的电话
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>您的电话为
				<input type='text' name='tel' size='16' maxlength='16' value='".$res->usertel."'></font></p>
				</td>
		  </tr>";
	//输出送货方式
	echo "<tr bgcolor='#ccccff'>
			<td>
				<font size='2'>本站现在只提供货到付款服务,请大家给于谅解.
				</font><input type='hidden' name='total' value='".$total."'>
				</td>
		  </tr>";
	//输出相应的按钮
	echo "<tr bgcolor='#ccccff'>
			<td>";
	echo "<input type='submit' name='submit' value='完成购物'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	//取消订单的按钮
	echo "<a href='showall.php?job=clear'>取消订单</a>
			</font></td>
		</tr>
		</table>
		</form>";
}

//如果用户没有登录
else 
{
	//如果没有$prepage这个变量的话,则首先注册为session变量
	if (!isset($prepage)) {
		session_register("prepage");
	}
	$prepage = "check.php";
	
	//开始输出页面
	//给用户一个选择,可以先登录,也可以直接填写下面的表格
	echo "<form method='post' action='login.php' name='1'>
			<p><font size='2'>如果您是注册用户,可以选择登录,也可以直接开始填写下面的内容</font></p>
			<p><font size='2'>用户名
			<input type='text' name='username' size='12' maxlength='12'>
			</font></p>
			<p><font size='2'>用户密码
			<input type='password' name='password' size='12' maxlength='12'>
			</font></p>
			<p>
				<input type='submit' name='submit' value='登录'>
			</p>
			</form>";
	//输出第二个表单
	echo "<form method='post' action='finish.php' name='2'>
			<table width='500' border='0' cellspacing='0' cellpadding='0'>
				<tr bgcolor='#ccccff'>
					<td>
						<p><font size='2'>你好,</font></p>
						<p>&nbsp;</p>
					</td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td>
						<p><font size='2'>你的购物总金额为 ".$total."元</font></p>
						<p>&nbsp;</p>
					</td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>你的住址为
						<input type='text' name='addr' size='50'>
					</font></td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>你的电话为
						<input type='text' name='tel' size='16' maxlength='16'>
					</font></td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>本站现在只提供货到付款服务,请大家谅解.
					</font><input type='hidden' name='total' value='".$total."'></td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>
						<input type='submit' name='submit2' value='完成购物'>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href='showall.php?job=clear'>取消订单</a>
						</font>
					</td>	
				</tr>
			</table>
		</form>";	
}
//输出页面结尾部分,此处略
//include_once("footer.php");
?>