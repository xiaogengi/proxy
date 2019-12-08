package com.xg.patternproxy.proxy.datasourcedynamicproxy.handwriting;

import com.xg.patternproxy.proxy.datasourcedynamicproxy.OrderServiceDynamicProxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XgProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(XgClassLoader classLoader, Class<?>[] interfaces, XgInvocationHandler h) {

        try {
            //获取字符串代码
            String filePath = generatesClass(interfaces);

            String path = XgProxy.class.getResource("").getPath();
//            path = path.replace("%e7%bd%91%e7%9b%98/%e4%bb%a3%e7%90%86%e6%a8%a1%e5%bc%8f/"
//                    ,"网盘/代理模式/");

            File f = new File(path + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(filePath);
            fw.flush();
            fw.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manage.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manage, null, null, null, iterable);
            task.call();
            manage.close();

            Class proxyclass = classLoader.findClass("$Proxy0");
            Constructor c = proxyclass.getConstructor(XgInvocationHandler.class);
//            f.delete();
            return c.newInstance(h);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static String generatesClass(Class<?>[] interfaces) {
        try {

            StringBuffer sb = new StringBuffer();
            sb.append("package com.xg.patternproxy.proxy.datasourcedynamicproxy.handwriting;" + ln);
            sb.append("import com.xg.patternproxy.proxy.datasourcedynamicproxy.IOrderService;" + ln);
            sb.append("import java.lang.reflect.*;" + ln);
            sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);

                sb.append("XgInvocationHandler h;" + ln);
                sb.append("public $Proxy0(XgInvocationHandler h){" + ln);
                    sb.append("this.h = h;" + ln);
                sb.append("}" + ln);

            for (Method m : interfaces[0].getMethods()) {
                Class<?>[] params = m.getParameterTypes();

                StringBuffer paramNames = new StringBuffer();
                StringBuffer paramValues = new StringBuffer();
                StringBuffer paramClasses = new StringBuffer();

                for (int i = 0; i < params.length; i++) {
                    Class clazz = params[i];
                    String type = clazz.getName();
                    String paramName = toLowerFirstCase(clazz.getSimpleName());
                    paramNames.append(type + " " + paramName);
                    paramValues.append(paramName);
                    paramClasses.append(clazz.getName() + ".class");
                    if(i > 0){
                        paramNames.append(",");
                        paramValues.append(",");
                        paramClasses.append(",");
                    }
                }
                
                sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln);
                    sb.append("try{" + ln);
                        sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\", new Class[]{" + paramClasses.toString() + "});" + ln);
                        sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this, m, new Object[]{" + paramValues +"})", m.getReturnType()) + ";" + ln);
                    sb.append("} catch (Throwable e){}" + ln);
                sb.append("}" + ln);

            }

            sb.append("}" + ln);
            return sb.toString();
        } catch (Exception e){

        }
        return null;
    }

    private static boolean hasReturnValue(Class<?> returnType) {
        return returnType != void.class;
    }

    private static Map<Class,Class> mappings = new HashMap<>();
    static {
        mappings.put(int.class,int.class);
    }

    private static Object getCaseCode(String s, Class<?> returnType) {
        if(mappings.containsKey(returnType)){
            return "((" + mappings.get(returnType.getName() + ")" + s + ")." + returnType.getSimpleName() + "Value()");
        }
        return s;
    }

    private static String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
