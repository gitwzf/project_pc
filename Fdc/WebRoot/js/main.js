$(function(){ 
var flag=true;
	$('.edit-btn').click(function(){
		var tr=$(this).parent().parent();
		var etd=$('.editoritem');//alert($(etd[1]).attr('type'));
		var td=tr.find("td").each(function(i,v){
			var that=$(v);
			$(etd[i]).attr("value",that.html());
		});
		$('.edit-box').css('display','block');
		flag=false;
	});
	$('.delete-btn').on('click',function(){
	if(flag){
		if(confirm("删除是不可恢复的，你确认要删除吗？")){
			var tr=$(this).parent().parent();
			var first=$($(this).parent().parent().children()[0]).html();
			$.post("crudServ",{first:first},function(data){
				if(data=='11') $(tr).remove();
				else
					if(data=='00')alert('删除失败');
			},'text');
			
			
			
		}
	}
	});
	$('.cancel').on('click',function(){
		$('.edit-box').css('display','none');
		flag=true;
	});
	$('.can-btn').on('click',function(){
		$('.edit-box').css('display','none');
		flag=true;
	});
	$('.new-btn').on('click',function(){
		$('.edit-box').css('display','block');
		flag=!flag;
	});
	$('.sav-btn').on('click',function(){
		$('#editorform').submit();
	//alert($($('.editoritem')[1]).val());
//		$.post("crudServ" ,{editoritem:[3,5,56,35,6]},function(data){},"json");
		
		
	});
	$('.search-btn').on('click',function(){
		$('#searchform').submit();
	});
	
	
}); 
