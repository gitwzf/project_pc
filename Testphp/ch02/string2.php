<?php
//�ַ����ļ���
echo ">>ʹ��md5()��ȡ�ļ���ϣֵʾ��";
$str = 'apple';
if (md5($str) === '1f3870be274f6c49b3e31a0c6728957f') {
    echo "Would you like a green or red apple?";
}
echo "<br>";
echo ">>ʹ��sha1()��ȡ��ϣֵʾ��";
$str = 'apple';             
if (sha1($str) === 'd0be2dc421be4fcd0172e5afceea3970e2f3d940') {
    echo "Would you like a green or red apple?";
}else{
	echo "apple's sha1 hash=".sha1($str);
}
echo "<br>";
echo ">>ʹ��crc32()У����ʾ��";
$checksum = crc32("The quick brown fox jumped over the lazy dog.");
printf("%u\n", $checksum);
?>