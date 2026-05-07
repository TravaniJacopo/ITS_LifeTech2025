package it.itsvolta.corsobackend2026.studentmanager.dto;


public class StudentDto {
        private String code;
        private String name;
        private String surname;
        private int age;

        public StudentDto() {
        }

        public StudentDto(String code, String name, String surname, int age) {
            this.code = code;
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        public String getCode () {
            return code;
        }
         public String getName () {
            return name;
        }
         public String getSurname () {
            return surname;
        }
         public int getAge () {
            return age;
        }

        public void setCode (String code){
            this.code = code;
        }
         public void setName (String name){
            this.name = name;
        }
         public void setSurname (String surname){
            this.surname = surname;
        }
         public void setAge (int age){
            this.age = age;
        }
}
