<!--
//
// ■ 进行动态按钮图文件的切换动作
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
// 当这段程序代码应用到播放器使用时：
// 以函式 imgChange('按钮识别名称',0) 进行的动作即使用 "off" 的图档；
// 以函式 imgChange('按钮识别名称',1) 进行的动作即使用 "on" 的图档。
// 下面的部份就是设定 "off" 与 "on" 的动态按钮图文件。
// vmute, pmode, rept, playt, pauzt, stopt 这些都是「按钮识别名称」。
if(document.images){
img = new Object();
// 「静音模式」按钮的图文件 (已关闭／已开启)
img.vmute_off = new Image();
img.vmute_off.src = "img/btn_mute_off.gif";
img.vmute_on = new Image();
img.vmute_on.src = "img/btn_mute_on.gif";

// 「播放顺序模式」按钮的图文件 (循序／随机)
img.pmode_off = new Image();
img.pmode_off.src = "img/btn_rndmode_off.gif";
img.pmode_on = new Image();
img.pmode_on.src = "img/btn_rndmode_on.gif";
// 「是否重复播放」按钮的图文件 (不重复／重复)
img.rept_off = new Image();
img.rept_off.src = "img/btn_rept_off.gif";
img.rept_on = new Image();
img.rept_on.src = "img/btn_rept_on.gif";
// 「播放」按钮的图文件 (非播放中／播放中)
img.playt_off = new Image();
img.playt_off.src = "img/btn_play.gif";
img.playt_on = new Image();
img.playt_on.src = "img/btn_play_on.gif";
// 「暂停」按钮的图文件 (非暂停中／暂停中)
img.pauzt_off = new Image();
img.pauzt_off.src = "img/btn_pauz_off.gif";
img.pauzt_on = new Image();
img.pauzt_on.src = "img/btn_pauz_on.gif";
// 「停止」按钮的图文件 (未被停止／已停止)
img.stopt_off = new Image();
img.stopt_off.src = "img/btn_stop.gif";
img.stopt_on = new Image();
img.stopt_on.src = "img/btn_stop_on.gif";
// 显示播放状态的 Scope 动态图文件 (静止／转动)
img.scope_off = new Image();
img.scope_off.src = "img/roll_off.gif";
img.scope_on = new Image();
img.scope_on.src = "img/roll_on.gif";
// 此面板同时也附上另一套显示「播放状态」的 Scope 动态图文件：
// scope_off.gif, scope_on.gif
// 可以将这两个图档取代 roll_off.gif, roll_on.gif 试试看。
// 请注意要同时修改 exobud.htm 对应的地方为这样子：
// img name="scope" src="./img/scope_off.gif" width=17 height=16 ...
}
//-->

