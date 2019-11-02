package com.xcy.job.utils;

/**
 * @author xcy
 * @Desc 处理薪水字符串工具 将 (0.7-1.5万/月),(6-9千/月),(1.5千以下/月),(30万/年),(9-15万/年) 字符串
 * 抽取出最低和最高工资
 * @date 2019/11/2 17:23
 * @Version v1.0
 */
public class SalaryUtil {

    public static Integer getLowerSalary(String text) {
        Integer a = common(text);
        if (text.contains("千/月")) {
            String[] split = text.replace("千/月", "").split("-");
            String lowerSalary = split[0];

            Float l = Float.parseFloat(lowerSalary) * 1000;
            a = Math.round(l);
        }
        if (text.contains("万/月")) {
            String[] split = text.replace("万/月", "").split("-");
            String lowerSalary = split[0];
            Float l = Float.parseFloat(lowerSalary) * 10000;
            a = Math.round(l);
        }
        if (text.contains("万/年")) {
            String[] split = text.replace("万/年", "").split("-");
            Float l = Float.parseFloat(split[0]) * 10000;
            a = Math.round(l / 12);
        }
        return a;
    }


    public static Integer getHigherSalary(String text) {
        Integer a = common(text);
        if (text.contains("千/月")) {
            String[] split = text.replace("千/月", "").split("-");
            String higherSalary = split[1];
            Float l = Float.parseFloat(higherSalary) * 1000;
            a = Math.round(l);
        }
        if (text.contains("万/月")) {
            String[] split = text.replace("万/月", "").split("-");
            String higherSalary = split[1];
            Float l = Float.parseFloat(higherSalary) * 10000;
            a = Math.round(l);
        }
        if (text.contains("万/年")) {
            String[] split = text.replace("万/年", "").split("-");
            Float l;
            if (split.length > 1) {
                l = Float.parseFloat(split[1]) * 10000;
            } else {
                l = Float.parseFloat(split[0]) * 10000;
            }
            a = Math.round(l / 12);
        }
        return a;
    }

    private static Integer common(String text) {
        Integer a = 0;
        if (text.contains("千以下/月")) {
            String s = text.replace("千以下/月", "");
            Float l = Float.parseFloat(s) * 1000;
            a = Math.round(l);
        }
        return a;
    }


    public static void main(String[] args) {
        Integer lowerSalary = getLowerSalary("6-9千/月");
        Integer higherSalary = getHigherSalary("6-9千/月");

        Integer lowerSalary1 = getLowerSalary("0.7-1.5万/月");
        Integer higherSalary1 = getHigherSalary("0.7-1.5万/月");

        Integer lowerSalary2 = getLowerSalary("1.5千以下/月");
        Integer higherSalary2 = getHigherSalary("1.5千以下/月");

        Integer lowerSalary3 = getLowerSalary("30万/年");
        Integer higherSalary3 = getHigherSalary("30万/年");

        Integer lowerSalary4 = getLowerSalary("9-15万/年");
        Integer higherSalary4 = getHigherSalary("9-15万/年");

        System.out.println("测试完毕");
    }


}
