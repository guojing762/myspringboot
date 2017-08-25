package com.guojing.boot.service;

import com.guojing.boot.entity.TbUser;
import com.guojing.boot.util.SpringContextUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by kinble on 2016/3/31.
 */
@Service
public class UserDetailsAdminServiceImpl implements UserDetailsService, Serializable {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SecurityService securityService = (SecurityService) SpringContextUtil.getBean("securityService");
        return securityService.loadUserByUsername(s);
    }

    public NiceNameUser initNiceNameUser(TbUser tbUser, List<GrantedAuthority> authorities) {
        return new NiceNameUser(tbUser,authorities);
    }

    public class NiceNameUser extends User implements Serializable {
        private final Long id;
        private String niceName;
        private String salt;
	
//		private List<TbMenu> parentMenu;
//        private Map<Long, List<TbMenu>> subMenu;
		public NiceNameUser(TbUser tbUser, List<GrantedAuthority> authorities) {
            super(tbUser.getLoginName(), tbUser.getPassword(), true, true,
                    true, true, authorities);
            this.niceName = tbUser.getUsername();
            this.id = tbUser.getId();
            if( tbUser.getSalt() != null){
            this.salt = tbUser.getSalt();
            }
        }
		
		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		public void setNiceName(String niceName) {
			this.niceName = niceName;
		}
			
        public String getNiceName() {
            return niceName;
        }

        public Long getId() {
            return id;
        }

//        public List<TbMenu> getParentMenu() {
//            return parentMenu;
//        }
//
//        public void setParentMenu(List<TbMenu> parentMenu) {
//            this.parentMenu = parentMenu;
//        }
//
//        public Map<Long, List<TbMenu>> getSubMenu() {
//            return subMenu;
//        }
//
//        public void setSubMenu(Map<Long, List<TbMenu>> subMenu) {
//            this.subMenu = subMenu;
//        }

        @Override
        public String toString() {
            return niceName;
        }
    }
}
