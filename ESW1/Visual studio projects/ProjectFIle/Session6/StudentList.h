#pragma once
#include <stdint.h>
#include "Student.h"
typedef enum {
	OK,
	EMPTY,
	NOT_FOUND,
	NULLL,
	ERROR
}ListReturnCode;

typedef StudentList* studentList_t;
void studentList_createStudentList(studentList_t self);
ListReturnCode studentList_addStudent(studentList_t self, student_t* student);
ListReturnCode studentList_getStudent(studentList_t self, student_t* student);
ListReturnCode studentList_reoveStudent(studentList_t self, student_t* student);
uint16_t studentList_noOfStudents(studentList_t self);