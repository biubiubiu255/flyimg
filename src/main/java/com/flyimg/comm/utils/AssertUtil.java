package com.flyimg.comm.utils;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.vo.MyException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 参数检查断言工具类，可以减少一些<code>if</code>代码逻辑<br>
 * 当断言不成立时，抛出指定错误代码的异常 LdPayBaseException
 *
 */
public final class AssertUtil {

    /**
     * 禁用构造函数
     */
    private AssertUtil() {
        // 禁用构造函数
    }

    //期望值：空
    //异常为：不为空

    public static void isNullsEx(ResultCode resultCode, Object...objects){
        for (int i=0;i<objects.length;i++){
            if(objects[i]!=null) {
                throw new MyException(resultCode);
            }
        }
    }


    public static void isNotNullsEx(ResultCode resultCode, Object...objects)  {

        for (int i=0;i<objects.length;i++){
            if(objects[i]==null || "".equals(objects[i].toString())) {
                throw new MyException(resultCode);
            }
        }
    }

    public static void isIntackPojoEx(ResultCode resultCode, Object pojo)  {

        Field[] declaredFields = pojo.getClass().getDeclaredFields();

        for (Field f : pojo.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                if (f.get(pojo) == null) {
                    System.out.println("[Debug-me] pojo." + f.getName() + " is null");
                    throw new MyException(resultCode);
                }
            } catch (IllegalAccessException e) {
                System.out.println("[Debug-me] pojo." + f.getName() + " is null");
                throw new MyException(resultCode);
            }
        }

    }

    public static void isIntackExceptFiledEx(ResultCode resultCode, Object pojo, String... fields)  {

        Field[] declaredFields = pojo.getClass().getDeclaredFields();

        Set<String> mySet = new HashSet<String>(Arrays.asList(fields));

        for (Field f : pojo.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                if (f.get(pojo) == null  &&  mySet.contains(f.getName())) {
                    System.out.println("[Debug-me] pojo." + f.getName() + " is null");
                    throw new MyException(resultCode);
                }
            } catch (IllegalAccessException e) {
                System.out.println("[Debug-me] pojo." + f.getName() + " is null");
                throw new MyException(resultCode);
            }
        }

    }

    public static void isIntackSubclassEx(ResultCode resultCode, Object pojo)  {
        isIntackSubclassEx(resultCode, pojo, 0);
    }


    /**
    *  Func isIntackSubclassEx()  检测POJO对象是否是合法值
    *  p1: 抛出异常的errorCode  p2: 要检测的pojo  p3: 允许不合法的个数，负数为反向（输入1，只需要一个成功的成员即可）
    * */
    public static void isIntackSubclassEx(ResultCode resultCode, Object pojo, int allowNullNumber)  {

        Field[] declaredFields = pojo.getClass().getDeclaredFields();

        int nullNumber = allowNullNumber<0 ? declaredFields.length+allowNullNumber : 0;

        try {
            for (Field f : pojo.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                if (f.get(pojo) == null) {
                    System.out.println("[Debug-me] pojo." + f.getName() + " is null");
                    nullNumber ++;
                }
            }
            if(nullNumber>allowNullNumber){
                throw new MyException(resultCode);
            }
        } catch (IllegalAccessException e) {
            throw new MyException(resultCode);
        }
    }

    public static void isNotNullListEx(List list, ResultCode resultCode)  {
        if(list==null || list.size()==0){
            throw new MyException(resultCode);
        }
    }

    public static void isNullListEx(ResultCode resultCode, List list)  {
        if(list==null || list.isEmpty()){
            return;
        }
        throw new MyException(resultCode);
    }

    /** 期待值：false（与这个相反的），如果这个为真了就不是我期待的，抛出异常 **/
    public static void isFalse(boolean bo, ResultCode resultCode) {
        if (bo) {
            throw new MyException(resultCode);
        }
    }

    /** 期待值：true（与这个相反的），如果这个为真了就不是我期待的，抛出异常 **/
    public static void isTrue(boolean bo, ResultCode resultCode) {
        if (!bo) {
            throw new MyException(resultCode);
        }
    }

    /** 期待值：true（与这个相反的），如果这个为真了就不是我期待的，抛出异常 **/
    public static void isCount(Object[] objects, int count, ResultCode resultCode) {
        if(objects==null || objects.length!=count){
            throw new MyException(resultCode);
        }
    }
}
