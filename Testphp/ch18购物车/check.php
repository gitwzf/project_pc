<?php
//����ĳ���,check.php
include_once("cart.php");
session_start();
//echo "userid=".$_SESSION["userid"];
// .....
//���ҳ�濪ͷ,ʡ��
//����û��Ѿ���¼
if (isset($userid)) 
{
	//��ȡ�û�����Ϣ
	$conn = mysql_connect("localhost", "root", "");
	mysql_select_db("phpbook_ch18");
	$query = "select * from user where userid=".$userid;
	$result = mysql_query($query);
	$res = mysql_fetch_object($result);
	mysql_close($conn);	
	//��ʾ���Ŀ�ͷ
	echo "<form method='post' action='finish.php'>
			<table width='500' border='0' cellspacing='0' cellpadding='0'>
		";
	//��ʾ�û�����
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>".$res->username.",���.</font></p>
				<p>&nbsp;</p>
			</td>
		  </tr>
		";
	//����û�ѡ�������Ʒ���ܽ��
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>���Ĺ����ܽ��Ϊ".$total."Ԫ.</font></p>
				<p>&nbsp;</p>
			</td>
		  </tr>";
	//����û��ĵ�ַ
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>����סַΪ
				<input type='text' name='addr' size='50' value='".$res->useraddr."'></font></p>
				</td>
		  </tr>";
	//����û��ĵ绰
	echo "<tr bgcolor='#ccccff'>
			<td>
				<p><font size='2'>���ĵ绰Ϊ
				<input type='text' name='tel' size='16' maxlength='16' value='".$res->usertel."'></font></p>
				</td>
		  </tr>";
	//����ͻ���ʽ
	echo "<tr bgcolor='#ccccff'>
			<td>
				<font size='2'>��վ����ֻ�ṩ�����������,���Ҹ����½�.
				</font><input type='hidden' name='total' value='".$total."'>
				</td>
		  </tr>";
	//�����Ӧ�İ�ť
	echo "<tr bgcolor='#ccccff'>
			<td>";
	echo "<input type='submit' name='submit' value='��ɹ���'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	//ȡ�������İ�ť
	echo "<a href='showall.php?job=clear'>ȡ������</a>
			</font></td>
		</tr>
		</table>
		</form>";
}

//����û�û�е�¼
else 
{
	//���û��$prepage��������Ļ�,������ע��Ϊsession����
	if (!isset($prepage)) {
		session_register("prepage");
	}
	$prepage = "check.php";
	
	//��ʼ���ҳ��
	//���û�һ��ѡ��,�����ȵ�¼,Ҳ����ֱ����д����ı��
	echo "<form method='post' action='login.php' name='1'>
			<p><font size='2'>�������ע���û�,����ѡ���¼,Ҳ����ֱ�ӿ�ʼ��д���������</font></p>
			<p><font size='2'>�û���
			<input type='text' name='username' size='12' maxlength='12'>
			</font></p>
			<p><font size='2'>�û�����
			<input type='password' name='password' size='12' maxlength='12'>
			</font></p>
			<p>
				<input type='submit' name='submit' value='��¼'>
			</p>
			</form>";
	//����ڶ�����
	echo "<form method='post' action='finish.php' name='2'>
			<table width='500' border='0' cellspacing='0' cellpadding='0'>
				<tr bgcolor='#ccccff'>
					<td>
						<p><font size='2'>���,</font></p>
						<p>&nbsp;</p>
					</td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td>
						<p><font size='2'>��Ĺ����ܽ��Ϊ ".$total."Ԫ</font></p>
						<p>&nbsp;</p>
					</td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>���סַΪ
						<input type='text' name='addr' size='50'>
					</font></td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>��ĵ绰Ϊ
						<input type='text' name='tel' size='16' maxlength='16'>
					</font></td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>��վ����ֻ�ṩ�����������,�����½�.
					</font><input type='hidden' name='total' value='".$total."'></td>
				</tr>
				<tr bgcolor='#ccccff'>
					<td><font size='2'>
						<input type='submit' name='submit2' value='��ɹ���'>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href='showall.php?job=clear'>ȡ������</a>
						</font>
					</td>	
				</tr>
			</table>
		</form>";	
}
//���ҳ���β����,�˴���
//include_once("footer.php");
?>