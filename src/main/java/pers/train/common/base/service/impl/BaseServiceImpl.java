package pers.train.common.base.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import pers.train.admin.dao.SecurityUserMapper;
import pers.train.common.base.dao.BaseMapper;
import pers.train.common.base.service.BaseService;




/**
 * 基类Service 用于动态获取泛型中的实体类信息<p>
 * 该基类通过实现BaseService接口，然后在其实现方法中调用BaseMapper中的方法实现功能<p>
 * 通过set注入将BaseMapper注入到该基类中<p>
 * 在该基类的构造方法中通过反射获取该基类的泛型对应的实体类，即传入的pojo对象，由于Mybatis的机制，
 * 根据相应的pojo,获取上下文中对应的mapper,然后将此pojo类名首字母小写，再与Mapper字符串拼接，
 * 组成新的xxxMapper。子类必须继承该实现类。<p>
 * 
 * 该类需要注意：
 * <ul>
 *    <li>在该基类中，必须注入所有要用到的xxxMapper，类型为接口，否则报错</li>
 *    <li>其他内容不可乱动</li>
 * </ul>
 * @author mingshan
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T>{

	private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	
	@SuppressWarnings("rawtypes")
	private Class clazz=null;
	
    
    private BaseMapper<T> baseMapper;
 	public void setBaseMapper(BaseMapper<T> baseMapper) {
 		this.baseMapper = baseMapper;
 	}
	
 	@Autowired
 	private SecurityUserMapper securityUserMapper;
 	
 	
    public  BaseServiceImpl(){
    	 //通过反射机制获取子类传递过来的实体类型信息
    	 ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
    	 clazz = (Class) type.getActualTypeArguments()[0];
     }
     
 	@PostConstruct
	public void init() throws Exception{
		
		// 根据相应的clazz,获取上下文中对应的mapper
		// 1: 获取相应的类名称
		String clazzName = clazz.getSimpleName();	    

		System.out.println("clazzName = " + clazzName);
		// 2:SecurityUser -> securityUser  -> securityUserMapper
		String clazzDaoName = clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "Mapper";//toLowerCase首字母小写

		System.out.println("clazzDaoName = " + clazzDaoName);
		// 3: 通过clazzDaoName获取相应 Field字段    this.getClass().getSuperclass():获取到相应BaseServiceImpl
		Field daoNameField = this.getClass().getSuperclass().getDeclaredField(clazzDaoName);		
	    System.out.println("this.getClass().getSuperclass() = "+this.getClass().getSuperclass());
		System.out.println("daoNameField = " + daoNameField);
		Object object = daoNameField.get(this);		

		System.out.println("object = " + object);
		// 4: 获取baseMapper 的字段信息
		Field baseDaoNameField = this.getClass().getSuperclass().getDeclaredField("baseMapper");
		baseDaoNameField.set(this, object);
		
	}	
     
	@Override
	public T findById(int id) {
		// TODO Auto-generated method stub
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return baseMapper.selectAll();
	}

	@Override
	public int insert(T model) {
		// TODO Auto-generated method stub
		return baseMapper.insert(model);
	}

	@Override
	public int insertSelective(T model) {
		// TODO Auto-generated method stub
		return baseMapper.insertSelective(model);
	}

	@Override
	public int update(T model) {
		// TODO Auto-generated method stub
		return baseMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public T selectByUniqueFiled(T t) {
		// TODO Auto-generated method stub
		return baseMapper.selectByUniqueFiled(t);
	}

	@Override
	public List<T> findByPage(Map map) {
		// TODO Auto-generated method stub
		return baseMapper.findByPage(map);
	}

}
