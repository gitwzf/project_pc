<?php
//�жϷ�ҳ�����Ƿ��Ѿ�����
if(!function_exists('turnpage')){
	/**
	 * ��ҳ����
	 * @param $total     ��Ϣ����
	 * @param $pagesize  ÿҳ��ʾ��Ϣ������������ΪĬ����20
	 * @param $url       ��ҳ�����е����ӣ�Ĭ��ֵ������Ϊ��ҳURL��
	 * @return 		 ��ҳ���
	 */
	function turnpage($total,$pagesize=10,$url=''){
		//����ȫ�ֱ���
		global $page,$firstcount,$pageresult,$_SERVER;
		//��"$pagesize"����Ϊȫ�ֱ��������㺯���ⲿ����ֱ�ӷ�������
		$GLOBALS["displaypg"]=$pagesize;
		if(!$page)
			$page=1;
		//���$urlʹ��Ĭ�Ͽ�ֵ����ֵΪ��ҳURL��
		if(!$url){
			$url=$_SERVER["REQUEST_URI"];
		}
		//��ʼ����URL
		$parse_url=parse_url($url);
		//����ȡ��URL�Ĳ�ѯ�ִ�
		$url_query=$parse_url["query"];
		if($url_query){
			//ʹ��������ʽ����URL��������ȷ��ҳ����Ϣ
			$url_query=ereg_replace("(^|&)page=$page","",$url_query);
			//��������URL�Ĳ�ѯ�ִ��滻ԭ����URL�Ĳ�ѯ�ִ�
			$url=str_replace($parse_url["query"],$url_query,$url);
			//��URL���page��ѯ��Ϣ��������ֵ
			if($url_query)
				$url.="&page"; 
			else
				$url.="page";
		}else {
			$url.="?page";
		}
		//��ʼ����ҳ����Ϣ
		$lastpg=ceil($total/$pagesize); //��ҳ����Ҳ�����һҳ��Ŀ
		$page=min($lastpg,$page);
		$prepg=$page-1; //��һҳ
		$nextpg=($page==$lastpg ? 0 : $page+1); //��һҳ
		$firstcount=($page-1)*$pagesize;
		//��ʼ�����ҳ����������
		$pageresult="��ʾ�� <B>".($total?($firstcount+1):0)."</B>-<B>"
				.min($firstcount+$pagesize,$total)."</B> ����¼���� $total ����¼<BR>";
		//���ֻ��һҳ����������
		if($lastpg<=1)
			return false;
		$pageresult.=" <a href='$url=1'>��ҳ</a> ";
		if($prepg)
			$pageresult.=" <a href='$url=$prepg'>ǰҳ</a> ";
		else
			$pageresult.=" ǰҳ ";
		if($nextpg) 
			$pageresult.=" <a href='$url=$nextpg'>��ҳ</a> ";
		else
			$pageresult.=" ��ҳ ";
		$pageresult.=" <a href='$url=$lastpg'>βҳ</a> ";
		//ѭ����ʾ������ת�б��ҳ��ҳ��
		$pageresult.="������ <select name='topage' size='1' onchange='window.location"
				."=\"$url=\"+this.value'>\n";
		for($i=1;$i<=$lastpg;$i++){
			if($i==$page)
				$pageresult.="<option value='$i' selected>$i</option>\n";
			else 
				$pageresult.="<option value='$i'>$i</option>\n";
		}
		$pageresult.="</select> ҳ���� $lastpg ҳ";
	  }
}
?>