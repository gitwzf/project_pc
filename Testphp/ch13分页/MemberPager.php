<?php
Class MemberPager extends Pager{
	function showMemberList(){
		global $db;
		//��ȡ�������
		$data = $this->getPageData();
		//��ʾ����Ĵ���
		echo "<table border='1' cellspacing='0' cellpadding='0'>";
		echo "<tr align='center'><td>�������</td><td>���±���</td>"
				."<td>��������</td><td>����ʱ��</td></tr>";
		for ( $i = 0; $i < count($data); $i++){
			echo "<tr>";
			echo "<td>".$data[$i][0]."</td><td>".$data[$i][1]."</td><td>"
					.$data[$i][2]."</td><td>".$data[$i][3]."</td>";
			echo "</tr>";
		}
		echo "</table>";
	}
}
//��ʼ���ã�����ҳ���ҳ����ز�������
if ( isset($_GET['page']) ){
	$page = (int)$_GET['page'];
}else{
	$page = 1;
}
$sql = "select * from members order by uid";
$pager_option = array("sql" => $sql,"PageSize" => 10,"CurrentPageID" => $page);
if ( isset($_GET['numItems']) ){
	$pager_option['numItems'] = (int)$_GET['numItems'];
}
$pager = @new MemberPager($pager_option);
$pager->showMemberList();
?>