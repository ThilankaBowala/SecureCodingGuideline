package CodeFragments;

import Tools.LiveParser;
import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package level code fragment
 *
 * @author S.L. Dasanayake
 * @author A. Mudalige
 * @author M.L.T. Perera
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code refactoring on 28/1/24
 * Original repo: https://bitbucket.org/lasithd2/seproject_framework_for_secure_coding/src/master/
 * @since 2018
 */

public class PackageLevelCodeFragment extends AnAction {
    public List<String> ImplementedInterfaces = new ArrayList<String>();

    public PackageLevelCodeFragment() {
//        JavaParser.getStaticConfiguration().setAttributeComments(false);
        CompilationUnit cu;
        try {
            cu = StaticJavaParser.parse(LiveParser.getInstance().editorText);
        } finally {
        }
        VoidVisitorAdapter<List<String>> ClassVisitor = new ClassVisitor();
        ClassVisitor.visit(cu, ImplementedInterfaces);
    }

    private static class ClassVisitor extends VoidVisitorAdapter<List<String>> {
        @Override
        public void visit(ClassOrInterfaceType n, List<String> arg) {
            super.visit(n, arg);
            arg.add(n.getNameAsString());
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
    }
}
