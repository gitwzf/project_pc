<?php
//���ù���ʱ��
header("Expires: Mon, 26 Jul 2007 05:00:00 GMT");
//�趨����޸�ʱ��Ϊ���ʸ�ҳ���ʱ��
header("Last-Modified: ".gmdate("D, d M Y H:i:s")." GMT");
//HTTP 1.1 ����������
header("Cache-Control: no-store, no-cache, must-revalidate");
header("Cache-Control: post-check=0, pre-check=0", false);
//HTTP 1.0 ����������
header("Pragma: no-cache");
?>