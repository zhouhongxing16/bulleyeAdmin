layui.config({ base: './static/' }).extend({ lau: 'lau/lau' }).use(['lau'], function () {
    var lau = layui.lau,
        layer = layui.layer,
        $ = layui.$;

    //监听事件,这个不一定要用lau-event,可以自己写
    $(document).on('click', '[lau-event]', function () {
        var _this = $(this);
        switch (_this.attr('lau-event')) {
            case "sideMenu0":
                lau.sideMenuChange(0);
                break;
            case "sideMenu1":
                lau.sideMenuChange(1);
                break;
            case "reload":
                lau.reload();
                break;
        }
    });

    
});

function ChangePass() {
    var html = '<fieldset class="layui-elem-field layui-field-title">';
    html += '<legend>修改密码</legend>';
    html += '</fieldset>';
    html += '<div class="layui-form-item">';
    html += '<label class="layui-form-label">输入原密码</label>';
    html += '<div class="layui-input-inline">';
    html += '<input type="password" id="PasswordOld" placeholder="请输入原密码" autocomplete="off" class="layui-input">';
    html += '</div>';
    html += '</div>';

    html += '<div class="layui-form-item">';
    html += '<label class="layui-form-label">输入新密码</label>';
    html += '<div class="layui-input-inline">';
    html += '<input type="password" id="Password" placeholder="4-20为且只能是字母或数字" autocomplete="off" class="layui-input">';
    html += '</div>';
    html += '</div>';

    html += '<div class="layui-form-item">';
    html += '<label class="layui-form-label">再输入密码</label>';
    html += '<div class="layui-input-inline">';
    html += '<input type="password" id="Password2" placeholder="再次输入密码" autocomplete="off" class="layui-input">';
    html += '</div>';
    html += '</div>';

    html += '<div class="layui-form-item" style="text-align: center;">';
    html += '<button class="layui-btn layui-btn-sm layui-btn-normal" onclick="change()">&nbsp;&nbsp;确&nbsp;&nbsp;认&nbsp;&nbsp;</button>';
    html += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    html += '<button class="layui-btn layui-btn-sm" onclick="javascript:layer.closeAll();">&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;</button>';
    html += '</div>';
    layer.open({
        type: 1,
        area: ['330px', '310px'],
        fixed: false, //不固定
        maxmin: false,
        content: html
    });
}

function change() {
    if ($("#PasswordOld").val().length == 0) {
        layer.msg('请输入原密码');
        $('#PasswordOld').focus();
        return false;
    }

    if ($("#Password").val().length == 0) {
        layer.msg('请输入新密码');
        $('#Password').focus();
        return false;
    }

    if ($("#Password2").val().length == 0) {
        layer.msg('请再次输入');
        $('#Password2').focus();
        return false;
    }

    //checkPassWord2
    if ($("#Password").val() != $("#Password2").val()) {
        layer.msg('两次密码不一致');
        $("#Password2").focus();
        return false;
    }

    //checkPassReg
    var reg = /^[0-9A-Za-z]{4,20}$/;
    if (!reg.test($("#Password").val())) {
        layer.msg('密码不符合要求');
        $("#Password").focus();
        return false;
    }
}