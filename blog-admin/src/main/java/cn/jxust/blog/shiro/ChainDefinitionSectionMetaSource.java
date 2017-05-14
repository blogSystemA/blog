package cn.jxust.blog.shiro;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.springframework.beans.factory.FactoryBean;


/**
 * 产生责任链，确定每个url的访问权限
 * 
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	public Section getObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
//	private static final Logger logger = getLogger(ChainDefinitionSectionMetaSource.class);
//	@Resource
//	//private ResourceService ResourceService;
//	//静态资源访问权限
//	private String filterChainDefinitions = null;
//
//	/**
//	 * 不对角色进行权限验证
//	 * 如需要则 permission = "roles[" + Resource.getResKey() + "]";
//	 */
//	public Ini.Section getObject() throws Exception {
//		Ini ini = new Ini();
//		//加载默认的url
//		ini.load(filterChainDefinitions);
//		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
//		//循环Resource的url,逐个添加到section中.section就是filterChainDefinitionMap,
//		//里面的键就是链接URL,值就是存在什么条件才能访问该链接
//		List<Resource> lists = ResourceService.selectResources(null);
//		for (Resource Resource : lists) {
//			// 构成permission字符串
//			if (StringUtils.isNotEmpty(Resource.getResUrl()) && StringUtils.isNotEmpty(Resource.getResKey())) {
//				String permission = "perms[" + Resource.getResKey() + "]";
//				logger.info("添加权限验证链接:{}",permission);
//				section.put(Resource.getResUrl() + "", permission);
//			}
//		}
//		//所有资源的访问权限，必须放在最后
//		section.put("/**", "authc,kickout,sysUser,user");
//		return section;
//	}
//
//	/**
//	 * 通过filterChainDefinitions对默认的url过滤定义
//	 * @param filterChainDefinitions 默认的url过滤定义
//	 */
//	public void setFilterChainDefinitions(String filterChainDefinitions) {
//		this.filterChainDefinitions = filterChainDefinitions;
//	}
//
//	public Class<?> getObjectType() {
//		return this.getClass();
//	}
//
//	public boolean isSingleton() {
//		return false;
//	}
}
