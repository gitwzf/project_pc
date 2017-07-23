<?php
Class MemberPager extends Pager{
	function showMemberList(){
		global $db;
		//获取结果数据
		$data = $this->getPageData();
		//显示结果的代码
		echo "<table border='1' cellspacing='0' cellpadding='0'>";
		echo "<tr align='center'><td>文章序号</td><td>文章标题</td>"
				."<td>文章内容</td><td>发表时间</td></tr>";
		for ( $i = 0; $i < count($data); $i++){
			echo "<tr>";
			echo "<td>".$data[$i][0]."</td><td>".$data[$i][1]."</td><td>"
					.$data[$i][2]."</td><td>".$data[$i][3]."</td>";
			echo "</tr>";
		}
		echo "</table>";
	}
}
//开始调用，处理页面分页的相关参数变量
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