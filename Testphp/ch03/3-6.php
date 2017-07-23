<?php
//设置过期时间
header("Expires: Mon, 26 Jul 2007 05:00:00 GMT");
//设定最后修改时间为访问该页面的时间
header("Last-Modified: ".gmdate("D, d M Y H:i:s")." GMT");
//HTTP 1.1 不保留缓存
header("Cache-Control: no-store, no-cache, must-revalidate");
header("Cache-Control: post-check=0, pre-check=0", false);
//HTTP 1.0 不保留缓存
header("Pragma: no-cache");
?>