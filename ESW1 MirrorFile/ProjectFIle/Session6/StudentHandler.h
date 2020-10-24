#pragma once
#include "Student.h"

typedef StudentHandler* studentHandler_t;
studentHandler_t* createStudentHandler(studentHandler_t self);
void printStudentInfo(studentHandler_t self, student_t* student);
void printAllStudentsInfo(studentHandler_t self);
student_t* searchStudentById(studentHandler_t self);
student_t* searchStudentByLastName(studentHandler_t self, char* lastName);
void addStudent(studentHandler_t self, student_t* student);
