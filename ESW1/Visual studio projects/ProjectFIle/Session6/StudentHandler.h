#pragma once
#include "Student.h"

typedef StudentHandler* studentHandler_t;
void studentHandler_printStudentInfo(studentHandler_t self, student_t* student);
void studentHandler_printAllStudentsInfo(studentHandler_t self);
student_t* studentHandler_searchStudentById(studentHandler_t self);
student_t* studentHandler_searchStudentByLastName(studentHandler_t self, char* lastName);
void studentHandler_addStudent(studentHandler_t self, student_t* student);
