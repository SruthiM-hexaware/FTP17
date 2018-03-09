package com.rest;




public class newrest{


    public static newrest[] numbers(int b){
        newrest[] A = new newrest[6]();
        for(int i=0; i<A.length;i++) {
            A[i]= b*i;
        }
        return A;
    }
}