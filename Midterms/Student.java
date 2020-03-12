//interfaces

interface StudentModuleMarks {

    getModuleMarks();

}

interface StudentScholarshipsLoans {

    getScholarshipsLoans();

}

//classes

class Module {

    String moduleName;

    double marks;

    void awardMarks(double value) {

        this.marks = value;

    }

    double getMarks() {
        return marks;
    }

}

class ScholarshipLoans {

    String name;

}

class Student implements StudentScholarshipsLoans , StudentModuleMarks {

    List<Module> listOfModules;

    List<ScholarshipLoans> listOfScholarshipLoans;

    void read(Module module) {

        listOfModules.add(module);

   }

    void awardMarksToModule(Module module, double marks) {

        listOfModules.get(module).awardMarks(marks);

    }

    List<Modules> getModuleMarks() {
        return listOfModules;
    }   

    List<ScholarshipLoans> getScholarshipsLoans() {
        return listOfScholarshipLoans;
    }

}

class AdminOffice {
    List<StudentModuleMarks> listOfStudent;
}

class FinanceOffice {
    List<StudentScholarshipsLoans> listOfStudent;
}