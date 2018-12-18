var cacheScripts = [];
var scriptMaps = {};

var DM = {
    ready: function (objects, callback) {
        var loads_count = 0;
        $.each(objects, function (i, js) {
            if (cacheScripts.indexOf(js) != -1) {
                loads_count++;
                if (loads_count == objects.length) {
                    if (typeof(callback) == 'function') {
                        callback();
                        return false;
                    }
                }
                return true;
            }
            else {
                cacheScripts.push(js);
                var script = document.createElement('script');
                script.src = js;
                // script.aysnc = false;
                script.type = 'text/javascript';
                script.onload = function () {
                    loads_count++;
                    if (loads_count == objects.length) {
                        if (typeof(callback) == 'function') {
                            callback();
                            return false;
                        }
                    }
                }
                document.body.appendChild(script);
            }
        });
    },
    post: function (href, params, callback, async) {
        var that = this;
        async = async == undefined ? true : async;
        $.ajax({
            url: href,
            data: params,
            async: async,
            type: "post",
            dataType: "json",
            //headers : {
            //    "Content-Type" : "application/json;charset=utf-8"
            //},
            //contentType:"application/json;charset=utf-8",
            success: function (ret) {
                if (callback != null) {
                    if (ret != null && typeof(ret.data) != 'undefined' && ret.data != null) {
                        callback(ret);
                    } else if (ret.status == -1) {
                        console.log('您的请求出现了错误,信息为:' + ret.message);
                        callback(ret);
                    } else {
                        callback(ret);
                    }
                    callback == null;
                }

            },
            error: function (resp, status, xhr) {
                if (resp.status == 403) {
                    //session timeout or no privileges
                    top.location.href = '/login?expired';
                }
                //callback(null);
                if (callback != null) {
                    //callback(null);

                    var data = {success:false};
                    if(resp.responseJSON != null) {
                        data = $.extend(resp.responseJSON,data);
                    } else {
                        data = $.extend(data,{message:resp.responseText});
                    }
                    callback(data);
                }
                try {
                    $.messager.progress('close');
                } catch (e) {
                }
            }
        });
    },
    /*弹出层*/
    /*
        参数解释：
        title   标题
        url     请求的url
        id      需要操作的数据id
        w       弹出层宽度（缺省调默认值）
        h       弹出层高度（缺省调默认值）
    */
    xAdminShow(title,url,w,h){
        if (title == null || title == '') {
            title=false;
        };
        if (url == null || url == '') {
            url="404.html";
        };
        if (w == null || w == '') {
            w=($(window).width()*0.9);
        };
        if (h == null || h == '') {
            h=($(window).height() - 50);
        };
        layer.open({
            type: 2,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade:0.4,
            title: title,
            content: url
        });
    }
}