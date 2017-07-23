<?php
//字符串的加密
echo ">>使用md5()获取文件哈希值示例";
$str = 'apple';
if (md5($str) === '1f3870be274f6c49b3e31a0c6728957f') {
    echo "Would you like a green or red apple?";
}
echo "<br>";
echo ">>使用sha1()获取哈希值示例";
$str = 'apple';             
if (sha1($str) === 'd0be2dc421be4fcd0172e5afceea3970e2f3d940') {
    echo "Would you like a green or red apple?";
}else{
	echo "apple's sha1 hash=".sha1($str);
}
echo "<br>";
echo ">>使用crc32()校验码示例";
$checksum = crc32("The quick brown fox jumped over the lazy dog.");
printf("%u\n", $checksum);
?>