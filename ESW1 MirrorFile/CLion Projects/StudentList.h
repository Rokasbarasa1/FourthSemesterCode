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
/*
typedef StudentList* studentList_t;
void createStudentList(studentList_t self);
ListReturnCode addStudent(studentList_t self, student_t* student);
ListReturnCode getStudent(studentList_t self, student_t* student);
ListReturnCode reoveStudent(studentList_t self, student_t* student);
uint16_t noOfStudents(studentList_t self);
 */