
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


/*修改密码提交*/
function modSubmit(){
	//新密码
	var pwd=$("#password").val();
	if(pwd==""){
//    	$("#error_password").html("请输入密码");
    	showError('error_password','请输入密码');
		return;
    }
	if(pwd.length<6 || pwd.length>16){
//    	$("#error_password").html("密码只允许6-16个字符");
    	showError('error_password','密码只允许6-16个字符');
		return;
    }
	
	//确认密码
	var comfpwd=$("#password2").val();
	if(comfpwd=="" || comfpwd!=pwd){
//    	$("#error_password2").html("两次密码不一致");
    	showError('error_password2','两次密码不一致');
		return;
    }
	
	//提交
	// $("#form").submit();
	var paramter = $("#form");
	$.ajax({
		type: "post",
		dataType: "json",
		url: "/appuser/checkpwd",
		data: paramter.serialize(),
		success: function (datas) {
			console.log(datas);
			if (datas.code==200){
				location.href = '/appuser/successpwd'
			} else{
				showError('error_password2','两次密码不一致');
				return;
			}
		}
	})
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

	function checkPassword(node){
		var vl=node.value;
		if(vl==null || vl==""){
		        showError('error_password','密码不能为空');
		   		// removePwdLev();
		   		return;
		}else if(vl.length<5 || vl.length>16){
		  		showError('error_password','密码限定为6-16个字符');
		   		// removePwdLev3();
		   		return;
		}else if(vl==$("#uname").val()){
				showError('error_password','账号和密码不允许重复');
				// removePwdLev();
				return;
		}else {
			var temp = /^\d+$/;
			var temp1 = /^(?!\d+$)(?![a-zA-Z]+$)[a-zA-Z\d]+$/;
			if (temp.test(vl)){
				removePwdLev1();
				return;
			}
			if (temp1.test(vl)){
				removePwdLev2();
				return;
			}
			removePwdLev3();
			return;
		}
	}

	function comparePassword(node){
		var v1=node.value;
		var v2= $("#password").val();
		if(v2==null || v2==""){
			    showError('error_password2','请先输入密码');
		   		return;
		}else if(v1==null || v1==""){
		        showError('error_password2','请确认密码');
		   		return;
		}else if(v1!=v2){
				showError('error_password2','两次输入的密码不一致');
				return;
		}
	}

	function removePwdLev(){
		 hideError("passwordLev1");
		 $("#passwordLev2").removeClass();
	  	 $("#passwordLev2").addClass("progressouter");
	}
	function removePwdLev1(){
		     $('#passwordLev1').html('密码强度 一般');
			 $("#passwordLev2").removeClass();
			 $("#passwordLev2").addClass("levelone progressouter");
	}
	function removePwdLev2(){
		   $('#passwordLev1').html('密码强度 中');
		   $("#passwordLev2").removeClass();
		   $("#passwordLev2").addClass("leveltwo progressouter");
	}
	function removePwdLev3(){
		   $('#passwordLev1').html('密码强度 高');
		   $("#passwordLev2").removeClass();
		   $("#passwordLev2").addClass("levelthree progressouter");
	}
	function setPasswordLeve(node){
	   var vl=node.value;
	   if(vl==null || vl==""){
		    showError('error_password','密码不能为空');
	  		removePwdLev();
			return;
	   }else if(vl.length<5 || vl.length>15){
		   showError('error_password','密码限定为6-16个字符');
	  		removePwdLev();
	  		return;
		}else if(vl==$("#uname").val()){
				showError('error_password','账号和密码不允许重复');
				removePwdLev();
				return;
		}else if("123456"==vl){
			 showError('error_password','请重新设置密码');
			 removePwdLev();
		     return;
		}else{
		   hideError('error_password');
	       var temp=passwordComplexity(vl);
	       if(temp<10){
	    	   removePwdLev1();
	       }else if(temp<20){
	    	   removePwdLev2();
	       }else{
	    	   removePwdLev3();
	       }
	   }
	}
	function passwordComplexity(password) {
		var complex = 0;
		var length = password.length;
		var pre = '';
		var preType = 0;
		for (var i = 0; i < length; i++) {
			var cur = password.charAt(i);
			var curType = pwdGettype(password, i);
			if (preType != curType || !pwdIsregular(cur, pre, curType)) {
				complex += curType + pwdComplex(curType, preType);
			}
			pre = cur;
			preType = curType;
		}
		return complex;
	}
	function pwdGettype(str, i) {
		if (str.charCodeAt(i) >= 48 && str.charCodeAt(i) <= 57) {
			return 1;
		}
		else if(str.charCodeAt(i) >= 97 && str.charCodeAt(i) <= 122) {
			return 2;
		}
		else if(str.charCodeAt(i) >= 65 && str.charCodeAt(i) <= 90) {
			return 3;
		}

		return 4;
	}
	function pwdIsregular(cur, pre, type) {
		var curCode = cur.charCodeAt(0);
		var preCode = pre.charCodeAt(0);
		
		if(curCode - preCode == 0){
			return true;
		}
		
		if(type != 4 && (curCode - preCode == 1 || curCode - preCode == -1)){
			return true;
		}
		
		return false;
	}
	function pwdComplex(curType, preType){
		if(preType == 0 || curType == preType){
			return 0;
		}else if(curType == 4 || preType == 4){
			return 2;
		}else{
			return 1;
		}
	}



