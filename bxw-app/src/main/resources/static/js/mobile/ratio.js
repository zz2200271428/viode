// JavaScript Document

//最大分辨率,默认支持全屏，输入具体数值（分辨率）支持最大分辨率
//例：支持全分辨率: var zmaxWidth=false;  支持最大分辨率640: var zmaxWidth=640;
var zmaxWidth=640;
function zzoomm(zf){
	if(zf){
		if($(window).width()<zf){
			$("html").css('fontSize',$(window).width()/640*20+'px');
		}else{
			$("html").css('fontSize',zf/640*20+'px');
		}
	}else{
		$("html").css('fontSize',$(window).width()/640*20+'px');
	}
}

zzoomm(zmaxWidth);
$(window).resize(function(){
	zzoomm(zmaxWidth);
});