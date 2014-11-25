package com.yeahwell.demo.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * 对类文件进行增强的时机是需要在Java源代码编译之后，在JVM 执行之前。
 * @author yeahwell
 *
 */
public class TestASM {
	
	public static int GETSTATIC = 1;
	
	public static int INVOKEVIRTUAL = 2;

	public void testASM() throws IOException {

		InputStream is = new FileInputStream("");
		ClassReader cr = new ClassReader(is);
		ClassNode cn = new ClassNode();
		cr.accept(cn, 0);
		for (Object object : cn.methods) {
			MethodNode mn = (MethodNode) object;
			if ("<init>".equals(mn.name) || "<clinit>".equals(mn.name)) {
				continue;
			}
			InsnList insns = mn.instructions;
			InsnList il = new InsnList();
			il.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;"));
			il.add(new LdcInsnNode("Enter method -> " + mn.name));
			il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream",
					"println", "(Ljava/lang/String;)V"));
			insns.insert(il);
			mn.maxStack += 3;
		}
		ClassWriter cw = new ClassWriter(0);
		cn.accept(cw);
		byte[] b = cw.toByteArray();
	}

}
