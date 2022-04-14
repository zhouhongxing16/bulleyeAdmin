package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.ValidateCodeUtils;
import com.chris.bulleyeadmin.system.dto.AccountDto;
import com.chris.bulleyeadmin.system.mapper.AccountMapper;
import com.chris.bulleyeadmin.system.pojo.Account;
import com.chris.bulleyeadmin.system.pojo.Logger;
import com.chris.bulleyeadmin.system.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-11 11:52
 */
@Service
public class AccountService extends BaseService<Account> {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BaseMapper<Account> getMapper() {
        return accountMapper;
    }

    public AccountDto getAccountByUserName(String userName) {
        return accountMapper.getAccountByUserName(userName);
    }

    public AccountDto getAccountByStaffMobile(String userName) {
        return accountMapper.getAccountByStaffMobile(userName);
    }


    public JsonResult changePassword(Map<String, String> map) {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        User user = AuthUtil.getCurrentUser();
        if (user == null) {
            result.setMessage("未登录，非法访问！");
        } else if (map.get("oldPassword") == null) {
            result.setMessage("旧密码不能为空");
        } else if (map.get("newPassword") == null) {
            result.setMessage("新密码不能为空");
        } else {
            String oldPassword = map.get("oldPassword");
            AccountDto accountDto = accountMapper.getById(user.getId());
            if (accountDto != null) {
                if (!PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(oldPassword, accountDto.getPassword())) {
                    result.setMessage("密码验证错误！");
                } else {
                    String newPwd = map.get("newPassword");
                    newPwd = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(newPwd);
                    Account account = new Account();
                    account.setId(accountDto.getId());
                    account.setPassword(newPwd);
                    int count = accountMapper.updateByPrimaryKeySelective(account);
                    if (count > 0) {
                        result.setSuccess(true);
                        result.setMessage("密码修改成功！");
                        result.setStatus(HttpStatus.OK.value());
                    }

                }
            } else {
                result.setMessage("非法请求！");
            }


        }
        return result;
    }


    @Transactional(rollbackFor = Exception.class)
    public JsonResult forgetPassword(Map<String, String> params) {
        String validateCode = params.get("validateCode");
        String mobile = params.get("mobile");
        String password = params.get("password");
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        if (StringUtils.isEmpty(mobile)) {
            result.setMessage("手机号不能为空！");
        } else if (StringUtils.isEmpty(password)) {
            result.setMessage("密码不能为空！");
        } else if (StringUtils.isEmpty(validateCode)) {
            result.setMessage("验证码不能为空！");
        } else if (!validateCode.equals(ValidateCodeUtils.getValidateCode(mobile))) {
            result.setMessage("验证码错误！");
        } else {

            Account account = accountMapper.getAccountByStaffMobile(mobile);
            if (account != null) {
                String pwd = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
                Account a = new Account();
                a.setId(account.getId());
                a.setPassword(pwd);

                accountMapper.updateByPrimaryKeySelective(a);
                result.setSuccess(true);
                result.setMessage("密码修改成功！");
                //验证后删除验证码
                ValidateCodeUtils.removeValidateCode();

            } else {
                result.setMessage("数据异常，非法请求！");
            }
            if (!result.isSuccess()) {
                Logger.debug("数据有误，执行手动回滚！");
                //手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        result.setStatus(HttpStatus.OK.value());
        return result;

    }

    public JsonResult initPassword(String accountId) {

        JsonResult result = new JsonResult();
        result.setSuccess(false);
        Account account = accountMapper.getById(accountId);
        if (account != null) {
            String pwd = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(account.getUsername());
            Account a = new Account();
            a.setId(account.getId());
            a.setPassword(pwd);
            accountMapper.updateByPrimaryKeySelective(a);
            result.setSuccess(true);
            result.setMessage("密码修改成功！");
        } else {
            result.setMessage("数据异常，非法请求！");
        }
        result.setStatus(HttpStatus.OK.value());
        return result;

    }
}
