package com.baizhi.springb1.shiro;

import com.baizhi.springb1.dao.*;
import com.baizhi.springb1.entity.Admin;
import com.baizhi.springb1.entity.R_P;
import com.baizhi.springb1.entity.U_R;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private U_RMapper u_rMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private R_PMapper r_pMapper;

    @Autowired
    private PermissionMapper permissionMapper;



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = (String) authenticationToken.getPrincipal();
        Admin admin = new Admin();
        admin.setName(name);
        Admin admin1 = adminMapper.selectOne(admin);
        AuthenticationInfo authenticationInfo = null;
        if (admin1 != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin1.getName(), admin1.getPassword(), ByteSource.Util.bytes(admin1.getSalt()), this.getName());
        }
        return authenticationInfo;
        //char[] credentials = (char[]) authenticationToken.getCredentials();
        //String password = String.valueOf(credentials);
        //System.out.println(password);

        /*AuthenticationInfo authenticationInfo = null;
        SqlSession sqlSession = MybatisUtil.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        //User user = mapper.query(name, password);
        User user = mapper.queryByName(name);
        if (user != null) {
            authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());

        }
        return authenticationInfo;*/

    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        Admin admin = new Admin();
        admin.setName(name);
        Admin admin1 = adminMapper.selectOne(admin);
        U_R u_r = new U_R();
        u_r.setUId(admin1.getId());
        List<U_R> uRs = u_rMapper.select(u_r);
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        if (uRs != null) {
            for (U_R uR : uRs) {
                list.add(String.valueOf(uR.getRId()));
                R_P r_p = new R_P();
                r_p.setRId(uR.getRId());
                List<R_P> r_ps = r_pMapper.select(r_p);
                if (r_ps != null) {
                    for (R_P rP : r_ps) {
                        list.add(String.valueOf(rP.getPId()));
                    }
                } else {
                    return authorizationInfo;
                }
            }
            authorizationInfo.addStringPermissions(list2);
            authorizationInfo.addRoles(list);
            return authorizationInfo;
        }else {
            return authorizationInfo;
        }
        /*if (name.equals("zhang3")){
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("user:delete");
            authorizationInfo.addStringPermission("user:query");
            authorizationInfo.addStringPermission("user:update");

        }*/
        //return authorizationInfo;
    }
}
