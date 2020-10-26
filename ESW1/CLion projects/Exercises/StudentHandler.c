//
// Created by Rokas on 24/10/2020.
//
#include "StudentHandler.h"
#include "StudentList.h"
#include "Student.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct StudentHandler {
    studentList_t studentList;
}StudentHandler;

studentHandler_t studentHandler_createStudentHandler(studentList_t studentList){
    studentHandler_t new_studentHandler = calloc(sizeof(StudentHandler), 1);
    if (NULL == new_studentHandler) {
        return NULL;
    }
    new_studentHandler->studentList = studentList;
    return new_studentHandler;
}

void studentHandler_printStudentInfo(studentHandler_t self, student_t* student) {
    student_t* studentSearch = studentHandler_searchStudentById(self, student_getId(student));
    student_printInfo(studentSearch);
}

void studentHandler_printAllStudentsInfo(studentHandler_t self) {
    for (int i = 0; i < studentList_noOfStudents(self->studentList); ++i) {
        student_t* student = studentList_getStudent(self->studentList, i);
        student_printInfo(student);
    }
}

student_t* studentHandler_searchStudentById(studentHandler_t self, uint16_t studentId) {
    student_t* student;
    for (int i = 0; i < studentList_noOfStudents(self->studentList); ++i) {
        student = studentList_getStudent(self->studentList, i);
        if(student_getId(student) == studentId){
            break;
        }
    }
    return student;
}

student_t* studentHandler_searchStudentByLastName(studentHandler_t self, char* lastName) {
    student_t* student;
    for (int i = 0; i < studentList_noOfStudents(self->studentList); ++i) {
        student = studentList_getStudent(self->studentList, i);
        if(strcmp(lastName, student_getLastName(student)) == 0){
            break;
        }
    }
    return student;
}

void studentHandler_addStudent(studentHandler_t self, student_t* student) {
    studentList_addStudent(self->studentList, student);
}