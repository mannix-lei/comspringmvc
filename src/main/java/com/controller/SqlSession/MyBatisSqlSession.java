package com.controller.SqlSession;

import com.mapper.IBaseMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


/**
 * Created by Tellyes_worker on 2017/12/5/0005.
 */
public class MyBatisSqlSession {
    private SqlSession sqlSession;
    private Class mapperClass;
    private boolean isClose = false;
    private IBaseMapper baseMapper;
    public MyBatisSqlSession(Class mapperClass){
        this.mapperClass = mapperClass;
        sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();

        this.baseMapper = (IBaseMapper) sqlSession.getMapper(this.mapperClass);
    }
    private static MyBatisSqlSession instance;
    public static MyBatisSqlSession getInstance(Class mapperClass){
        instance = new MyBatisSqlSession(mapperClass);
        return instance;
    }

    /**
     * 获取mapper执行自定义操作
     * @return
     */
    public IBaseMapper getMapper(){
        return baseMapper;
    }

    public void commit(){
        sqlSession.commit();
    }
    public void close(){
        sqlSession.close();
        isClose=true;
    }

    /**
     * xml获取方法par[0]
     * @param args
     * @param <T>
     * @return
     */
    public <T>List<T> findAll(Object ...args){
        //args是个空集合，但是在xml文件中判断是否为空，故设置为Null
        if(args==null || args.length==0){
            args=null;
        }
        List<T>res = baseMapper.findAll(args);
        return res;
    }

    public void add(Object object){
        baseMapper.add(object);
    }
    public void update(Object object){
        baseMapper.update(object);
    }
    public void del(Object object){
        baseMapper.del(object);
    }

    /**
     * xml获取方法par[0]
     * @param args
     * @param <T>
     * @return
     */
    public <T>T findFirst(Object ...args){
        if(args ==null || args.length==0){
            args=null;
        }
        List<T> resList= baseMapper.findFirst(args);
        if(resList.size()>0){
            return resList.get(0);
        }
        return null;
    }


    public int count(Object ...args){
        if(args ==null || args.length==0){
            args=null;
        }
        return baseMapper.count(args);
    }

    @Override
    protected void finalize() throws java.lang.Throwable{
        super.finalize();
        if(sqlSession!=null && !isClose){
            sqlSession.close();
        }
        System.out.println("释放了");
    }

}
