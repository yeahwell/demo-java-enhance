package com.yeahwell.demo.jvm.case03;

import java.lang.reflect.Method;

/**
 * 自己动手实现远程执行功能
 * <ul>
 * <li>如何编译提交到服务器的Java代码</li>
 * <li>如何执行编译之后的Java代码</li>
 * <li>如何收集Java代码的执行结果</li>
 * </ul>
 * @author yeahwell
 *
 */
public class JavaClassExecuter {

	public static String execute(byte[] classByte){
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/yeahwell/demo/jvm/HackSystem");
		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class clazz = loader.loadByte(modiBytes);
		
		try{
			Method method = clazz.getMethod("main", new Class[]{String[].class});
			method.invoke(null, new String[]{null});
			
		}catch(Throwable e){
			e.printStackTrace(HackSystem.out);
		}
		return HackSystem.getBufferString();
	}
}
