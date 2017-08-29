package com.guojing.boot.service;

import com.guojing.boot.entity.TbUser;
import com.guojing.boot.entity.TbUserRole;
import com.guojing.boot.interf.TbUserRepository;
import com.guojing.boot.interf.UserRoleRepository;
import com.guojing.boot.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xingsen on 15/1/30.
 */
@Service
@Transactional
public class SecurityService {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	@Autowired
	TbUserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;

	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {

		TbUser tbUser = userRepository.findByLoginName(loginName);

		if (tbUser == null) {
			throw new UsernameNotFoundException(String.format("用户名%s不存在", loginName));
		}

		if (!tbUser.getLoginName().toLowerCase().equals("admin") && !tbUser.getLoginName().toLowerCase().contains("itadmin")) {
			Long day = 0L;
			Date nowlongintime;
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DATE_FORMAT.format(new Date());
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		boolean isAdmin = false;
		if (tbUser.getLoginName().toLowerCase().equals("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
			authorities.add(new SimpleGrantedAuthority("ROLE_manager"));
			isAdmin = true;
		} else {
			List<TbUserRole> userRoles = userRoleRepository
					.findUserId(loginName);
			for (TbUserRole tbUserRole : userRoles) {
				if (tbUserRole.getRole().getName().toLowerCase().equals("admin")) {
					authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
					isAdmin = true;
					//break;
				} else {
					authorities.add(new SimpleGrantedAuthority(tbUserRole.getRole().getName()));
				}
			}
			authorities.add(new SimpleGrantedAuthority("ROLE_manager"));
		}
		UserDetailsAdminServiceImpl userDetailsService = (UserDetailsAdminServiceImpl) SpringContextUtil.getBean("userDetailsAdminServiceImpl");
		UserDetailsAdminServiceImpl.NiceNameUser returnUser = userDetailsService.initNiceNameUser(tbUser, authorities);

		/*// menu
		List<TbMenu> parentMenu = new ArrayList<>();
		Map<Long, List<TbMenu>> subMenu = new HashMap<>();
		if (isAdmin) {
			QTbMenu qTbMenu = QTbMenu.tbMenu;
			parentMenu = menuRepository.from().where((qTbMenu.parent.isNull().or(qTbMenu.parent.id.eq(58L))).and(qTbMenu.channel.eq(0))).list(qTbMenu);
			for (int i = 0; i < parentMenu.size(); i++) {
				subMenu.put(parentMenu.get(i).getId(),
						menuRepository.from()
								.where(qTbMenu.parent.id.isNotNull().and(
										qTbMenu.parent.id.eq(parentMenu.get(i).getId())).and(qTbMenu.channel.eq(0))).list(qTbMenu));
			}
		} else {
			// 先根据用户id查找用户的角色
			QTbUserRole query = QTbUserRole.tbUserRole;
			QTbRoleMenu qTbRoleMenu = QTbRoleMenu.tbRoleMenu;
			List<TbRole> roles = userRoleRepository.from()
					.where(query.user.id.eq(tbUser.getId()))
					.orderBy(query.role.id.asc()).list(query.role);
			// 遍历用户所属角色 查找角色所对应的菜单
			HashSet<TbMenu> menus = new HashSet<>();
			for (TbRole role : roles) {
				List<TbMenu> menuList = roleMenuRepository.from()
						.where(qTbRoleMenu.role.id.eq(role.getId()).and(qTbRoleMenu.menu.channel.eq(0)))
						.orderBy(qTbRoleMenu.menu.id.asc())
						.list(qTbRoleMenu.menu);
				if (menuList != null && menuList.size() != 0) {
					menus.addAll(menuList);
				}
			}
			// 把菜单拆成 父菜单和子菜单两个列表
			for (TbMenu menu : menus) {
				if (menu.getParent() == null
						&& !parentMenu.contains(menu.getParent())) {
					parentMenu.add(menu);
				} else {
					if (!subMenu.containsKey(menu.getParent().getId())) {
						subMenu.put(menu.getParent().getId(),
								new ArrayList<TbMenu>());
					}
					if (!parentMenu.contains(menu.getParent())) {
						parentMenu.add(menu.getParent());
					}
					subMenu.get(menu.getParent().getId()).add(menu);
				}
			}
		}
		// 定义比较器规则：按菜单id排序
		Comparator<? super TbMenu> cmp = new Comparator<TbMenu>() {
			@Override
			public int compare(TbMenu menu1, TbMenu menu2) {
				return (int) (menu1.getId() - menu2.getId());
			}
		};



		// 定义比较器规则：按菜单OrderNum降序排序
		Comparator<? super TbMenu> cmp2 = new Comparator<TbMenu>() {
			@Override
			public int compare(TbMenu menu1, TbMenu menu2) {
				if (menu2.getOrderNum() != null && menu1.getOrderNum() != null) {
					return menu2.getOrderNum() - menu1.getOrderNum();
				}
				return 0;
			}
		};

		// 定义比较器规则：按菜单OrderNum升序排序
		Comparator<? super TbMenu> cmp3 = new Comparator<TbMenu>() {
			@Override
			public int compare(TbMenu menu1, TbMenu menu2) {
				if (menu1.getOrderNum() != null && menu2.getOrderNum() != null) {
					return menu1.getOrderNum() - menu2.getOrderNum();
				}
				return 0;
			}
		};


		// 将父菜单排序
		Collections.sort(parentMenu, cmp3);
		// 将子菜单排序
		for (TbMenu menu : parentMenu) {
			if (subMenu.get(menu.getId()) != null && !subMenu.get(menu.getId()).isEmpty()) {
				Collections.sort(subMenu.get(menu.getId()), cmp3);
			}
		}

		for(int i=0;i<parentMenu.size();i++ ) {
			deleteZpMenu(parentMenu.get(i),subMenu);

			if(parentMenu.get(i).getTitle().equals("采购模块统计") || parentMenu.get(i).getTitle().equals("销售模块统计")) {
				parentMenu.remove(i);
				--i;
			}
		}


		returnUser.setParentMenu(new ArrayList<TbMenu>(
				new LinkedHashSet<TbMenu>(parentMenu)));
		returnUser.setSubMenu(subMenu);*/
		return returnUser;
	}

}
