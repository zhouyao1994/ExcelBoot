package com.zhouyao.boot;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.sun.tools.javac.util.List;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;


public class CodeGenerate {
  public static void main(String[] args) throws IOException {
//    genarateDemo();

    //1.从excel读取配置文件
    List<TestCaseBean> testCaseBeans = GeneralBeans();

    //2.处理
    //一个包下可以有多个类，每个类有多个方法。每个方法是一个testcase。
    //按 包名分组   （同一个包下可以有多个类）
    Map<String, java.util.List<TestCaseBean>> collect = testCaseBeans
            .stream()
            .collect(Collectors.groupingBy(TestCaseBean::getPackageName));

    //3.输出
    //按分组结果 在按 类名分组一次  （同一个类下可以有多个测试方法）
    collect.forEach((pakegeName,v)->{
      Map<String, java.util.List<TestCaseBean>> classGroupBy = v
              .stream()
              .collect(Collectors.groupingBy(TestCaseBean::getClassName));

      classGroupBy.forEach((className, cases) ->{
        try {
          System.out.println("=============");
          GenerateFiles(pakegeName, className, cases).writeTo(System.out);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });

    });
  }

  private static JavaFile GenerateFiles(String pakegeName,
                                        String className,
                                        java.util.List<TestCaseBean> cases) {
    TypeSpec.Builder builder = TypeSpec.classBuilder(className)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
    //1.先创建方法，有多个case

    for (TestCaseBean caseIt : cases) {
//    TestCaseBean caseIt = cases.get(0);

    String methodName = caseIt.getMethodName();
    String caseItClassName = caseIt.getClassName();
    String mainBO = caseIt.getMainBO();
    String testMethod = caseIt.getTestMethod();
    Map<String, String> fields = caseIt.getFields();

    //1.1 创建单个方法
    MethodSpec.Builder builderLocal = MethodSpec.methodBuilder(methodName)
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(void.class)
            .addParameter(String[].class, "args")
            //1.new 对象,N表示name，S表示String
            .addStatement("$N testObject = new $N()", mainBO, mainBO);
    //1.2 为新建BO设置属性
    fields.forEach((key, value) -> {
      builderLocal.addStatement("testObject.set$N($S)", key, value);
    });

    //1.3 run测试函数的逻辑代码
    builderLocal.addStatement("$N($N)", testMethod, "testObject");

    MethodSpec localClass = builderLocal
            .build();
    //2.把方法放到类中
      builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addMethod(localClass);
    }

    //3.把类放到包中，返回文件
    return JavaFile.builder(pakegeName, builder.build())
            .build();
  }


  public static List<TestCaseBean> GeneralBeans() {
    List<TestCaseBean> testCase = List.of(
            new TestCaseBean(
                    "org.xx.yy.zz",
                    "Rate",
                    "Invivid",
                    "BO",
                    "mainLIneA",
                    new String[][] {{ "Hello", "World" },
                    { "John", "Doe" },}),
            new TestCaseBean(
                    "org.xx.yy.zz",
                    "Rate",
                    "All",
                    "BO",
                    "mainLIneA",
                    new String[][] {{ "Hello", "World" },
                    { "John", "Doe" },}),
            new TestCaseBean(
                    "org.xx.yy.aa",
                    "Fee",
                    "NullString",
                    "BO",
                    "mainLIneA",
                    new String[][] {{ "Hello", "World" },
                    { "John", "Doe" },}),
            new TestCaseBean(
                    "org.xx.yy.aa",
                    "Fee",
                    "EmptyName",
                    "BO",
                    "mainLIneA",
                    new String[][] {{ "Hello", "World" },
                    { "John", "Doe" },})
    );
    return testCase;
  }

  private static void genarateDemo() throws IOException {
    HashMap<String, String> stringStringHashMap = new HashMap<>();
    stringStringHashMap.put("methodName", "Invivid");
    stringStringHashMap.put("RunMethod", "mainLIneA");
    stringStringHashMap.put("classBo", "BO");
    stringStringHashMap.put("FildA", "nameA");
    stringStringHashMap.put("FildB", "nameB");
    stringStringHashMap.put("PackageName", "org.xx.yy.zz");
//    System.out.println(stringStringHashMap);
//    System.out.println(stringStringHashMap);

    //按包名分组生成

    //按类名分组生成


    String methodName = stringStringHashMap.remove("methodName");
    String classBo = stringStringHashMap.remove("classBo");
    String Runmethod = stringStringHashMap.remove("RunMethod");
    String packageName = stringStringHashMap.remove("PackageName");

    MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName)
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(void.class)
            .addParameter(String[].class, "args")
            .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
            //1.new 对象,N表示name，S表示String
            .addStatement("$N testObject = new $N()", classBo, classBo);
    //2.设置值
    stringStringHashMap.forEach((key, value) -> {
      builder.addStatement("testObject.set$N($S)", key, value);
    });

    //3.run正常的逻辑
    builder.addStatement("$N($N)", Runmethod, "testObject");

    MethodSpec main = builder
            .build();

    TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addMethod(main)
            .build();

    JavaFile javaFile = JavaFile.builder(packageName, helloWorld)
            .build();


    javaFile.writeTo(System.out);
  }

//  public static ArrayList<HashMap<String, String>> GenerateHash() {
//    ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();

//    new TestCaseBean(
//            "org.xx.yy.zz",
//            "Rate",
//            "Invivid",
//            "BO",
//            "mainLIneA",
//            "nameA",
//            "nameB",
//            "nameC",
//            "nameD"
//    );
//
//    HashMap<String, String> stringHashMap2 = getMap(
//            "org.xx.yy.zz",
//            "Rate",
//            "All",
//            "BO",
//            "mainLIneA",
//            "nameA1",
//            "nameB1",
//            "nameC1",
//            "nameD1"
//    );
//
//    TestCaseBean testCaseBean = new TestCaseBean(
//            "org.xx.yy.aa",
//            "Fee",
//            "NullString",
//            "BO",
//            "mainLIneA",
//            List.of("nameA2",
//                    "nameB2",
//                    "nameC2",
//                    "nameD2")
//    );
//
//    TestCaseBean testCaseBean1 = new TestCaseBean(
//            "org.xx.yy.aa",
//            "Fee",
//            "EmptyName",
//            "BO",
//            "mainLIneA",
//            List.of("nameA3",
//                    "nameB3",
//                    "nameC3",
//                    "nameD3")
//    );
//
//    TestCaseBean testCaseBean2 = new TestCaseBean(
//            "org.xx.yy.bb",
//            "Accr",
//            "GetName",
//            "BO",
//            "mainLIneA",
//            List.of("nameA4",
//                    "nameB4",
//                    "nameC4",
//                    "nameD4"
//            );
//
//    TestCaseBean testCaseBean3 = new TestCaseBean(
//            "org.xx.yy.bb",
//            "Accr",
//            "HideGoald",
//            "BO",
//            "mainLIneA",
//            List.of("nameA5",
//                    "nameB5",
//                    "nameC5",
//                    "nameD5"
//            );
//    hashMaps.add(stringHashMap1);
//    hashMaps.add(stringHashMap2);
//    hashMaps.add(stringHashMap3);
//    hashMaps.add(stringHashMap4);
//    hashMaps.add(stringHashMap5);
//    hashMaps.add(stringHashMap6);
//    return hashMaps;
//  }
//
//  public static HashMap<String, String> getMap(String PackageName,
//                                               String ClassName,
//                                               String MethodName,
//                                               String MainBO,
//                                               String TestMethod,
//                                               String FieldA,
//                                               String FieldB,
//                                               String FieldC,
//                                               String FieldD) {
//    HashMap<String, String> stringStringHashMap1 = new HashMap<>();
//    stringStringHashMap1.put("PackageName", PackageName);
//    stringStringHashMap1.put("ClassName", ClassName);
//    stringStringHashMap1.put("MethodName", MethodName);
//    stringStringHashMap1.put("MainBO", MainBO);
//    stringStringHashMap1.put("TestMethod", TestMethod);
//    stringStringHashMap1.put("FieldA", FieldA);
//    stringStringHashMap1.put("FieldB", FieldB);
//    stringStringHashMap1.put("FieldC", FieldC);
//    stringStringHashMap1.put("FieldD", FieldD);
//    return stringStringHashMap1;
//  }
}
