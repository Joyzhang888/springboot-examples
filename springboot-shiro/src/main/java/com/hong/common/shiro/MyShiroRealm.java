package com.hong.common.shiro;


import com.hong.domain.Permission;
import com.hong.domain.Role;
import com.hong.domain.User;
import com.hong.service.PermissionService;
import com.hong.service.RoleService;
import com.hong.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hong
 * @version v1.1
 * @ClassName: MyShiroRealm
 * @Description: (身份校验和授权Realm)
 * @date 2017/5/6
 */
public class MyShiroRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    /**
     * shiro 认证方法.
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("MyShiroRealm.doGetAuthorizationInfo()...");

        // 把这个类型的authenticationToken 转换成UsernamePasswordToken 。
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.findUserByName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(token.getUsername(), user.getPassword(), ByteSource.Util.bytes(token.getUsername()), getName());
        }

        return null;
    }


    /**
     * shiro 授权方法.
     *
     * @param principals
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //1. 从PrincipalCollection 中获取登陆用户的信息
        Object principal = principals.getPrimaryPrincipal();

        User user = userService.findUserByName((String) principal);

        if (user != null) {
            //2. 利用登陆用户信息来查询当前用户的角色或者权限信息
            Set<String> roles = new HashSet<>();
            Set<String> permissions = new HashSet<>();

            //获取角色名称集合
            List<Role> roleList = roleService.getRoleByUserId(user.getId());
            roleList.forEach(role -> roles.add(role.getRolename()));

            //获取对应权限名称集合
            List<Permission> permissionList = permissionService.getPermissionByUserId(user.getId());
            permissionList.forEach(permission -> permissions.add(permission.getPermissionName()));

            //3. 创建SimpleAuthorizationInfo ,并设置其roles、permissions 属性
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //设置角色
            info.setRoles(roles);
            //设置权限
            info.setStringPermissions(permissions);

            return info;
        }
        return null;
    }
}
