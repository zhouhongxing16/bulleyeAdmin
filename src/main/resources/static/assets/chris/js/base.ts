// @ts-ignore
const cacheScripts:any = [],axios:any;

let DM = {
    post(url: string, data: any,callback) {
        return axios.post(url, data).then(function (response) {
            callback(response.data);
        }).catch(function (error) {
            callback(error);
            console.log("请求错误："+error);
        });
    },
    get(url: string, data: any,callback) {
        return axios.get(url, {
            params: data
        }).then(function (response) {
            callback(response.data);
        }).catch(function (error) {
            callback(error);
            console.log("请求错误："+error);
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
    xAdminShowIframe(title,url,w,h){
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
    },
    xAdminShowModal(title,url,w,h,func,that) {
        if (title == null || title == '') {
            title = false;
        }
        ;
        if (url == null || url == '') {
            url = "404.html";
        }
        ;
        if (w == null || w == '') {
            w = ($(window).width() * 0.5);
        }
        ;
        if (h == null || h == '') {
            h = ($(window).height() * 0.5);
        }
        ;
        DM.get(url, null, function (msg) {
            //页面层-自定义
            layer.open({
                id: 'staff-add',
                type: 1,
                area: [w + 'px', h + 'px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: title,
                content: msg,
                btn: ['保存', '取消'],
                yes: function (index, layero) {
                    console.log("执行点击事件");
                },
                btn2: function (index, layero) {
                    //按钮【按钮二】的回调

                    //return false 开启该代码可禁止点击该按钮关闭
                },
                success: function (index, layero) {
                    console.log(layero);
                    if (typeof func == 'function') {
                        func();
                    }
                }
            });
        })
    }
}