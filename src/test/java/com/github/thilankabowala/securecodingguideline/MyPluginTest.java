package com.github.thilankabowala.securecodingguideline;

import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.testFramework.TestDataPath;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.intellij.util.PsiErrorElementUtil;

@TestDataPath("\\$CONTENT_ROOT/src/test/testData")
public class MyPluginTest extends BasePlatformTestCase {

    public void testXMLFile() {
        PsiFile psiFile = myFixture.configureByText(XmlFileType.INSTANCE, "<foo>bar</foo>");
//        XmlFile xmlFile = assertInstanceOf(psiFile, XmlFile::class.java);
//
//        assertFalse(PsiErrorElementUtil.hasErrors(project, xmlFile.virtualFile));
//
//        assertNotNull(xmlFile.rootTag);
//
//        xmlFile.rootTag?.let {
//            assertEquals("foo", it.name);
//            assertEquals("bar", it.value.text);
//        }
    }

    @Override
    public String getTestDataPath() {
        return "src/test/testData/rename";
    }

    public void testRename() {
        myFixture.testRename("foo.xml", "foo_after.xml", "a2");
    }
}
