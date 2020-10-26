//
// Created by Rokas on 24/10/2020.
//

#ifndef EXERCISES_STUDENTHANDLER_H
#define EXERCISES_STUDENTHANDLER_H
#include "Student.h"

typedef struct StudentHandler* studentHandler_t;
studentHandler_t studentHandler_createStudentHandler();
void studentHandler_printStudentInfo(studentHandler_t self, student_t* student);
void studentHandler_printAllStudentsInfo(studentHandler_t self);
student_t* studentHandler_searchStudentById(studentHandler_t self, uint16_t studentId);
student_t* studentHandler_searchStudentByLastName(studentHandler_t self, char* lastName);
void studentHandler_addStudent(studentHandler_t self, student_t* student);

#endif //EXERCISES_STUDENTHANDLER_H
