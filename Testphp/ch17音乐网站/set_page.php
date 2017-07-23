<?php
/* 分页显示：[1]... [<<] - [<] [9] [10] [11] [12] [13] [14] [15] [16] [>] - [>>] ...[232] */
/* url Format: ./list.php?page=  ; system will add the Pagenum after the Url automaticly */
function set_page($url, $total, $curpage = 1, $perpage = 15, $perlist = 8)
{
	if($total <= 0) return;
	if($curpage <= 0) $curpage = 1;
	$totalpage = ceil($total/$perpage);
	$totallist = ceil($totalpage/$perlist);
	
	if($curpage > $totalpage) $curpage = $totalpage;
	$curlist = ceil($curpage / $perlist);

	$toppage = $curlist * $perlist;
	if($toppage > $totalpage) $toppage = $totalpage;
	$botpage = ($curlist-1)*$perlist + 1;
	if($botpage < 1) $botpage = 1;

	$ret = "";
	settype($ret, "string");
	
	if($curlist > 1) {
		$ret .= "<a title=\"第一页\" href=\"".$url."1\">[1]</a>...\n";
		$ret .= "<a title=\"上一列\" href=\"".$url.($botpage - $perlist)."\">[&lt;&lt;]</a> - \n";
	}
	if($curpage > 1)
		$ret .= "<a title=\"上一页\" href=\"".$url.($curpage - 1)."\">[&lt;]</a> \n";

	do
	{
		if($botpage == $curpage)
			$ret .= "<b><font color=red>[".$botpage."]</font></b> \n";
		else
			$ret .= "<a title=\"第".$botpage."页\" href=\"".$url.$botpage."\">[".$botpage."]</a> \n";
	} while($botpage++ < $toppage);

	if($totalpage > $curpage)
		$ret .= "<a title=\"下一页\" href=\"".$url.($curpage + 1)."\">[&gt;]</a> \n";

	if($totallist > $curlist) {
		$ret .= "- <a title=\"下一列\" href=\"".$url.($toppage + 1)."\">[&gt;&gt;]</a>\n";
		$ret .= "...<a title=\"最后一页\" href=\"".$url.$totalpage."\">[".$totalpage."]</a>\n";
	}

	$ret .= "Total: ".$total;
	return $ret;
}
?>