//
// Created by Rokas on 24/10/2020.
//

#ifndef EXERCISES_STUDENTLIST_H
#define EXERCISES_STUDENTLIST_H
#include <stdint.h>
#include "Student.h"
#include "List.h"

typedef struct StudentList* studentList_t;
studentList_t studentList_createStudentList();
list_listReturnCode studentList_addStudent(studentList_t self, student_t* student);
list_listReturnCode studentList_getStudent(studentList_t self, student_t* student);
list_listReturnCode studentList_reoveStudent(studentList_t self, student_t* student);
uint16_t studentList_noOfStudents(studentList_t self);

#endif //EXERCISES_STUDENTLIST_H