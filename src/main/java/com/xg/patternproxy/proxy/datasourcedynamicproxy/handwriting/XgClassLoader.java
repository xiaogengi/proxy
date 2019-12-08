package com.xg.patternproxy.proxy.datasourcedynamicproxy.handwriting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class XgClassLoader extends ClassLoader {

    private File classPathFile;
    public XgClassLoader(){
        String classPath = XgClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //return super.findClass(name);
        try {

            String className = XgClassLoader.class.getPackage().getName() + "." + name;
            if(classPathFile != null){
                File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
                if(classFile.exists()){
                    FileInputStream in = new FileInputStream(classFile);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff, 0 ,len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());
                }


            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}