var browser={  
    versions:function(){ 
            var u = navigator.userAgent, app = navigator.appVersion;
            return {         //�ƶ��ն�������汾��Ϣ
                 trident: u.indexOf('Trident') > -1, //IE�ں�
                presto: u.indexOf('Presto') > -1, //opera�ں�
                webKit: u.indexOf('AppleWebKit') > -1, //ƻ�����ȸ��ں�
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //����ں�
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //�Ƿ�Ϊ�ƶ��ն�
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios�ն�
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android�ն˻�uc�����
                iPhone: u.indexOf('iPhone') > -1 , //�Ƿ�ΪiPhone����QQHD�����
                iPad: u.indexOf('iPad') > -1, //�Ƿ�iPad
                webApp: u.indexOf('Safari') == -1 //�Ƿ�webӦ�ó���û��ͷ����ײ�
            };
         }(),
         language:(navigator.browserLanguage || navigator.language).toLowerCase()
}
$('.f').focus(function(){
	if(document.activeElement!=$('.f')&&browser.versions.ios){
	$(".footer_menu").css('display','none'); 
}
})
$('.f').blur(function(){
	$(".footer_menu").css('display','block'); 
})
  
