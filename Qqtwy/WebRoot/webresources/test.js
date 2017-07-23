function myCallbackMethod(dataObj) {
	alert(dataObj.returnValue);
	var reportguid=getComponentGuidById(dataObj.pageid, dataObj.componentid);//先取到报表GUID   
	var datasArr=null;  
	if(WX_ALL_SAVEING_DATA!=null) datasArr =WX_ALL_SAVEING_DATA[reportguid];  
	if(datasArr!=null)  {   
		for(var i=0;i<datasArr.length;i++)  
		{    var datasObj=datasArr[i];//取到存放当前记录数据的对象   
		for(var paramname in datasObj)   
		{     alert('参数名：'+paramname+';参数值'+datasObj[paramname]);  } } 
	}
}
function testCallbackMethod(xmlHttpObj,datasObj) {
	alert(xmlHttpObj.responseText);//服务器端返回的字符串
	if(datasObj==null) return;//本次调用没有传入数据
	var datasObjArr=convertToArray(datasObj);//将当前对象转变为数组
	for(var i=0;i<datasObjArr.length;i++) 
	{ for(key in datasObjArr[i]) 
	{ alert(key+':'+datasObjArr[i][key]); }
}
}

function edit(){
	var area=document.getElementsByTagName("textarea");
	//alert("yes"+area.length);
  for(var i=0;i<area.length;i++)
  { // alert(area[i].id+"  "+area[i].rows);
//  var b=document.getElementById(area[i].id);
//  alert(b.innerHTML);
	 if(area[i].rows!=2){
var editor = new baidu.editor.ui.Editor();
  editor.render(area[i].id);}
  }
  // var editor = new baidu.editor.ui.Editor();
  //editor.render('editor');
  }

function edit2(obj){
	var val=obj.value;
	//alert(val);
	var a=val.lastIndexOf("\\");
	//alert(a);
	var picurl=val.substring(a+1);
	//alert("3");
	obj.value=picurl;
	//alert("4");
	//alert(obj.value.lastIndexOf("\\"));
}

function edit3(pageid){
	//获取选中行id
	//alert(pageid);
	var trid;
	var type;
	var tr_ids=document.getElementsByTagName("tr");
//	alert(tr_ids[tr_ids.length-1]);
	for(var i=0;i<tr_ids.length;i++){
	if(tr_ids[i].id!="") 
		if(tr_ids[i].style["background-color"]=="rgb(172, 250, 240)") //火狐下不行
		{
			trid=tr_ids[i].childNodes[0].firstChild.firstChild.innerHTML;
	//		alert(trid);
			type=tr_ids[i].childNodes[1].firstChild.firstChild.innerHTML;
	//		alert(type);
			break;
		}
	}
	
	 //更改从表keyid    
var count=0;
	var last_tr="";
	for(var i=0;i<tr_ids.length;i++){
		var tid=tr_ids[i].id;
	    if(tid.indexOf(pageid+"_guid_pic_mus_reback_tr")!=-1)
	    	{count=count+1;last_tr=tr_ids[i]}}
	if(count>8){alert("无法发送如此多条信息！");return;}
	//alert("条数："+count);

	count=count+1;
	var a;
	if(type=="图文")a=1;
	else if(type=="音频")a=2;
	else {
		type="图文";
		a=1;
	}
	
//	alert(last_tr.firstChild.firstChild.innerHTML=="");     onmouseover=\"edit3()\"
	for(var i=tr_ids.length-1;i>=0;i--){
		var tid=tr_ids[i].id;
	//	alert(tid);
		  if(tid.indexOf(pageid+"_guid_pic_mus_reback_tr")!=-1)
		  {	last_tr=tr_ids[i];
	          if(last_tr.firstChild.firstChild.innerHTML=="")
	          { last_tr.innerHTML="<td id=\""+pageid+"_guid_pic_mus_reback_wxcol_id__td"+count+"\"  class=\"cls-data-td-editlist\"align=\"center\">-</td><td id=\""+pageid+"_guid_pic_mus_reback_wxcol_keyid__td"+count+"\" oldvalue=\"0\" oldvalue_name=\"keyid__old\" value_name=\"keyid\" value=\""+trid+"\" class=\"cls-data-td-editlist\" align=\"center\"><div style=\"width:100%;\" class=\"cls-data-content-list\" align=\"center\"><span tagtype=\"COL_CONTENT_WRAP\">"+trid+"</span></div></td><td align=\"center\" attachinfo=\"\" " +
	              		"onclick=\"try{fillInputBoxOnClick(event,'"+pageid+"','"+pageid+"_guid_pic_mus_reback','editablelist2','radiobox','"+pageid+"_guid_pic_mus_reback_wxcol_type__"+count+"');}catch(e){logErrorsAsJsFileLoad(e);}\" class=\"cls-data-td-editlist\" value=\""+a+"\" value_name=\"type\" oldvalue_name=\"type__old\" oldvalue=\"0\" id=\""+pageid+"_guid_pic_mus_reback_wxcol_type__td"+count+"\"><div class=\"cls-data-content-list\" style=\"width:100%;\"><span tagtype=\"COL_CONTENT_WRAP\">"+type+"</span></div></td><td class=\"cls-data-td-editlist\" value_name=\"title\" " +
	              		"id=\""+pageid+"_guid_pic_mus_reback_wxcol_title__td"+count+"\" onclick=\"fillInputBoxOnClick(event,'"+pageid+"','"+pageid+"_guid_pic_mus_reback','editablelist2','textbox','"+pageid+"_guid_pic_mus_reback_wxcol_title__"+count+"')\"  value=\"\"><div class=\"cls-data-content-list\" style=\"width:100%;\"><span tagtype=\"COL_CONTENT_WRAP\"></span></div></td><td class=\"cls-data-td-editlist\" value_name=\"main\" id=\""+pageid+"_guid_pic_mus_reback_wxcol_main__td"+count+"\" onclick=\"fillInputBoxOnClick(event,'"+pageid+"','"+pageid+"_guid_pic_mus_reback','editablelist2','textareabox'," +
	              				"'"+pageid+"_guid_pic_mus_reback_wxcol_main__"+count+"')\" value=\"\"><div class=\"cls-data-content-list\" style=\"width:100%;\"><span tagtype=\"COL_CONTENT_WRAP\"></span></div></td>" +
"<td class=\"cls-data-td-editlist\" value_name=\"urlo\" id=\""+pageid+"_guid_pic_mus_reback_wxcol_urlo__td"+count+"\"><div style=\"width:100%;\" class=\"cls-data-content-list\"><span tagtype=\"COL_CONTENT_WRAP\"><input type=\"text\"value=\"\" id=\""+pageid+"_guid_pic_mus_reback_wxcol_urlo__"+count+"\" name=\""+pageid+"_guid_pic_mus_reback_wxcol_urlo__"+count+"\" typename=\"file\" readonly=\"\" class=\"cls-inputbox2-full\" onkeypress=\"return onKeyEvent(event);\" onmouseover=\"this.style.cursor='pointer';\" style=\"text-align: left; cursor: pointer;\" onfocus=\"try{this.select();}catch(e){logErrorsAsJsFileLoad(e);}" +
		"\" onblur=\"try{edit2(this);try{addInputboxDataForSaving('"+pageid+"_guid_pic_mus_reback',this);}catch(e){logErrorsAsJsFileLoad(e);};}catch(e){logErrorsAsJsFileLoad(e);}\"onclick=\"try{popupPageByFileUploadInputbox('"+pageid+"_guid_pic_mus_reback_wxcol_urlo__"+count+"','{pageid:wx_DBLQUOTE_wx"+pageid+"wx_DBLQUOTE_wx,reportid:wx_DBLQUOTE_wxpic_mus_rebackwx_DBLQUOTE_wx,popupPageUrl:wx_DBLQUOTE_wxnullwx_DBLQUOTE_wx,popupparams:wx_DBLQUOTE_wx{width:300,height:160,handler:closePopUpPageEvent}wx_DBLQUOTE_wx}','inputbox');}catch(e){logErrorsAsJsFileLoad(e);}\"></span></div></td><td id=\""+pageid+"_guid_pic_mus_reback_wxcol_pty__td"+count+"\" oldvalue=\"\" " +
	              				"oldvalue_name=\"pty__old\" value_name=\"pty\" value=\"\" class=\"cls-data-td-editlist\" onclick=\"try{fillInputBoxOnClick(event,'"+pageid+"','"+pageid+"_guid_pic_mus_reback','editablelist2','selectbox','"+pageid+"_guid_pic_mus_reback_wxcol_pty__"+count+"');}catch(e){logErrorsAsJsFileLoad(e);}\" attachinfo=\"\" align=\"center\"><div style=\"width:100%;\" class=\"cls-data-content-list\"><span tagtype=\"COL_CONTENT_WRAP\"></span></div></td><td id=\""+pageid+"_guid_pic_mus_reback_wxcol_pid__td"+count+"\" oldvalue=\"0\" oldvalue_name=\"pid__old\" value_name=\"pid\"" +
	              						" value=\"\" class=\"cls-data-td-editlist\" onclick=\"try{fillInputBoxOnClick(event,'"+pageid+"','"+pageid+"_guid_pic_mus_reback','editablelist2','textbox','"+pageid+"_guid_pic_mus_reback_wxcol_pid__"+count+"');}catch(e){logErrorsAsJsFileLoad(e);}\" attachinfo=\"\" align=\"center\"><div style=\"width:100%;\" class=\"cls-data-content-list\"><span tagtype=\"COL_CONTENT_WRAP\"></span></div></td>";
	             
	          }
	         else break;}
	//alert("yes");
	}
}
function showimg(e,obj){  //预览图片
	$('body').append("<div id='pic' style='position: fixed;'><img src='http://syw.zjol.com.cn/image/"+obj.attributes["showname"].value+"/"+obj.value+"'/></div>");
 $('#pic').css("left",mousePos(e).x);
 $('#pic').css("top",mousePos(e).y); 
	}
	function hidimg(){//remove
	var node=document.getElementById("pic");
	document.body.removeChild(node);
	}
	function mousePos(e){
var x,y;
var e = e||window.event;
return {
x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
};
}
function upmodelcompete(id1,id2){
	if(id1==''||id2==''){alert("编号没有取值！");return;}
	if(id2=='0'){alert("该榜单无须生成！");return;}
	var numbers=prompt("晋级名额：");
    if(numbers==null)return;
	if(isNaN(numbers)||numbers==''){alert("请输入数字");return;}
	else numbers=Number(numbers);
	window.parent.upcompete(id1,id2,numbers);
}
function jsupdatesql(sql,name){//自定义sql执行语句
	if(confirm("确认"+name+"吗？"))
	window.parent.updatesql(sql);

}