//
// Created by Rokas on 24/10/2020.
//
#include "StudentList.h"
#include <stdlib.h>


typedef struct StudentList {
    list_t* adaptee;
}StudentList;

studentList_t studentList_createStudentList(list_t list){
    studentList_t new_studentList = calloc(sizeof(StudentList), 1);
    if (NULL == new_studentList) {
        return NULL;
    }
    new_studentList->adaptee = list;
    return new_studentList;
}

list_listReturnCode studentList_addStudent(studentList_t self, student_t* student) {
    uint16_t response = list_addItem(self->adaptee, student);
    if(response == 0){
        free(response);
        return OK;
    } else{
        free(response);
        return ERROR;
    }
}

student_t* studentList_getStudent(studentList_t self, uint16_t index) {
    student_t* student;
    uint16_t response = list_getItem(self->adaptee, &student, index);
    if(response == 0){
        free(response);
        return student;
    } else{
        free(response);
        return NULL;
    }
}

list_listReturnCode studentList_removeStudent(studentList_t self, student_t* student) {
    uint16_t response = list_removeItem(self->adaptee, student);
    if(response == 0){
        free(response);
        return OK;
    } else{
        free(response);
        return ERROR;
    }
}

uint16_t studentList_noOfStudents(studentList_t self) {
    return list_noOfItems(self->adaptee);
}