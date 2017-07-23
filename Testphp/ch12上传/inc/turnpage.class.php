<?php
//判断分页函数是否已经存在
if(!function_exists('turnpage')){
	/**
	 * 分页函数
	 * @param $total     信息总数
	 * @param $pagesize  每页显示信息数，这里设置为默认是20
	 * @param $url       分页导航中的链接，默认值本该设为本页URL。
	 * @return 		 分页结果
	 */
	function turnpage($total,$pagesize=10,$url=''){
		//定义全局变量
		global $page,$firstcount,$pageresult,$_SERVER;
		//把"$pagesize"设置为全局变量，方便函数外部可以直接访问它。
		$GLOBALS["displaypg"]=$pagesize;
		if(!$page)
			$page=1;
		//如果$url使用默认空值，则赋值为本页URL。
		if(!$url){
			$url=$_SERVER["REQUEST_URI"];
		}
		//开始解析URL
		$parse_url=parse_url($url);
		//单独取出URL的查询字串
		$url_query=$parse_url["query"];
		if($url_query){
			//使用正则表达式处理URL，设置正确的页码信息
			$url_query=ereg_replace("(^|&)page=$page","",$url_query);
			//将处理后的URL的查询字串替换原来的URL的查询字串
			$url=str_replace($parse_url["query"],$url_query,$url);
			//在URL后加page查询信息，但待赋值
			if($url_query)
				$url.="&page"; 
			else
				$url.="page";
		}else {
			$url.="?page";
		}
		//开始计算页码信息
		$lastpg=ceil($total/$pagesize); //总页数，也是最后一页数目
		$page=min($lastpg,$page);
		$prepg=$page-1; //上一页
		$nextpg=($page==$lastpg ? 0 : $page+1); //下一页
		$firstcount=($page-1)*$pagesize;
		//开始处理分页导航条代码
		$pageresult="显示第 <B>".($total?($firstcount+1):0)."</B>-<B>"
				.min($firstcount+$pagesize,$total)."</B> 条记录，共 $total 条记录<BR>";
		//如果只有一页则跳出函数
		if($lastpg<=1)
			return false;
		$pageresult.=" <a href='$url=1'>首页</a> ";
		if($prepg)
			$pageresult.=" <a href='$url=$prepg'>前页</a> ";
		else
			$pageresult.=" 前页 ";
		if($nextpg) 
			$pageresult.=" <a href='$url=$nextpg'>后页</a> ";
		else
			$pageresult.=" 后页 ";
		$pageresult.=" <a href='$url=$lastpg'>尾页</a> ";
		//循环显示下拉跳转列表的页面页码
		$pageresult.="　到第 <select name='topage' size='1' onchange='window.location"
				."=\"$url=\"+this.value'>\n";
		for($i=1;$i<=$lastpg;$i++){
			if($i==$page)
				$pageresult.="<option value='$i' selected>$i</option>\n";
			else 
				$pageresult.="<option value='$i'>$i</option>\n";
		}
		$pageresult.="</select> 页，共 $lastpg 页";
	  }
}
?>