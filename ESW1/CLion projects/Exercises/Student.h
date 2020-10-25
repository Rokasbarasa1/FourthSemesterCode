//
// Created by Rokas on 24/10/2020.
//

#ifndef EXERCISES_STUDENT_H
#define EXERCISES_STUDENT_H

typedef struct Student* student_t;
student_t* student_createStudent(char* firstName, char* lastName, int studentId, char* nationality);
void student_printInfo(student_t self);

#endif //EXERCISES_STUDENT_H
