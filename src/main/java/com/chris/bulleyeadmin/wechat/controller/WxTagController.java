package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxTag;
import com.chris.bulleyeadmin.wechat.service.WxMemberService;
import com.chris.bulleyeadmin.wechat.service.WxTagService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:18
 * @Description:
 */
@OperationLog("微信用户标签")
@RestController
@RequestMapping("/wxtag")
@AllArgsConstructor
public class WxTagController extends BaseController<WxTag> {

    @Autowired
    private WxTagService wxTagService;

    @Autowired
    private final WxMpService wxService;

    @Autowired
    private WxAccountMapper wxAccountMapper;

    @Autowired
    private WxMemberService wxMemberService;

    @Override
    public BaseService<WxTag> getService() {
        return wxTagService;
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    @Override
    public synchronized JsonResult create(@RequestBody WxTag obj) {
        //获取该公众号的标签数量，一个公众号最多可以创建100个标签

        List<WxTag> wxTags = wxTagService.selectAll();
        if (wxTags != null && wxTags.size() == 100) {
            return new JsonResult(false,null,"该公众号创建标签数量已达上限，最多100个",null, HttpStatus.OK.value());
        }

        //获取相关接口
        WxAccount queryAccount = new WxAccount();
        queryAccount.setSourceId(obj.getSourceId());

        WxAccount account = wxAccountMapper.selectOne(queryAccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());

        try {
            WxUserTag wxUserTag = wxService.getUserTagService().tagCreate(obj.getName());
            if (wxUserTag !=null && !StringUtils.isEmpty(wxUserTag.getId())) {
                obj.setTagId(wxUserTag.getId().toString());
                wxTagService.add(obj);
                return new JsonResult(true,null,"创建成功",null, HttpStatus.OK.value());
            }

        } catch (WxErrorException e) {
            e.printStackTrace();
            return new JsonResult(false,null,"创建失败",null, HttpStatus.OK.value());
        }

        return new JsonResult(false,null,"创建失败",null, HttpStatus.OK.value());
    }


    //更新
    @ApiOperation(value = "更新一条数据", notes = "更新一条数据")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("更新")
    @PostMapping("/update")
    public JsonResult update(@RequestBody WxTag obj) {
        //获取相关接口
        WxAccount queryAccount = new WxAccount();
        queryAccount.setSourceId(obj.getSourceId());

        WxAccount account = wxAccountMapper.selectOne(queryAccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());

        try {
            Boolean result = wxService.getUserTagService().tagUpdate(Long.parseLong(obj.getTagId()), obj.getName());
            if (result) {
                wxTagService.update(obj);
                return new JsonResult(true,null,"更新成功",null, HttpStatus.OK.value());
            } else {
                return new JsonResult(false,null,"更新失败",null, HttpStatus.OK.value());
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            return new JsonResult(false,null,"更新失败",null, HttpStatus.OK.value());
        }
    }

    //删除
    @ApiOperation(value = "根据ID删除一条数据", notes = "根据ID删除一条数据")
    @GetMapping("/delete/{id}")
    @OperationLog("删除")
    public JsonResult remove(@PathVariable String id) {
        WxTag wxTag = null;
        JsonResult result = wxTagService.getById(id);
        if (result.isSuccess()) {
            wxTag = (WxTag) result.getData();
        }

        //获取相关接口
        WxAccount queryAccount = new WxAccount();
        queryAccount.setSourceId(wxTag.getSourceId());

        WxAccount account = wxAccountMapper.selectOne(queryAccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());
        try {
            Boolean flag = wxService.getUserTagService().tagDelete(Long.parseLong(wxTag.getTagId()));
            if (flag) {
                wxTagService.deleteById(id);
                return new JsonResult(true,null,"删除成功",null, HttpStatus.OK.value());
            } else {
                return new JsonResult(false,null,"删除失败",null, HttpStatus.OK.value());
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            return new JsonResult(false,null,"删除失败",null, HttpStatus.OK.value());
        }
    }

    public JsonResult setMemberTag (@RequestBody Map<String, Object> params) {
        Long tagId = Long.parseLong(params.get("tagId").toString());
        String[] openIds =  params.get("openids").toString().split(",");

        WxTag wxTag = null;
        JsonResult result = wxTagService.getById(tagId.toString());
        if (result.isSuccess()) {
            wxTag = (WxTag) result.getData();
        }

        //获取相关接口
        WxAccount queryAccount = new WxAccount();
        queryAccount.setSourceId(wxTag.getSourceId());

        WxAccount account = wxAccountMapper.selectOne(queryAccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());

        try {
            boolean flag = wxService.getUserTagService().batchTagging(tagId, openIds);
            if (flag) {
                wxMemberService.setMemberTag(tagId, openIds);
            } else {
                return new JsonResult(false,null,"删除失败",null, HttpStatus.OK.value());
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return new JsonResult(true,null,"删除成功",null, HttpStatus.OK.value());
    }

}
