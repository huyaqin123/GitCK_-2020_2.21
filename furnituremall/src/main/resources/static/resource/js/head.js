$(function () {
    $.get("/mall/type/findOneType",function (result) {
            if (result.code == 200) {
                var dd="";
                $(result.data).each(function () {
                    if (this.id == 1) {
                        dd += "<li class='layui-nav-item layui-this'><a href='#'>" + this.typename + "</a></li>";
                    } else {
                        dd += "<li class='layui-nav-item'><a href='/mall/type/findTowType/"+this.id+"'>" + this.typename + "</a></li>";
                    }
                });
                $("#nav_head").append(dd);
            }
        });
    });

    function findThreeType(pid) {
        $("#nav_type").html("");
        $.get("/mall/type/findThreeType/",{"pid":pid},function (result) {
            if (result.code == 200) {
                var dd="<li class='active'>全部</li>";
                $(result.data).each(function () {
                    dd +="<li>" + this.typeName + "</li>";
                });
                $("#nav_type").append(dd);
            }
        });
    }