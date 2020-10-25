//
// Created by Rokas on 24/10/2020.
//
#include "StudentList.h"
#include "list.h"
#include <stdlib.h>


typedef struct StudentList {
    list_t* adaptee;
}StudentList;

studentList_t studentList_createStudentList(){
    studentList_t new_studentList = calloc(sizeof(StudentList), 1);
    if (NULL == new_studentList) {
        return NULL;
    }
    //new_studentList->head = NULL;
    return new_studentList;
}

list_listReturnCode studentList_addStudent(studentList_t self, student_t* student) {
    return 1;
}

list_listReturnCode studentList_getStudent(studentList_t self, student_t* student) {
    return 1;
}

list_listReturnCode studentList_reoveStudent(studentList_t self, student_t* student) {
    return 1;
}

uint16_t studentList_noOfStudents(studentList_t self) {
    return 1;
}