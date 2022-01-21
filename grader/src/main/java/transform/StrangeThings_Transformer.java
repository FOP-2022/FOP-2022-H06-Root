package transform;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

public class StrangeThings_Transformer implements ClassTransformer {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if (reader.getClassName().equals("h06/StrangeThings")) {
            reader.accept(new StrangeThings_Visitor(writer), 0);
        } else {
            reader.accept(writer, 0);
        }
    }
}

class StrangeThings_Visitor extends ClassVisitor {

    public StrangeThings_Visitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.equals("computeStrangeValue1")) {
            return new StrangeThings_MethodVisitor(super.visitMethod(access, name, descriptor, signature, exceptions));
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}

class StrangeThings_MethodVisitor extends MethodVisitor {

    public StrangeThings_MethodVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM9, methodVisitor);
    }

    @Override
    public void visitCode() {
        visitVarInsn(Opcodes.ILOAD, 0);
        visitMethodInsn(Opcodes.INVOKESTATIC,
            "h06/StrangeThings_Foo",
            "computeStrangeValue1_injected",
            "(I)V",
            false
        );
        super.visitCode();
    }
}
