<?php
class Page{
	var $pagesize;		//每页显示结果数
	var $numrows;		//结果集总数
	var $pages;			//页面总数
	var $curpage;		//当前页面位置
	var $offset;		//搜索偏移量
	var $url;			//跳转URL地址
	//解析页面相关的参数
	function pageparse($str1,$str2,$str3){
		global $pagesize,$offset;
		$this->pagesize = $str1;
		$this->numrows  = $str2;
		$this->url = $str3;
		$this->pages = intval($this->numrows/$this->pagesize);
		if($this->numrows%$this->pagesize){
			$this->pages ++;
		}
		$nPage = $_GET['page'];
		if(isset($nPage) && $nPage != null && !preg_match("/^\d+$/",$nPage)){
			echo("传递的参数类型错误，请检查！");
			return false;
		}
		if(isset($nPage)){
			$this->curpage = intval($nPage);
		}else{
			$this->curpage = 1;
		}
		if($nPage < 1 || $nPage > $this->pages){
			$this->curpage = 1;
		}
		$this->offset = $this->pagesize * ($this->curpage - 1);
		$pagesize = $this->pagesize;
		$offset = $this->offset;
	}
	//显示分页的结果
	function pageshow(){
		echo "[".$this->numrows."][".$this->curpage."/".$this->pages."]";
		if($this->curpage > 4){
			echo "<a href='".$this->url."=1'><font style='font-family:Webdings;'>7</font></a>";
		}
		if($this->curpage != 1){
			$pageup = $this->curpage - 1;
			echo "<a href='".$this->url."=".$pageup."'><font style='font-family:Webdings;'>3</font></a>";
		}
		if($this->curpage <= 3){
			for($i = 1 ; $i <= 10 ; $i ++){
				if($i <= $this->pages){
					if($i == $this->curpage){
						echo "[".$i."]";
					}else{
						echo "[<a href='".$this->url."=".$i."'>".$i."</a>]";
					}
				}
			}
		}else if($this->curpage >= $this->pages-6){
			for($i = $this->pages-9 ; $i <= $this->pages ; $i ++){
				if($i == $this->curpage){
					echo "[".$i."]";
				}else{
					echo "[<a href='".$this->url."=".$i."'>".$i."</a>]";
				}
			}
		}else{
			for($i = $this->curpage-3 ; $i <= $this->curpage+6 ; $i ++){
				if($i == $this->curpage){
					echo "[".$i."]";
				}else{
					echo "[<a href='".$this->url."=".$i."'>".$i."</a>]";
				}
			}
		}
		if($this->curpage != $this->pages && $this->pages != 0){
			$pagedown = $this->curpage + 1;
			echo "<a href='".$this->url."=".$pagedown."'><font style='font-family:Webdings;'>4</font></a>";
		}
		if($this->curpage < $this->pages-6){
			echo "<a href='".$this->url."=".$this->pages."'><font style='font-family:Webdings;'>8</font></a>";
		}
	}
}
?>