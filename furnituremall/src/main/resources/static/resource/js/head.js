$(function () {
    /*一级分类*/
    $.get("/mall/type/findOneType",function (result) {
            if (result.code == 200) {
                  var dd = "<li class='layui-nav-item layui-this'><a href='#'>首页</a></li>";
                $(result.data).each(function () {
                    dd += "<li class='layui-nav-item'><a href='/mall/type/findTowType/"+this.id+"'>" + this.typename + "</a></li>";
                });
                $("#nav_head").append(dd);
            }
        });
    });