package com.swufestu.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;

import com.swufestu.calculator.databinding.ActivityMainBinding;
import com.swufestu.calculator.my_control.MyViewModel;

public class MainActivity extends AppCompatActivity {
    int num=0;

    private MyViewModel myViewModel;//数据模式
    private ActivityMainBinding binding;//组件存储
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);//获得所有控件
        //获取数据模型
        myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        //事件监控
        //监听数据是否发生改变
        myViewModel.getMainNum().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.myTextView.setText(myViewModel.getMainNum().getValue());//让myTextView显示mainNum的数值
                //让textView（小标签）显示用户输入的式子
                if(myViewModel.sign2.equals("")) {
                    if (myViewModel.sign1.equals("")) {
                        binding.textView.setText(myViewModel.getMainNum().getValue());
                    } else {//如果像a+b这种形式的时候
                        binding.textView.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.getMainNum().getValue());
                    }
                }else{
                    //如果是像a+b*c这种式子时
                    binding.textView.setText(myViewModel.num[0]+myViewModel.sign1+myViewModel.num[1]+myViewModel.sign2+myViewModel.getMainNum().getValue());
                }
            }
        });
        //监听每个按钮
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("0");
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("1");
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("2");
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("3");
            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("4");
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("5");
            }
        });

        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("6");
            }
        });

        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("7");
            }
        });

        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("8");
            }
        });

        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("9");
            }
        });
        //小数点
        binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue((myViewModel.getMainNum().getValue()+"."));
                    myViewModel.havePoint=true;
                };
            }
        });
        //加号
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="+";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){//如果像a+b这种形式
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else{//如果是像a+b*c这种式子的时候
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2="";
                    myViewModel.num[1]="";
                    myViewModel.num[0]=myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1="+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        //乘号
        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "*";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*")||myViewModel.sign1.equals("/")){//按照顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1="*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{//如果sign[1]是减号或者加号
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2="*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{//如果是a+b*c这样的形式
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2="*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });
        //减号
        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1="-";
                    myViewModel.num[0]=myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if(myViewModel.sign2.equals("")){//如果像a+b这种向欧盟是奇偶
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else{//如果是像a+b*c这种式子的时候
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.sign2="";
                    myViewModel.num[1]="";
                    myViewModel.num[0]=myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1="-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });

        //除号
        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "/";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*")||myViewModel.sign1.equals("/")){//按照顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1="/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }else{//如果sign[1]是减号或者加号
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2="/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint=false;
                    }
                }else{//如果是a+b*c这样的形式
                    myViewModel.num[1] = myViewModel.mainNumWithNum_1_Tocal();
                    myViewModel.sign2="/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }
            }
        });

        //重置
        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//全部初始化
                myViewModel.sign2="";
                myViewModel.sign1="";
                myViewModel.num[0]="";
                myViewModel.num[1]="";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint=false;
            }
        });

        binding.buttonCount.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign2.equals("")){
                   if(!myViewModel.sign1.equals("")){
                       myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                       if(myViewModel.getMainNum().getValue().contains(".")){
                           myViewModel.havePoint=true;
                       }else{
                           myViewModel.havePoint=false;
                       }
                       myViewModel.num[0]="";
                       myViewModel.sign1="";
                   }
                }else{//如果是a+b*c这种形式的式子的时候
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_1_Tocal());
                    myViewModel.num[1]="";
                    myViewModel.sign2="";
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                    if(myViewModel.getMainNum().getValue().contains(".")){
                        myViewModel.havePoint=true;
                    }else{
                        myViewModel.havePoint=false;
                    }
                    myViewModel.num[0]="";
                    myViewModel.sign1="";
                }
                binding.textView.setText(myViewModel.getMainNum().getValue()); //修改小标签显示
            }
        }));
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.getMainNum().getValue().equals("0")){
                   myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    if(myViewModel.getMainNum().getValue().equals(""))
                        myViewModel.getMainNum().setValue("0");
                }
            }
        });

    }
}