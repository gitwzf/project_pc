<?php
$filename = 'test.txt';
$somecontent = "�����Щ���ֵ��ļ�\n";
// ��������Ҫȷ���ļ����ڲ��ҿ�д��
if (is_writable($filename)) {
    //�������������ǽ�ʹ�����ģʽ��$filename��
    //��ˣ��ļ�ָ�뽫�����ļ��Ŀ�ͷ��
    //�Ǿ��ǵ�����ʹ��fwrite()��ʱ��$somecontent��Ҫд��ĵط���
    if (!$handle = fopen($filename, 'a')) {
         print "���ܴ��ļ� $filename";
         exit;
    }
    //��$somecontentд�뵽���Ǵ򿪵��ļ��С�
    if (!fwrite($handle, $somecontent)) {
        print "����д�뵽�ļ� $filename";
        exit;
    }
    print "�ɹ��ؽ� $somecontent д�뵽�ļ�$filename";
    fclose($handle);
} else {
    print "�ļ� $filename ����д";
}
?>