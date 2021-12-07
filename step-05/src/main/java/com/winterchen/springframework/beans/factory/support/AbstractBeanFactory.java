package com.winterchen.springframework.beans.factory.support;

import com.winterchen.springframework.beans.BeanException;
import com.winterchen.springframework.beans.factory.BeanFactory;
import com.winterchen.springframework.beans.factory.factory.BeanDefinition;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/11/25 15:57
 * @description TODO
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeanException {
        return getBean(name, (Object) null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return toGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return (T) getBean(name);
    }

    protected <T> T toGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

}