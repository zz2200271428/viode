var cookieName = "bxwcookie";
//搜索栏
$("#search").click(function () {
    $(".search-mask").css("display", "block")
    $(".m-header").addClass("in-search");
    $.post('/search/keyword', function (d) {
        var html = '';
        for (var i = 0; i < d.length; i++) {
            html += '<li class="m-search-page__hotitem f-border-1px ck" >' + '<a href="javascript:void(0)" class="m-search-page__hotitem__link">' + d[i].content + '</a></li>';
        }
        $("#sss").html(html);
        var arr = getCookie().values;
        $("#ssss").html(getHtml(arr));
        //重新渲染
        //form.render("section");
    });
})

function getHtml(arr) {
    var html = '';
    for (var i = 0; i < arr.length; i++) {
        html += '<li class="m-search-page__hotitem f-border-1px ck" >' + '<a href="javascript:void(0)" class="m-search-page__hotitem__link">' + arr[i] + '</a></li>';
    }
    return html;
}

$("#search").on("keypress", function (e) {
    var vv = $("#search").val().trim();
    if (e.keyCode == 13 && vv != '') {
        isCookieValue(vv);
        setCookie(vv);
        search(vv);
    }
});

$(".u-button").click(function () {
    $(".search-mask").css("display", "none")
    $(".m-header").removeClass("in-search");
})

// 请求服务器
$('body').on('click', '.ck', function () {
    var text = $(this).text();
    search(text);
})

function search(text) {
    window.location.href = "/search/history?text=" + text;
}

// 往cookie中設值
function setCookie(value) {
    var time = new Date();
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = cookieName + time.getTime() + "=" + value + ";expires=" + exp.toGMTString();
}

// 判斷cookie中的值是否存在
function isCookieValue(value) {
    var cookie = getCookie();
    var values = cookie.values;
    var names = cookie.names;
    for (var i = 0; i < names.length; i++) {
        var name = names[i];
        var v = values[i];
        if (name.indexOf(cookieName) != -1) {
            if (v == value) {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                document.cookie = name + "=" + "" + ";expires=" + exp.toGMTString();
            }
        }
    }
}

// 读取cookies
function getCookie() {
    var names = [];
    var values = [];
    var cookie = {"names": names, "values": values};
    if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 1; i <= cookies.length; i++) {
            var tempArr = cookies[i - 1].split('=');
            var name = tempArr[0];
            var value = tempArr[1];
            if (name.indexOf(cookieName) != -1) {
                names.unshift(name);
                values.unshift(value);
            }
        }
    }
    return cookie;
}

$('body').on('click', '#clear', function () {
    var names = getCookie().names;
    for (var i = 0; i < names.length; i++) {
        var name = names[i];
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        document.cookie = name + "=" + "" + ";expires=" + exp.toGMTString();
    }
    $("#ssss").empty();
})
