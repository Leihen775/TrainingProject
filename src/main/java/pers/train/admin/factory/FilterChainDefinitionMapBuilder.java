package pers.train.admin.factory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import pers.train.admin.po.SecurityResources;



/** 
 * 自定义FilterChainDefinitionMap  
 * 
 */  
public class FilterChainDefinitionMapBuilder {
	
	@Autowired  
	private ResourcesService resourcesService;	
	
	/** 
     * 由于在FilterChainDefinitionMap 中是需要顺序的，所以我们选择用LinkedHashMap 
     */  
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		//获取所有Resource  
        List<SecurityResources> list = resourcesService.findAll();
        //里面的键就是链接URL,值就是存在什么条件才能访问该链接  
        for (Iterator<SecurityResources> it = list.iterator(); it.hasNext();) {  
        	SecurityResources resource = it.next();  
	            //如果不为空值添加到section中  
	            if(!StringUtils.isEmpty(resource.getValue()) && !StringUtils.isEmpty(resource.getPermission())) {  
	                map.put(resource.getValue(),resource.getPermission());  
	            } 
        }
        
  
		map.put("/admin/**", "authc");
	
		return map;
	}
	
}
