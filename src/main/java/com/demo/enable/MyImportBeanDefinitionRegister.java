package com.demo.enable;

import com.demo.importselector.Biology;
import com.demo.importselector.Person;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    /**
     * Register bean definitions as necessary based on the given annotation metadata of
     * the importing {@code @Configuration} class.
     * <p>Note that {@link BeanDefinitionRegistryPostProcessor} types may <em>not</em> be
     * registered here, due to lifecycle constraints related to {@code @Configuration}
     * class processing.
     *
     * @param importingClassMetadata annotation metadata of the importing class
     * @param registry               current bean definition registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        System.out.println("ImportBeanDefinitionRegistrar");

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
        registry.registerBeanDefinition("person2",beanDefinitionBuilder.getBeanDefinition());

        BeanDefinitionBuilder beanDefinitionBuilder2 = BeanDefinitionBuilder.rootBeanDefinition(Biology.class);
        registry.registerBeanDefinition("biology2",beanDefinitionBuilder2.getBeanDefinition());


    }
}
