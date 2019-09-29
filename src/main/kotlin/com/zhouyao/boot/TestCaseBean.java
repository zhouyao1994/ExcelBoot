package com.zhouyao.boot;

import java.util.Map;

public class TestCaseBean {
  private String PackageName;
  private String ClassName;
  private String MethodName;
  private String MainBO;
  private String TestMethod;
  private Integer ForwardDependencyNum;
  private String NorP;
  private String AssertSentence;
  private Map<String, String> Fields;

//  public TestCaseBean() {
//  }

  @Override
  public String toString() {
    return "TestCaseBean{" +
            "PackageName='" + PackageName + '\'' +
            ", ClassName='" + ClassName + '\'' +
            ", MethodName='" + MethodName + '\'' +
            ", MainBO='" + MainBO + '\'' +
            ", TestMethod='" + TestMethod + '\'' +
            ", Fields=" + Fields +
            '}';
  }

  public TestCaseBean(String packageName,
                      String className,
                      String methodName,
                      String mainBO,
                      String testMethod,
//                      String[][] fields) {
                      Map<String, String> fields,
                      Integer forwardDependencyNum,
                      String assertSentence,
                      String norP) {
    PackageName = packageName;
    ClassName = className;
    MethodName = methodName;
    MainBO = mainBO;
    TestMethod = testMethod;
//    Fields = Stream.of(fields).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    Fields =  fields;
    NorP = norP;
    AssertSentence = assertSentence;
  }

  public String getPackageName() {
    return PackageName;
  }

  public void setPackageName(String packageName) {
    PackageName = packageName;
  }

  public String getClassName() {
    return ClassName;
  }

  public void setClassName(String className) {
    ClassName = className;
  }

  public String getMethodName() {
    return MethodName;
  }

  public void setMethodName(String methodName) {
    MethodName = methodName;
  }

  public String getMainBO() {
    return MainBO;
  }

  public void setMainBO(String mainBO) {
    MainBO = mainBO;
  }

  public String getTestMethod() {
    return TestMethod;
  }

  public void setTestMethod(String testMethod) {
    TestMethod = testMethod;
  }

  public Map<String, String> getFields() {
    return Fields;
  }

  public void setFields(Map<String, String> fields) {
    Fields = fields;
  }

  public Integer getForwardDependencyNum() {
    return ForwardDependencyNum;
  }

  public void setForwardDependencyNum(Integer forwardDependencyNum) {
    ForwardDependencyNum = forwardDependencyNum;
  }

  public String getAssertSentence() {
    return AssertSentence;
  }

  public void setAssertSentence(String assertSentence) {
    AssertSentence = assertSentence;
  }

  public String getNorP() {
    return NorP;
  }

  public void setNorP(String norP) {
    NorP = norP;
  }
}
