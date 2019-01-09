#include <jni.h>
#include <string>
#include <string.h>
#include <stdio.h>




#include <stdlib.h>
#include <limits.h>
#include <stdio.h>
#include <errno.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_example_liumin_mystudy_jni_JniUtils_getString(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_example_liumin_mystudy_jni_JniUtils_getFormatString(
        JNIEnv* env,jobject obj,jstring phoneid) {


    //指向常量的指针
    const  char*  str  = env->GetStringUTFChars(phoneid,0);
    //声明三个临时字符串
    char aa[3] =""  ;
    char bb[3] =""  ;
    char cc[2] =""  ;

    //拆成3部分分别进行赋值
    strncpy(aa,str,3);          //从第三位开始截取
    strncpy(bb,str+3,3);
    strncpy(cc,str+6,2);


    //三句一起执行，闪退提示lock
//  env-> GetStringUTFRegion(phoneid,0,3,aa);
//    env-> GetStringUTFRegion(phoneid,3,6,bb);
//    env-> GetStringUTFRegion(phoneid,6,8,cc);


    int a = atoi(aa)*3;
    int b = atoi(bb)*6;
    int c = atoi(cc)*8;


    char *s1 = new  char();
    char *s2 = new  char();
    char *s3 = new  char();
    sprintf(s1,"%d",a);
    sprintf(s2,"%d",b);
    sprintf(s3,"%d",c);

//    char *string1 = (char *) malloc(sizeof(char) * 16);//c中的写法，c++中可以直接new
//    char *string2 = (char *) malloc(sizeof(char) * 16);
//    char *string3 = (char *) malloc(sizeof(char) * 16);
//    sprintf(string1,"%d",a);
//    sprintf(string2,"%d",b);
//    sprintf(string3,"%d",c);


    strcat(s3,s2);
    strcat(s3,s1);
    //加密算法///////////////////////////////////////


    //获取长度，用于截取
    int len = strlen(s3);



    char x[20] = "";
    char  y[8] = "";

    strncpy(x,s3,8);
    strncpy(y,s3+8,len-8);

    int yi = (atoi(y)*35)+100;



    char *ys=(char *) malloc(sizeof(char) * 16);;
    sprintf(ys,"%d",yi);

    int yslen = strlen(ys);

    strncpy(y ,ys+(yslen-2),2);

    strcat(x,y);

    //释放内存空间
    delete s1;
    delete s2;
    delete s3;
    env->ReleaseStringUTFChars(phoneid,str);

    return env->NewStringUTF( x);



}













