<!--
function do_select(faint) {
	len = document.SelectSongs.SelectSongsID.length;
	for(var i=0; i < len; i++) {
		document.SelectSongs.SelectSongsID[i].checked = faint;
	}
	return true;
}

function play_song() {
	var SelectValue = "";
	for(var i=0; i < document.SelectSongs.SelectSongsID.length; i++) {
		if(document.SelectSongs.SelectSongsID[i].checked) {
			SelectValue += document.SelectSongs.SelectSongsID[i].value.toString() + ",";
		}
	}
	
	if(SelectValue == "") {
		alert("至少选择1首歌曲！");
		return false;
	}
	var url="listen/listen.php?SelectSongsID="+SelectValue;
	window.open(url,"Listen","scrollbars=no,width=275,height=116");
	document.SelectSongs.reset();
	return true;
}

function collect_song() {
	var SelectValue = "";

	for(var i=0;i<document.SelectSongs.SelectSongsID.length;i++) {
		if(document.SelectSongs.SelectSongsID[i].checked) {
			SelectValue += document.SelectSongs.SelectSongsID[i].value.toString() + ",";
		}
	}
	
	if(SelectValue == "") {
		alert("至少选择1首歌曲！");
		return false;
	}	
	var url="favorite.php?type=song&song_id=" + SelectValue;
	document.SelectSongs.reset(); // reset form
	return Favorite(url);
}

function del_song() {

	if(confirm("真的要删除选中的歌曲? ") != true) {
		return false;
	}

	var SelectValue = "";
	for(var i=0;i<document.SelectSongs.SelectSongsID.length;i++) {
		if(document.SelectSongs.SelectSongsID[i].checked) {
			SelectValue += document.SelectSongs.SelectSongsID[i].value.toString() + ",";
		}
	}	
	if(SelectValue == "") {
		alert("至少选择1首歌曲！");
		return false;
	}	
	var url="my_favorite_del.php?type=song&id="+SelectValue;
	document.location = url;
	return true;
}
//-->