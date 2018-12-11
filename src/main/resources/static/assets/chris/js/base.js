var cacheScripts = [];
var scriptMaps = {};
var DM = {
    ready: function (objects, callback) {
        var loads_count = 0;
        $.each(objects, function (i, js) {
            if (cacheScripts.indexOf(js) != -1) {
                loads_count++;
                if (loads_count == objects.length) {
                    if (typeof (callback) == 'function') {
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
                        if (typeof (callback) == 'function') {
                            callback();
                            return false;
                        }
                    }
                };
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
                    if (ret != null && typeof (ret.data) != 'undefined' && ret.data != null) {
                        callback(ret);
                    }
                    else if (ret.status == -1) {
                        console.log('您的请求出现了错误,信息为:' + ret.message);
                        callback(ret);
                    }
                    else {
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
                    var data = { success: false };
                    if (resp.responseJSON != null) {
                        data = $.extend(resp.responseJSON, data);
                    }
                    else {
                        data = $.extend(data, { message: resp.responseText });
                    }
                    callback(data);
                }
                try {
                    $.messager.progress('close');
                }
                catch (e) {
                }
            }
        });
    }
};
