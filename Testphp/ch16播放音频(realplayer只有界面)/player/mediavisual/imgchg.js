<!--
//
// �� ���ж�̬��ťͼ�ļ����л�����
// 
toggleKey = new Object();
toggleKey[0] = "_off";
toggleKey[1] = "_on";
toggleKey[2] = "_ovr";
toggleKey[3] = "_out";
toggleKey[4] = "_mdn";
toggleKey[5] = "_mup";
function imgChange(id,act){
if(document.images){ document.images[id].src = eval("img." + id + toggleKey[act] + ".src");}
}
// ����γ������Ӧ�õ�������ʹ��ʱ��
// �Ժ�ʽ imgChange('��ťʶ������',0) ���еĶ�����ʹ�� "off" ��ͼ����
// �Ժ�ʽ imgChange('��ťʶ������',1) ���еĶ�����ʹ�� "on" ��ͼ����
// ����Ĳ��ݾ����趨 "off" �� "on" �Ķ�̬��ťͼ�ļ���
// vmute, pmode, rept, playt, pauzt, stopt ��Щ���ǡ���ťʶ�����ơ���
if(document.images){
img = new Object();
// ������ģʽ����ť��ͼ�ļ� (�ѹرգ��ѿ���)
img.vmute_off = new Image();
img.vmute_off.src = "img/btn_mute_off.gif";
img.vmute_on = new Image();
img.vmute_on.src = "img/btn_mute_on.gif";

// ������˳��ģʽ����ť��ͼ�ļ� (ѭ�����)
img.pmode_off = new Image();
img.pmode_off.src = "img/btn_rndmode_off.gif";
img.pmode_on = new Image();
img.pmode_on.src = "img/btn_rndmode_on.gif";
// ���Ƿ��ظ����š���ť��ͼ�ļ� (���ظ����ظ�)
img.rept_off = new Image();
img.rept_off.src = "img/btn_rept_off.gif";
img.rept_on = new Image();
img.rept_on.src = "img/btn_rept_on.gif";
// �����š���ť��ͼ�ļ� (�ǲ����У�������)
img.playt_off = new Image();
img.playt_off.src = "img/btn_play.gif";
img.playt_on = new Image();
img.playt_on.src = "img/btn_play_on.gif";
// ����ͣ����ť��ͼ�ļ� (����ͣ�У���ͣ��)
img.pauzt_off = new Image();
img.pauzt_off.src = "img/btn_pauz_off.gif";
img.pauzt_on = new Image();
img.pauzt_on.src = "img/btn_pauz_on.gif";
// ��ֹͣ����ť��ͼ�ļ� (δ��ֹͣ����ֹͣ)
img.stopt_off = new Image();
img.stopt_off.src = "img/btn_stop.gif";
img.stopt_on = new Image();
img.stopt_on.src = "img/btn_stop_on.gif";
// ��ʾ����״̬�� Scope ��̬ͼ�ļ� (��ֹ��ת��)
img.scope_off = new Image();
img.scope_off.src = "img/roll_off.gif";
img.scope_on = new Image();
img.scope_on.src = "img/roll_on.gif";
// �����ͬʱҲ������һ����ʾ������״̬���� Scope ��̬ͼ�ļ���
// scope_off.gif, scope_on.gif
// ���Խ�������ͼ��ȡ�� roll_off.gif, roll_on.gif ���Կ���
// ��ע��Ҫͬʱ�޸� exobud.htm ��Ӧ�ĵط�Ϊ�����ӣ�
// img name="scope" src="./img/scope_off.gif" width=17 height=16 ...
}
//-->

