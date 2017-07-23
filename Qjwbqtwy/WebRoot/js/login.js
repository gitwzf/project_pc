// 登录
function onRegBtn() {
	var khDlm = $("#khDlm").val();
	var khDlmm = $("#khDlmm").val();
	var divshow = $("#errorMess");
	// 进入后台进行提交
	
	var url = "RegistServ";
	var params = {
		"khDlm" : khDlm,
		"khDlmm" : khDlmm,
		timestamp : new Date().getTime()
	};
	$.post(url, params, function(data) {
		divshow.html("");
		var backCode = data.backCode;
		if (data.backCode == -1) {
			divshow.attr("style","color: red; font-size: 12px; text-align:center;");
			divshow.html(data.errorMess);
		}
		if (data.backCode == 0) {
			window.location.href = "./ModeltypeServ";
		}
	},"json");
}