function mysubmit(id){
	var temp=$("#unameId").val();
	if(temp==""){
		showError("账户不能为为空");
		return;
	}
	temp=$("#passwordId").val();
	if(temp==""){
		showError("密码不能为空");
		return;
	}
	if($("#numcode_tr").is(":hidden")==false){
		temp=$("#numcode").val();
		if(temp==""){
			showError("请输入验证码");
			return;
		}else if(temp.length!=4){
			showError("验证码错误");
			return;
		}
	}
	temp=$("#unameId").val();
	var reg_email  = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g
	var reh_phone  = /^1[34578]\d{9}$/;
	if(!reg_email.test(temp)&&!reh_phone.test(temp)){
		showError("用户名格式错误");
		return;
	}
	var paramter = $("#"+id);
	$.ajax({
		type: "post",
		dataType: "json",
		url: '/appuser/login',
		data: paramter.serialize(),
		success: function (datas) {       //data回调信息
			console.log(datas)
			console.log(datas.msg)
			console.log(datas.code)
			if (datas.code==200) {
				$("#a").text("登录中...");
				location.href = '/index'
			}else if (datas.code==300){
				showError("验证码错误");
				return;
			}else{
				showError("用户名或密码错误")
				return;
			}
		}
	});
}
function showError(name){
	$("#show_error").html(name);
}
function hideError(id){
      $("#"+id).hide();
}
function setEmpty(id){
    $("#"+id).html("");
}
function keydownSubmit(event){
	e = event ? event :(window.event ? window.event : null);
	if(e.keyCode==13){
		mysubmit('form');
	}
}
function hideNameInput(){
	$("#nameNoteId").hide();
	$("#unameId").focus();
}
function showNameInput(){
	var v1=$("#unameId").val();
	if(v1==null || ""==v1){
		$("#nameNoteId").show();
	}
}
function  hidePwdInput(){
	$("#pwdNoteId1").hide();
	$("#passwordId").focus();
}
function  showPwdInput(){
	var v2=$("#passwordId").val();
	if(v2==null || ""==v2){
		$("#pwdNoteId1").show();
	}
}
function hideNameError(){
	$("#show_error").html("");
	$("#nameNoteId").hide();
}
function hidePwdError(){
	$("#show_error").html("");
	$("#pwdNoteId1").hide();
}
function  showNotice(fid){
	  if(fid==null ||  fid=="" || fid<0){
	       return;
	  }else{
	    $.ajax({ 
      		url: "/api/notice?fid="+fid,
      		type:"get",
          	success: function(date){
  				 var myjson=eval("("+date+")");
					 if(myjson.status==true){
					      $("#body_0x004").removeClass("xin_narrow");
						  $("#notice_0x004").html(myjson.content);
						  $("#notice_box_0x004").show();
					 }
  			}
      	});	
	  }
}

function  showGuide(){
  $("#body_0x003").removeClass("zin_narrow");
  $("#notice_box_0x003").show();
}

//dx设置cookie
function  setCookie(){
	var fid=$("#fid").val();
    $.ajax({ 
		url: "http://www.fanya.chaoxing.com/passport/setcookie?fid="+fid,
		type:"get",
    	success: function(date){}
	});	
}
function getVarCode(){
	var img = document.getElementById("imgVerCode");
	img.src="/img/code?"+new Date().getTime();
}


function getNumCode(){
	var img = document.getElementById("numVerCode");
	img.src="/appuser/code?"+new Date().getTime();
}