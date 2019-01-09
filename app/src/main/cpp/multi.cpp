//
// Created by USER on 2019/01/03.
//


#include <jni.h>
#include "take/take.h"
#include "add/add.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_example_liumin_mystudy_jni_JniUtils_getMultiString(JNIEnv* env,jobject obj,int a,int b){

    jstring result = env ->NewStringUTF("the answer is ")  +add(a,b);

return result;
}