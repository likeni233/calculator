package com.swufestu.calculator.my_control;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//数据模型
public class MyViewModel extends ViewModel {
    //只要程序未关闭模型中数据就不会初始化！！
    public MutableLiveData<String> mainNum;//主数值（用户正在操作的数）

    public boolean havePoint = false;//主数值中是否包含小数点
    public String sign1 = "", sign2 = "";//储存运算符
    public String num[] = {"", ""};//储存参与计算的数值

    public MutableLiveData<String> getMainNum() {
        if (mainNum == null) {
            mainNum = new MutableLiveData<>();
            mainNum.setValue("0");
        }
        return mainNum;
    }

    public void setMainNum(String n) {
        if (mainNum.getValue().equals("0")) {
            mainNum.setValue(n);
        } else {
            mainNum.setValue(mainNum.getValue() + n);
        }
    }

    public String mainNumWithNum_0_Tocal() {
        String value = "0";
        if (mainNum.getValue().contains(".") || num[0].contains(".")) {//如果两个数的其中一个数有小数点
            switch (sign1) {
                case "+":
                    value = String.valueOf(Double.valueOf(num[0]) + Double.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Double.valueOf(num[0]) - Double.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Double.valueOf(num[0]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) //除数不能为0！！
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
            }
        } else {//如果两个数都是整数
            switch (sign1) {
                case "+":
                    value = String.valueOf(Integer.valueOf(num[0]) + Integer.valueOf(mainNum.getValue()));
                    break;
                case "-":
                    value = String.valueOf(Integer.valueOf(num[0]) - Integer.valueOf(mainNum.getValue()));
                    break;
                case "*":
                    value = String.valueOf(Integer.valueOf(num[0]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) //除数不能为0！！
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
            }
        }
        return value;
    }

    public String mainNumWithNum_1_Tocal(){
        String value = "0";
        if (mainNum.getValue().contains(".") || num[1].contains(".")) {//如果两个数的其中一个数有小数点
            switch (sign2) {
                case "*":
                    value = String.valueOf(Double.valueOf(num[1]) * Double.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) //除数不能为0！！
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
            }
        } else {//如果两个数都是整数
            switch (sign2) {
                case "*":
                    value = String.valueOf(Integer.valueOf(num[1]) * Integer.valueOf(mainNum.getValue()));
                    break;
                case "/":
                    if (mainNum.getValue().equals("0")) //除数不能为0！！
                        mainNum.setValue("1");
                    value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
            }
        }  return value;
    }
}
