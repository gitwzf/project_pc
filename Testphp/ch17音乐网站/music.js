<!--
function Listen(url) {
	var Listen = window.open(url,"Listen","scrollbars=no,width=275,height=116");
	return false;
}

function Geci(url) {
	var Geci = window.open(url,"Geci","marginheight=0 marginwdith=0,toolbar=no,width=450,height=450,directories=no,title=no,status=no,scrollbars=yes,resizable=yes,menubar=no");
	return false;
}

function ReadOrder(url) {
	var ReadOrder = window.open(url,"ReadOrder","scrollbars=yes,width=450,height=350");
	return false;
}

function Modify(url) {
	var Modify = window.open(url,"Modify","marginheight=0 marginwdith=0,toolbar=no,width=600,height=550,directories=no,title=no,status=no,scrollbars=yes,resizable=yes,menubar=no");
	return false;
}

function Post(url) {
	var Post = window.open(url,"Post","marginheight=0 marginwdith=0,toolbar=no,width=500,height=450,directories=no,title=no,status=no,scrollbars=yes,resizable=yes,menubar=no");
	return false;
}

function Query(url) {
	var Query = window.open(url,"Query","scrollbars=yes,width=350,height=400");
	return false;
}

function QueryUser(name) {
     if(name == '') { 
		 alert('代号不能为空！');
		 return false;
	 } else {
		 url = 'user_query.php?user_name='+name;
		 return Query(url);
   }
}

function Geci(url) {
	var Geci = window.open(url,"Geci","marginheight=0 marginwdith=0,toolbar=no,width=450,height=450,directories=no,title=no,status=no,scrollbars=yes,resizable=yes,menubar=no");
	return false;
}

function Favorite(url) {
	var Favorite = window.open(url,"Favorite","width=250,height=50");
	return false;
}

function QuerySinger(url) {
	var QuerySinger = window.open(url,"QuerySinger","marginheight=0 marginwdith=0,toolbar=no,width=450,height=500,directories=no,title=no,status=no,scrollbars=yes,resizable=yes,menubar=no");
	return false;
}

function OrderSong(url) {
	var OrderSong = window.open(url,"OrderSong","marginheight=0 marginwdith=0,toolbar=no,width=450,height=450,directories=no,title=no,status=no,scrollbars=yes,resizable=yes,menubar=no");
	return false;
}

function CheckValue(name) {
	if(name == '') {
		alert('请填写完整信息!');
		return false;
	}
}
//-->