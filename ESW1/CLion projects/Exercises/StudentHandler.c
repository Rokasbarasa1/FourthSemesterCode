//
// Created by Rokas on 24/10/2020.
//
#include "StudentHandler.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct StudentHandler {
    int name;
    char* kaka;
}StudentHandler;

studentHandler_t studentHandler_createStudentHandler(){
    studentHandler_t new_list = calloc(sizeof(StudentHandler), 1);
    if (NULL == new_list) {
        return NULL;
    }
    //new_list->head = NULL;
    return new_list;
}

void studentHandler_printStudentInfo(studentHandler_t self, student_t* student) {

}

void studentHandler_printAllStudentsInfo(studentHandler_t self) {

}

student_t* studentHandler_searchStudentById(studentHandler_t self) {
    return NULL;
}

student_t* studentHandler_searchStudentByLastName(studentHandler_t self, char* lastName) {
    return NULL;
}

void studentHandler_addStudent(studentHandler_t self, student_t* student) {

}