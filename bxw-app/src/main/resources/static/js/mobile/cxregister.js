		//获取验证码
		var num=90;
		function clock(){	
			num--;
			$('.g_btn').html('重新获取('+num+'s)')			
			if(num <= 0){
				clearInterval(setTime);
				$("#getVcode").attr("onclick","getVcode();"); 	
				$('#getVcode').removeClass('gclick');
				$('.g_btn').html('获取验证码');
			}
		}
		
		   var timeCount=3;
		   function timedCount(){
		       var node = $("#timeCount").html(timeCount);
			   timeCount = timeCount-1;
			   if(timeCount > -1){
			      setTimeout("timedCount()",1000);
			   }else{
			      toTarget();
			   }
		   }
		   function toTarget(){
			   var refer = $("#refer").val();
			   var value = $("#phone").val();
			   if(refer == ""){
				   location.href =  "/"+value;
			   }else{
				   location.href =  "/"+refer;
			   }
		   }
		
		function getVcode(){
			//验证手机号
			var value = $("#phone").val();
			if(value == ""){
				$("#error_phone").html("<span></span>手机号不能为空");
			}else{
				if(unameIsPhone(value)){
					if(!isValPhone(value)){
					
						$.ajax({ 
						 url: "/register/coderegister?phone="+value+"&needcode=false",
						 type:"get",
						 dataType: "json",
						 success: function(date){
							// var myjson=eval("("+date+")");
							if(date.code==200){
								setTime = setInterval('clock()',1000)
								$("#getVcode").addClass('gclick');
								$("#getVcode").removeAttr("onclick");
								$("#vcode").focus();
								$('.g_btn').html('重新获取(90s)')
								num = 90;
							}else if (date.code==500){
								$("#error_phone").html("<span></span>服务器异常发送失败！");
							}
						}});
					
					

					}
				}else{
					$("#error_phone").html("<span></span>手机号格式不正确");
				}
			}
		}

		
		
		
		$('.g_r_name input').on('click',function(){
			//$('.g_r_name').find('span').show();
		})
		$('.g_r_pass input').on('click',function(){
			$('.g_r_pass').find('em').show();
			$('.g_r_pass').find('em').css('right',0);
		})
		$('.g_r_pass em').on('click',function(){
			$('.g_r_pass').find('input').attr('type','text');
		})
		function verifyPassword(node){
			 var value = node.value;
			 if(value != ""){
			 
			 	if (value.indexOf(" ") == -1) {
			 			if(value.length >16 || value.length < 8){
			 				$("#span2").removeClass("g_span_pass");
			 				$("#span2").addClass("g_span_nopass");
			 				var v = checkPass(value);
			 				if(v > 1){
			 		       		$("#span3").removeClass("g_span_nopass");
			 		       		$("#span3").addClass("g_span_pass");
				 		       }else{
				 					$("#span3").removeClass("g_span_pass");
				 					$("#span3").addClass("g_span_nopass");
				 		       }
			 			}else{
			 			   $("#span2").removeClass("g_span_nopass");
			 		       $("#span2").addClass("g_span_pass");
			 		       
			 		       var v = checkPass(value);
			 		       if(v > 1){
			 		       		$("#span3").removeClass("g_span_nopass");
			 		       		$("#span3").addClass("g_span_pass");
			 		       }else{
			 					$("#span3").removeClass("g_span_pass");
			 					$("#span3").addClass("g_span_nopass");
			 		       }
			 			}
			 	}else{
			 		$("#span1").removeClass("g_span_pass");
			 		$("#span1").addClass("g_span_nopass");
			 	}
			 }else{
			 		$("#span1").removeClass("g_span_nopass");
			 		$("#span1").addClass("g_span_pass");
			 }

		}
		
		function verifyRealname(node){
			var value = node.value;
			if(value == ""){
				$('.g_r_name').find('span').hide();
			}else{
				$('.g_r_name').find('span').show();
			}
		}
		
		function verifyPhone(node){
			var value = node.value;
			if(value == ""){
				$("#phone").addClass("error");
				$("#error_phone").html("<span></span>手机号不能为空");
			}else{
				if(unameIsPhone(value)){
					if(isValPhone(value)){
						$("#error_phone").html("");
					}
				}else{
					$("#phone").addClass("error");
					$("#error_phone").html("<span></span>手机号格式不正确");
				}
			}
		}
		function unameIsPhone(name){
			 var filter  = /^1[3456789]\d{9}$/;
			 return filter.test(name);
		}
		//验证手机是否存在
		function isValPhone(temp){
	 	$.ajax({ 
			url: "/register/phoneregister?phone="+temp,
			dataType: "json",
			type:"get",
			success: function(date){
				 // var myjson=eval("("+date+")");
			     if(date.code==200){
				 	    $("#error_phone").html("<span></span>手机号已注册");
				 	    $("#loginSkip").show();
				 	    timedCount();
				 }else{
					 $('.g_r_tel').find('span').show();
					   $("#error_phone").html("");
				 }
				 
				 
			}
		 });
	 }
	 function register(){
	 	var phone = $("#phone").val();
	 	var vcode = $("#vcode").val();
	 	var realname = $("#realname").val();
	 	var password = $("#password").val();
	 	var refer = $("#refer").val();
	 	
	 	
	 	var value = $("#phone").val();
		if(value == ""){
			//alert("手机号不能为空");
			return ;
		}else{
			if(!unameIsPhone(value)){
				//alert("手机号格式不正确");
				return ;
			}
		}
	 	
	 	//check
	 	if(vcode == ""){
	 		//alert("请输入验证码");
	 		return;
	 	}
	 	if(realname == ""){
	 		//alert("姓名不能为空");
			return ;
	 	}
	 	
	 	if(password.length > 16 || password.length < 8){
	 		//alert("密码长度为8-16");
			return ;
	 	}else{
	 		if (value.indexOf(" ") != -1) {
	 			//alert("密码不能包含空格");
			    return ;
	 		}else{
	 			var vl = checkPass(password);
	 			if(vl < 1){
		 			//alert("密码必须包含字母、数字、符号中至少两种");
					return ;
				}
	 		}
	 	}
		 // $("#form").submit();
	    var parmater = $("#form");
	 	$.ajax({
			type: "POST",
			dataType: "json",
			url: "/register/appuserregister",
			data: parmater.serialize(),
			success: function (date) {
				if (date.code==200){
					$("#div").show();
					setTimeout("toTarget()",3000);
				}else if (date.code==500){
					$("#error_vcode").html("<span></span>验证码超时");
				}else if (date.code ==400){
					$("#error_vcode").html("<span></span>验证码错误");
				}else{
					$("#error_vcode").html("<span></span>服务器异常");
				}
			}
		});
		 
	 }
	 function checkPass(s){   
        var ls = 0;   
        if(s.match(/([0-9])+/)){   
             ls++;     
        }   
        if(s.match(/([A-Za-z])+/)){   
             ls++;   
        }   
        if(s.match(/[^a-zA-Z0-9]+/)){   
           ls++;   
        }   
        return ls   
    } 
	    function verifyVcodeBlur(node){
	    	var value = node.value;
	    	if(value == ""){
	    		$("#vcode").addClass("error");
	    		$("#error_vcode").html("<span></span>验证码不能为空");
	    	}
	    }
	    function verifyVcodeFocus(node){
	    	var value = node.value;
	    	$("#error_vcode").html("");
	    	$("#vcode").removeClass("error");
	    }
	    function verifyPhoneFocus(node){
	    	$("#phone").removeAttr("readonly");
	    	$("#phone").removeClass("error");
	    	$("#error_phone").html("");
	    }
	    
	    function verifyRealnameBlur(node){
	    	var value = node.value;
	    	if(value == ""){
	    		$("#realname").addClass("error");
	    		$("#error_realname").html("<span></span>姓名不能为空");
	    	}else{
	    		$('.g_r_name').find('span').show();
	    	}
	    }
	    function verifyRealnameFocus(node){
	    	$("#realname").removeClass("error");
	    	$("#error_realname").html("");
	    }
	    //
	    function verifyPwdBlur(node){
	    	//判断密码 只显示错误的
	    	var value = node.value;
	    	//空格
	    	if (value.indexOf(" ") == -1) {
	    		$("#pwdli1").hide();
	    	}else{
	    		$("#span1").addClass("pwderror");
	    	}
	    	if(value.length >=8 && value.length <= 16){
	    		$("#pwdli2").hide();
	    	}else{
	    		$("#span2").addClass("pwderror");
	    	}
	    	var v = checkPass(value);
	       if(v > 1){
	       	$("#pwdli3").hide();
	       }else{
	    		$("#span3").addClass("pwderror");
	    	}
	       if(v >1 && (value.length >=8 && value.length <= 16) && value.indexOf(" ") == -1){
	    	 $('.g_r_pass').find('em').css('right','40px');  
	       	 $('.g_r_pass').find('span').show();
	       }else{
	    		$("#password").addClass("error");
	       }
	    }
	    function verifyPwdFocus(node){
	    	$("#password").removeClass("error");
	    	$("#span1").removeClass("pwderror");
	    	$("#span2").removeClass("pwderror");
	    	$("#span3").removeClass("pwderror");
		    $("#pwdli1").show();
		    $("#pwdli2").show();
		    $("#pwdli3").show();
	    	$("#pwdtip").show();
	    }
	    
		function changeType(obj){
			var eyeClass = $(obj).attr('class');
			if(eyeClass != 'g_yee_open'){
				$(obj).addClass('g_yee_open');
				$("#password").attr('type','text');
			}else{
				$(obj).removeClass("g_yee_open");
				$("#password").attr('type','password');
			}
		}