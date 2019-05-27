       

        /*60秒倒计时*/
        var c1=document.getElementById('content1');
           var c2=document.getElementById('content2');
            var time_djs= document.getElementById('time_djs');
            var mm_login= document.getElementById('mm_login');
            var pic= document.getElementById('pic');
           var djs = time_djs.getElementsByTagName('i')[0];
           var bbb = time_djs.getElementsByTagName('b')[0];
           var timer;
            djs.onclick=function (){
                clearInterval(timer);
                djs.innerHTML = 60;
                time_djs.style.textAlign='left';
                bbb.style.display='block';
                djs.style.paddingLeft='.5rem';
                time_djs.style.width='9rem';
                time_djs.style.paddingRight='.5rem';

                   //定义倒计时的函数 
                function move(){
                    var old_djs = djs.innerHTML;
                   var new_djs = old_djs;
                   djs.innerHTML = new_djs-1;
                    if(djs.innerHTML<0){
                        bbb.style.display='none';
                        djs.style.paddingLeft='.5rem';
                        djs.innerHTML = '点击再次获取';
                        time_djs.style.textAlign='center';
                        clearInterval(timer);
                    }
               }
               
               timer =  setInterval(move,1000);
           }
            
           
            
                /*登录页面点击按钮转换登录方式*/
                 var onoff = true;
            pic.onclick=function (){
                if(onoff){
                    pic.setAttribute('src','img/true.png');
                    onoff=false;
                }
                else{
                    pic.setAttribute('src','img/false.png');
                    onoff=true;
                } 
            }
            
            mm_login.onclick=function (){
                if(onoff){
                    c1.style.display='none';
                     c2.style.display='block';
                    mm_login.innerHTML='密码修改';
                    onoff=false;
                }
                else{
                     c1.style.display='block';
                    c2.style.display='none';
                    mm_login.innerHTML='短信修改';
                    
                    onoff=true;
                }
            }
            
            
            /*出主页意外其他所有页面的返回上一步*/
           var back=document.getElementById('back');
            back.onclick=function (){
                    window.history.back(-1); 
                
            }
                 
            