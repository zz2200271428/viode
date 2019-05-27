var emailInterValObj; //邮箱timer变量，控制时间
var emailcount = 90; //邮箱间隔函数，1秒执行
var emailcurCount;//邮箱当前剩余秒数

var phoneInterValObj; //手机timer变量，控制时间
var phonecount = 90; //手机间隔函数，1秒执行
var phonecurCount;//手机当前剩余秒数

//邮箱timer处理函数
function SetEmailRemainTime() {
    if (emailcurCount == 0) {                
        window.clearInterval(emailInterValObj);//停止计时器
        $("#btnEmailOrPhoneCode").attr("onclick", "getEmailOrPhoneCode()");//启用按钮
        $("#btnEmailOrPhoneCode").html("重新发送");
    }
    else {
        emailcurCount--;
        $("#tooltip_td").html("校验码已发出，请注意查收短信，<span>"+emailcurCount+"</span>秒后可要求系统重新发送");
    }
}

//手机timer处理函数
function SetPhoneRemainTime() {
    if (phonecurCount == 0) {        
        window.clearInterval(phoneInterValObj);//停止计时器
        $("#btnEmailOrPhoneCode").attr("onclick", "getEmailOrPhoneCode()");//启用按钮
        $("#btnEmailOrPhoneCode").html("重新发送");
    }
    else {
        phonecurCount--;
        $("#tooltip_td").html("校验码已发出，请注意查收短信，<span>"+phonecurCount+"</span>秒后可要求系统重新发送");
    }
}
/*邮箱格式验证*/
function isEmail(name){
	 var reg_email  = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
	 return reg_email.test(name);
}
/*手机格式验证*/
function isPhone(name){
	 var reh_phone  = /^1[34578]\d{9}$/;
	 return reh_phone.test(name);
}

/*获取邮箱验证码*/
function getEmailCode(){
	var email=$("#emailorphone").val();
	 $.ajax({ 
			url: "/appuser/emailcodepwd?email="+email,
			type:"get",
			success: function(date){
			     if(date.code==200){
					 emailcurCount = emailcount;//设置button效果，开始计时
					 $("#btnEmailOrPhoneCode").attr("onclick", "");
					 $("#btnEmailOrPhoneCode").html("已发送");
					 $(".tooltip").removeClass("zl_tr_td_hide");
					 $("#tooltip_td").html("校验码已发出，请注意查收短信，<span>90</span>秒后可要求系统重新发送")
					 emailInterValObj = window.setInterval(SetEmailRemainTime, 1000); //启动计时器，1秒执行一次
				 }else if(date.code==404){
					 showError('error_td','用户不存在');
					 return;
				 }else if(date.code==500){
			     	showError('error_td','天发送的验证码已到最多上线！稍后再试');
					return;
				}else{
					 showError('error_td','服务器错误发送失败');
					 return;
			    }
			}
	 });
}
/*获取手机号验证码*/
function getPhoneCode(){
	var phone=$("#emailorphone").val();
	var code=$("#numcode").val();
	$.ajax({ 
		 url: "/appuser/phonecodepwd?phone="+phone+"&code="+code,
		 type:"get",
		 success: function(date){
			// var myjson=eval("("+date+")");
			// if(myjson.result){
			if(date.result==200){
				phonecurCount = phonecount;//设置button效果，开始计时
			    $("#btnEmailOrPhoneCode").attr("onclick", "");
				$("#btnEmailOrPhoneCode").html("已发送");
				$(".tooltip").removeClass("zl_tr_td_hide");
				$("#tooltip_td").html("校验码已发出，请注意查收短信，<span>90</span>秒后可要求系统重新发送")
				phoneInterValObj = window.setInterval(SetPhoneRemainTime, 1000); //启动计时器，1秒执行一次
			}else if(date.result==500){
				showError('error_td','今天发送的验证码已到最多上线！稍后再试....');
				return;
			}else if(date.result==404){
				showError('error_td','用户不存在！');
				return;
			}else{
				showError('error_td','服务异常...发送失败！');
				return;
			}
			hideCode();
		 }
	 });
}

/*隐藏错误信息*/
function hiddenError(id){
	$("#"+id).html("");
}
	
/*回车提交*/
function keydownSubmit(event){
	e = event ? event :(window.event ? window.event : null);
	if(e.keyCode==13){
		pwdSubmit();
	}
}



/*提交*/
function pwdSubmit(){
	//邮箱
	var temp=$("#emailorphone").val();
	if(temp==""){;
		showError('error_td','手机/邮箱不可为空');
		return;
	}
    //验证码
    temp=$("#vercode").val();
    if(temp==""){
    	showError('error_td','请填写验证码');
		return;
    }
	temp=$("#emailorphone").val();
	var reg_email  = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g
	var reh_phone  = /^1[34578]\d{9}$/;
	if(!reg_email.test(temp)&&!reh_phone.test(temp)){
		showError('error_td',"用户名格式错误");
		return;
	}
    //提交
    // $("#form").submit();
	// var phone=$("#emailorphone").val();
	var paramter = $("#form");
	$.ajax({
		type: "post",
		dataType: "json",
		url: '/appuser/chkinfopwd',
		data: paramter.serialize(),
		success: function (datas) {       //data回调信息
			console.log(datas)
			if (datas.code==200){
				location.href='/appuser/nextpwd?phone='+temp;
			}else if (datas.code==400){
				showError('error_td','验证码错误');
				return;
			}else if (datas.code==404){
				showError('error_td','提交失败');
				return;
			}else if(datas.code==500){
				showError('error_td','验证超时请重新发送');
				return;
			}else{
				showError('error_td','发送失败');
				return;
			}
		}
	});
}


/*修改密码提交*/
function modSubmit(){
	//新密码
	var pwd=$("#pwd").val();
	if(pwd==""){
    	$("#mod_error").html("请输入密码");
		return;
    }
	if(pwd.length<6 || pwd.length>16){
    	$("#mod_error").html("密码只允许6-16个字符");
		return;
    }
	
	//确认密码
	var comfpwd=$("#comfpwd").val();
	if(comfpwd=="" || comfpwd!=pwd){
    	$("#mod_error").html("两次密码不一致");
		return;
    }
	
	//提交
	$("#modForm").submit();
}

	function hideError(id,txt){
	   var node=$("#"+id);
	   node.html(txt);
	   node.hide();
	}

	function showError(id,txt){
	   var node=$("#"+id);
	   node.html(txt);
	   node.show();
	}

	/* 获取验证码*/
	function getEmailOrPhoneCode(){
		var name=$("#emailorphone").val();
		if(name!=""){
			if(isEmail(name)){
				getEmailCode(name);
			}
			else if(isPhone(name)){
				toShowCode(name);
			}else{
				 showError('error_td','手机/邮箱格式错误');
			     return;
			}
		}else{
			showError('error_td','请填写手机号或邮箱');
		     return;
		}
	}
	/*/!*验证手机号是否存在*!/
	function isValPhone(temp){
		 $.ajax({ 
				url: "/appuser/isPhoneExistpwd?phone="+temp,
				type:"post",
				success: function(date){
//					 var myjson=eval("("+date+")");
				     if("true"==date){
				    	   getPhoneCode();
						   return;
					 }else{
						   showError('error_td','此手机号不存在');
						   hideCode();
						   return;
					 }
				}
		 });
	}*/
	/*/!*验证Email是否存在*!/
	function isValEmail(temp){
		 $.ajax({ 
				url: "/appuser/isEmailExistpwd?email="+temp,
				type:"post",
				success: function(date){
				     if("true"==date){
				    	    getEmailCode();
						    return;
					 }else{
						   showError('error_td','此邮箱不存在');
						   return;
					 }
				}
		 });
	}*/
	
	function getNumCode(){
		var img = document.getElementById("numVerCode");
		img.src="/appuser/code?"+new Date().getTime();
	}

	function toShowCode(value){
		$.ajax({ 
			url: "/appuser/booleanCodepwd?key="+value,
			type:"post",
			success: function(data){
				if(true==data){
					$("#numcode").val("");
					getNumCode();//获取验证码
					$(".Aleabg").show();
					var wid = $(".POP-yzm").width();
					var Dwid = $(document).width();
					var left = (Dwid - wid) / 2;
					$(".POP-yzm").css("left", left);
					var hit = $(".POP-yzm").height();
					var Dhit = $(window).height();
					var top = (Dhit - hit) / 2;
					$(".POP-yzm").css("top", top);
					$(".POP-yzm").show();
				}else{
					getPhoneCode();
				}
			}
	   });
	}
	
	
	function hideCode(){
		$(".Aleabg").hide();
		var wid = $(".POP-yzm").width();
		var Dwid = $(document).width();
		var left = (Dwid - wid) / 2;
		$(".POP-yzm").css("left", left);
		var hit = $(".POP-yzm").height();
		var Dhit = $(window).height();
		var top = (Dhit - hit) / 2;
		$(".POP-yzm").css("top", top);
		$(".POP-yzm").hide();
	}
	function doGetPhoneCode(){
		var phone=$("#emailorphone").val();
		var code=$("#numcode").val();
		isValPhone(phone);
	}



