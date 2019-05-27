$(function(){
    $("#btn_add2").click(function(){
        $("#upimg").trigger("click");
        $("#upimg").change(function(){
            var x = document.getElementById("upimg").files;
            //把获得的文件名放入text里面显示
            //$("#updateFile").val(x[0].name);
            $("#btn_add2").attr("style","display:none;");
        })
    });
    $("#btn_add3").click(function(){
        $("#upimg2").trigger("click");
        $("#upimg2").change(function(){
            var x = document.getElementById("upimg").files;
            //把获得的文件名放入text里面显示
            //$("#updateFile").val(x[0].name);
            $("#btn_add3").attr("style","display:none;");
        })
    });
    $("#btn_add4").click(function(){
        $("#upimg3").trigger("click");
        $("#upimg3").change(function(){
            //把获得的文件名放入text里面显示
            //$("#updateFile").val(x[0].name);
            $("#btn_add4").attr("style","display:none;");
        })

        /*var a=document.getElementById("file").files;
        alert(a)
        display: none
        */
    });


})


var Upload = (function(){
    var upimg = document.getElementById('upimg');
    var show  = document.getElementById('show');

    function init(){
        if(!(window.FileReader && window.File && window.FileList && window.Blob)){
            show.innerHTML = '您的浏览器不支持fileReader';
            upimg.setAttribute('disabled', 'disabled');
            return false;
        }
        handler();
    }

    function handler(){
        upimg.addEventListener('change', function(e){
            var files = this.files;
            if(files.length){
                checkFile(this.files);
            }
        });
    }

    function checkFile(files){
        if (files.length != 0) {
            //获取文件并用FileReader进行读取
            var html = '';
            var i = 0, j = show.childElementCount;
            var funcs = function(){
                if(files[i]){
                    var x = parseInt((i+j)/4)*250;
                    var y = ((i+j)%4)*250;
                    var reader = new FileReader();
                    if(!/image\/\w+/.test(files[i].type)){
                        show.innerHTML = "请确保文件为图像类型";
                        return false;
                    }
                    reader.onload = function(e) {
                        html += '<div class="item" style="top:'+x+'px; left:'+y+'px;text-align:center;"><img src="'+e.target.result+'" alt="img"></div>';
                        i++;
                        funcs(); // onload为异步调用
                    };
                    reader.readAsDataURL(files[i]);
                }else{
                    show.innerHTML += html;
                }
            }
            funcs();
        }
    }
    return {
        init : init
    }
})();
Upload.init();



var Upload2 = (function(){
    var upimg = document.getElementById('upimg2');
    var show  = document.getElementById('show2');

    function init(){
        if(!(window.FileReader && window.File && window.FileList && window.Blob)){
            show.innerHTML = '您的浏览器不支持fileReader';
            upimg.setAttribute('disabled', 'disabled');
            return false;
        }
        handler();
    }

    function handler(){
        upimg.addEventListener('change', function(e){
            var files = this.files;
            if(files.length){
                checkFile(this.files);
            }
        });
    }

    function checkFile(files){
        if (files.length != 0) {
            //获取文件并用FileReader进行读取
            var html = '';
            var i = 0, j = show.childElementCount;
            var funcs = function(){
                if(files[i]){
                    var x = parseInt((i+j)/4)*250;
                    var y = ((i+j)%4)*250;
                    var reader = new FileReader();
                    if(!/image\/\w+/.test(files[i].type)){
                        show.innerHTML = "请确保文件为图像类型";
                        return false;
                    }
                    reader.onload = function(e) {
                        html += '<div class="item" style="top:'+x+'px; left:'+y+'px; text-align:center;"><img src="'+e.target.result+'" alt="img"></div>';
                        i++;
                        funcs(); // onload为异步调用
                    };
                    reader.readAsDataURL(files[i]);
                }else{
                    show.innerHTML += html;
                }
            }
            funcs();
        }
    }
    return {
        init : init
    }
})();
Upload2.init();


var Upload3 = (function(){
    var upimg = document.getElementById('upimg3');
    var show  = document.getElementById('show3');

    function init(){
        if(!(window.FileReader && window.File && window.FileList && window.Blob)){
            show.innerHTML = '您的浏览器不支持fileReader';
            upimg.setAttribute('disabled', 'disabled');
            return false;
        }
        handler();
    }

    function handler(){
        upimg.addEventListener('change', function(e){
            var files = this.files;
            if(files.length){
                checkFile(this.files);
            }
        });
    }

    function checkFile(files){
        if (files.length != 0) {
            //获取文件并用FileReader进行读取
            var html = '';
            var i = 0, j = show.childElementCount;
            var funcs = function(){
                if(files[i]){
                    var x = parseInt((i+j)/4)*250;
                    var y = ((i+j)%4)*250;
                    var reader = new FileReader();
                    if(!/image\/\w+/.test(files[i].type)){
                        show.innerHTML = "请确保文件为图像类型";
                        return false;
                    }
                    reader.onload = function(e) {
                        html += '<div class="item" style="top:'+x+'px; left:'+y+'px; text-align:center; "><img src="'+e.target.result+'" alt="img"></div>';
                        i++;
                        funcs(); // onload为异步调用text-align:center;
                    };
                    reader.readAsDataURL(files[i]);
                }else{
                    show.innerHTML += html;
                }
            }
            funcs();
        }
    }
    return {
        init : init
    }
})();
Upload3.init();