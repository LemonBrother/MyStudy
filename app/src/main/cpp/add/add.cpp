//
// Created by USER on 2019/01/03.
//

#include "add.h"
#include "../take/take.h"

int add(int a, int b){
    int temp = take(a+b);
    return temp;
}