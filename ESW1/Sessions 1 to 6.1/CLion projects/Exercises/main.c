#include <stdio.h>
#include "List.h"
#include "Student.h"
#include "StudentList.h"
#include "StudentHandler.h"

int main() {
    printf("Hello, World!\n");
    /*
    list_t list;
    list = list_createList();
    int num1 = 8;
    int num2 = 567;
    int num3 = 444;
    int numGet;

    list_addItem(list, num1);
    list_addItem(list, num2);
    list_addItem(list, num3);

    list_getItem(list, &numGet, 0);
    printf("%d\n", numGet);
    list_getItem(list, &numGet, 1);
    printf("%d\n", numGet);
    list_getItem(list, &numGet, 2);
    printf("%d\n", numGet);

    numGet = list_noOfItems(list);
    printf("List length: %d\n", numGet);

    numGet = 567;
    list_removeItem(list,&numGet);
    list_getItem(list, &numGet, 0);
    printf("%d\n", numGet);
    list_getItem(list, &numGet, 1);
    printf("%d\n", numGet);

    if(list_destroy(list) == 0){
        printf("List destroyed\n");
    } else{
        printf("List not destroyed\n");
    }


    list_t listStrings;
    listStrings = list_createList();

    char name[] = "Rokas";
    char lastName[] = "Barasa";
    char nationality[] = "Lithuanian";
    char* string;

    list_addItem(listStrings, name);
    list_addItem(listStrings, lastName);
    list_addItem(listStrings, nationality);
    list_getItem(listStrings, &string, 2);
    printf("%s\n", string);
    //printf("%s\n", *string);
    //printf("%s\n", string);
    printf("%s\n", name);

    student_t student11;
    student11 = student_createStudent("Rokas", "Barasa", 1, "Lithuanian");
    student_t student15;
    student15 = student_createStudent("Kaka", "fgsd", 2, "fdgadfh");
    student_t student13;
    student13 = student_createStudent("gjh", "dfg", 3, "jjjjjj");

    list_t listForStudents1;
    listForStudents1 = list_createList();

    studentList_t studentList11;
    studentList11 = studentList_createStudentList(listForStudents1);

    studentList_addStudent(studentList11, student11);
    studentList_addStudent(studentList11, student15);
    studentList_addStudent(studentList11, student13);

    student_t student44;
    student44 = studentList_getStudent(studentList11, 0);
    //studentList_removeStudent(studentList, student5);
    student_printInfo(student44);
    int num;
    num = studentList_noOfStudents(studentList11);
    printf("%d\n", num);




*/
    // STUDENT EXERCISE Propper
    student_t student1;
    student1 = student_createStudent("Rokas", "Barasa", 0, "Lithuanian");
    student_t student2;
    student2 = student_createStudent("Kaka", "fgsd", 1, "fdgadfh");
    student_t student3;
    student3 = student_createStudent("gjh", "dfg", 2, "jjjjjj");

    list_t listForStudents;
    listForStudents = list_createList();

    studentList_t studentList;
    studentList = studentList_createStudentList(listForStudents);

    studentHandler_t studentHandler;
    studentHandler = studentHandler_createStudentHandler(studentList);

    studentHandler_addStudent(studentHandler, student1);
    studentHandler_addStudent(studentHandler, student2);
    studentHandler_addStudent(studentHandler, student3);

    studentHandler_printAllStudentsInfo(studentHandler);


    student_t* studentGotten = studentHandler_searchStudentByLastName(studentHandler, "fgsd");
    student_printInfo(studentGotten);
    studentGotten = studentHandler_searchStudentById(studentHandler, 2);
    student_printInfo(studentGotten);
    studentHandler_printStudentInfo(studentHandler, studentGotten);

    int numNew = studentList_noOfStudents(studentList);
    printf("%d", numNew);
    studentList_removeStudent(studentList, student2);
    numNew = studentList_noOfStudents(studentList);
    printf("%d", numNew);

    return 0;
}