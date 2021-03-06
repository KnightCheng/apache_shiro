package com.example.apache_shiro;

import com.example.apache_shiro.model.Permission;
import com.example.apache_shiro.model.Role;
import com.example.apache_shiro.model.User;
import com.example.apache_shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    //授权授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user=(User)principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList=new ArrayList<>();
        List<String> roleNameList=new ArrayList<>();
        Set<Role> roleSet=user.getRoles();
        if(!CollectionUtils.isEmpty(roleSet)){
            for (Role role:
                 roleSet) {
                roleNameList.add(role.getName());
                Set<Permission> permissionSet=role.getPermissions();
                if(!CollectionUtils.isEmpty(permissionSet)){
                    for (Permission permission:
                         permissionSet) {
                        permissionList.add(permission.getCname());
                    }
                }

            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }


    //认证登陆
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;
        String username=usernamePasswordToken.getUsername();
        User user=userService.findByUserName(username);
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
