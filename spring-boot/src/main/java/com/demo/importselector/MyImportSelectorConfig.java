package com.demo.importselector;

import com.demo.enable.EnableLog;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelectorConfig implements ImportSelector{
    /**
     * Select and return the names of which class(es) should be imported based on
     * the {@link AnnotationMetadata} of the importing @{@link Configuration} class.
     *
     * @param importingClassMetadata
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        System.out.println("selectImports:"+importingClassMetadata.getAnnotationAttributes(EnableLog.class.getName()));

        return new String[]{"com.demo.importselector.Person", "com.demo.importselector.Biology"};
    }
}
