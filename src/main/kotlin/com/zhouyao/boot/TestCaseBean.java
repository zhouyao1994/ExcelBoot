package com.zhouyao.boot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCaseBean {
  private String PackageName;
  private String ClassName;
  private String MethodName;
  private String MainBO;
  private String TestMethod;
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
                      Map<String, String> fields) {
    PackageName = packageName;
    ClassName = className;
    MethodName = methodName;
    MainBO = mainBO;
    TestMethod = testMethod;
//    Fields = Stream.of(fields).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    Fields =  fields; }

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
}
