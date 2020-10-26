//
// Created by Rokas on 24/10/2020.
//

#include "Student.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>


typedef struct Student {
    char firstName[25];
    char lastName[25];
    uint16_t studentID;
    char nationality[25];
}Student;

student_t* student_createStudent(char* firstName, char* lastName, uint16_t studentId, char* nationality) {
    Student* new_student = calloc(sizeof(Student), 1);
    if (NULL == new_student) {
        return 3;
    }
    strncpy(new_student->firstName,firstName, sizeof(new_student->firstName)-1);
    strncpy(new_student->lastName, lastName, sizeof(new_student->firstName) - 1);
    new_student->studentID = studentId;
    strncpy(new_student->nationality, nationality, sizeof(new_student->firstName) - 1);
    return new_student;
}

void student_printInfo(student_t self) {
    printf("%s, %s, %d, %s\n", self->firstName, self->lastName, self->studentID, self->nationality);
}

uint16_t student_getId(student_t self){
    return self->studentID;
}

char* student_getLastName(student_t self){
    return self->lastName;
}