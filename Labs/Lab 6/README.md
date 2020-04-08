Making Things Optional
Review Topics
Inheritance
Overriding
Interface
Generic class
Topic Coverage
Bounded Type Parameter
HashMap
Optional
Functional Interfaces
Exception
Problem Description
In this lab, we are going to see how we can use Optional to simplify our code, allowing us to focus on the core application logic instead of worrying about edge cases. This lab also involves using the HashMap class from Java Collections and more functional interfaces from Java: Consumer, Supplier, and Runnable.

Task
You are going to write two versions of a program to solve the same problem. Your task is to read in a roster of students, the modules they take, the assessments they have completed, and the grade for each assessment. Then, given a query consisting of a triplet: a student, a module, and an assessment, retrieve the corresponding grade.

For instance, if the input is:

Steve CS1010 Lab3 A
Steve CS1231 Test A+
Bruce CS2030 Lab1 C
and the query is Steve CS1231 Test, the program should print A+.

 

In our scenario, a roster has zero or more students; a student takes zero or more modules, a module has zero or more assessments, and each assessment has exactly one grade. Each of these entities can be uniquely identified by a string.

The following levels will guide you through solving this problem in two different ways.

Preamble
Map
Map<K,V> is a generic interface from the Java Collection Framework, the implementation of which is useful for storing a collection of items and retrieving an item. It maintains a map (aka dictionary) between keys (of type K) and values (of type V). The two core methods are (i) put, which stores a (key, value) pair into the map, and (ii) get, which returns the value associated with a given key if the key is found or returns null otherwise.

The following examples show how the Map<K,V> interface and its implementation HashMap<K,V> can be used.

jshell> Map<String,Integer> map = new HashMap<>();
jshell> map.put("one", 1);
$.. ==> null
jshell> map.put("two", 2);
$.. ==> null
jshell> map.put("three", 3);
$.. ==> null
jshell> map.get("one")
$.. ==> 1
jshell> map.get("four")
$.. ==> null
jshell> map.entrySet()
$.. ==> [one=1, two=2, three=3]
jshell> for (Map.Entry e: map.entrySet()) {
   ...>  System.out.println(e.getKey() + ":" + e.getValue());
   ...> }
one:1
two:2
three:3
jshell> 

Level 1
Base Interface and Class
We will start by implementing two general types: a generic interface and a generic class that will help us later.

We are going to store the students, the modules they take, and the assessments they completed in maps as we read them from the input, so that we can retrieve them later when we answer the queries. As such, each of these entities needs to have a key -- a unique identifier.

Write an interface called Keyable<K>, where K is the type of the key. Keyable<K> has an abstract method K getKey() that returns the key.

Each of the roster, student, and module also serves as a container. A roster contains zero or more students stored in a map; a student contains zero or more modules stored in a map, and a module contains zero or more assessment stored in a map. We will factor out their commonalities in a class called KeyableMap.

KeyableMap is a generic class that wraps around a key (it, therefore, implements Keyable<T>) and a Map<K,V>. KeyableMap models an entity that contains a map, but is also itself contained in another container (e.g., a student contains a map of modules but is contained in a roster). KeyableMap has three type parameters:

T is the type of the key of KeyableMap;
K is the type of the key of items stored in the map; and
V is the type of the value of items stored in the map; V must be a subtype of Keyable<K>.
 

The class KeyableMap is a mutable class -- we made this decision since the Map implementation in Java Collection Framework is mutable. KeyableMap provides two core methods:

get(K key) which returns the item with the given key;
put(V item) which adds the key-value pair (item.getKey(),item) into the map.
The return type of get should be V. The return type of put is a bit tricky. To allow method chaining, like we always do in CS2030, we require put to return the type of the caller. In KeyableMap the return type is just KeyableMap<..> (type parameters omitted).

jshell> /open Keyable.java
jshell> /open KeyableMap.java
jshell> class CallerID implements Keyable<Integer> { 
   ...>   int number; 
   ...>   String name;
   ...>   CallerID(int number, String name) { this.number = number; this.name = name; }
   ...>   @Override public Integer getKey() { return this.number; }
   ...>   @Override public String toString() { return this.number + ": " + this.name; }
   ...> }
jshell> KeyableMap<String,Integer,CallerID> map = new KeyableMap<>("phonebook")
jshell> map.put(new CallerID(65164463, "OWT")).put(new CallerID(66752378, "Blocked"));
$.. ==> phonebook: {66752378: Blocked, 65164463: OWT}
jshell> map.get(65164463)
$.. ==> 65164463: OWT
jshell> map.get(66752378)
$.. ==> 66752378: Blocked
jshell> map.get(65432100)
$.. ==> null
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q < level1.jsh
Check your styling by issuing the following

$ checkstyle *.java
Level 2
Assessment, Module, Student
Now, let's write the classes that implement the three entities in the problem, starting with Assessment. The Assessment class models that assessment of a student in a module, and it maintains two fields: the name of the assessment (of type String) and the grade (of type String). The Assessment class does not do much beyond storing these two fields, thus, we provide a getter getGrade() to get the grade of the assessment. The name of the assessment is unique within the module and it serves as a key. Assessment, therefore, implements the Keyable interface.

Next, let’s implement classes Student and Module by extending the generic class KeyableMap.

A Module models a module taken by a specific student and encapsulates the assessments taken by the students under the module. A module is uniquely identified by a module code (of type String) and can have zero or more Assessments.

A Student is uniquely identified by a String id and encapsulates one or more Modules taken by the student.

For these two classes, the put method in the parent (i.e., KeyableMap) returns the parent type (i.e., KeyableMap), causing type mismatch when we try to chain the put method calls together. As such, we should override the put method from KeyableMap and change the return type to the corresponding subclasses. E.g., Student should override put to return a Student through a (guaranteed to be safe) type casting.

(Note: An alternative is to make put a generic method with a bounded type parameter in KeyableMap and relies on an unchecked type cast. But we let the more advanced students explore this option on their own).

jshell> /open Keyable.java
jshell> /open KeyableMap.java
jshell> /open Assessment.java
jshell> /open Module.java
jshell> /open Student.java
jshell> new Assessment("Lab1", "B")
$.. ==> {Lab1: B}
jshell> new Assessment("Lab1", "B").getGrade()
$.. ==> "B"
jshell> new Assessment("Lab1", "B").getKey()
$.. ==> "Lab1"
jshell> new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+"))
$.. ==> CS2040: {{Lab1: B}, {Lab2: A+}}
jshell> new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab1")
$.. ==> {Lab1: B}
jshell> new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab2")
$.. ==> {Lab2: A+}
jshell> new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab3")
$.. ==> null
jshell> new Module("CS2040").put(new Assessment("Lab1", "B")).put(new Assessment("Lab2","A+")).get( "Lab1").getGrade()
$.. ==> "B"
jshell> new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B")))
$.. ==> Tony: {CS2040: {{Lab1: B}}}
jshell> new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040" )
$.. ==> CS2040: {{Lab1: B}}
jshell> new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040" ).get("Lab1")
$.. ==> {Lab1: B}
jshell> new Student("Tony").put(new Module("CS2040").put(new Assessment("Lab1", "B"))).get("CS2040" ).get("Lab1").getGrade()
$.. ==> "B"
jshell> Student natasha = new Student("Natasha");
jshell> natasha.put(new Module("CS2040").put(new Assessment("Lab1", "B")))
$.. ==> Natasha: {CS2040: {{Lab1: B}}}
jshell> natasha.put(new Module("CS2030").put(new Assessment("PE", "A+")).put(new Assessment("Lab2",  "C")))
$.. ==> Natasha: {CS2030: {{Lab2: C}, {PE: A+}}, CS2040: {{Lab1: B}}}
jshell> Student tony = new Student("Tony");
jshell> tony.put(new Module("CS1231").put(new Assessment("Test", "A-")))
$.. ==> Tony: {CS1231: {{Test: A-}}}
jshell> tony.put(new Module("CS2100").put(new Assessment("Test", "B")).put(new Assessment("Lab1", " F")))
$.. ==> Tony: {CS1231: {{Test: A-}}, CS2100: {{Lab1: F}, {Test: B}}}
jshell> 

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q < level2.jsh
Check your styling by issuing the following
$ checkstyle *.java
 

Level 3
Roster
A Roster encapsulates all students and is another subclass of KeyableMap. Implement Roster in this level.

Add a method called getGrade in Roster to answer the query from the user. The method takes in three String parameters, corresponds to the student id, the module code, and the assessment name, and returns the corresponding grade. If there is no such student, or the student does not take the given module, or the module does not have the corresponding assessment, then throws a NoSuchRecordException. You have to write your own NoSuchRecordException.

jshell> /open Keyable.java
jshell> /open KeyableMap.java
jshell> /open Assessment.java
jshell> /open Module.java
jshell> /open Student.java
jshell> /open Roster.java
jshell> /open NoSuchRecordException.java
jshell> Student natasha = new Student("Natasha");
jshell> natasha.put(new Module("CS2040").put(new Assessment("Lab1", "B")))
$.. ==> Natasha: {CS2040: {{Lab1: B}}}
jshell> natasha.put(new Module("CS2030").put(new Assessment("PE", "A+")).put(new Assessment("Lab2",  "C")))
$.. ==> Natasha: {CS2030: {{Lab2: C}, {PE: A+}}, CS2040: {{Lab1: B}}}
jshell> Student tony = new Student("Tony");
jshell> tony.put(new Module("CS1231").put(new Assessment("Test", "A-")))
$.. ==> Tony: {CS1231: {{Test: A-}}}
jshell> tony.put(new Module("CS2100").put(new Assessment("Test", "B")).put(new Assessment("Lab1", " F")))
$.. ==> Tony: {CS1231: {{Test: A-}}, CS2100: {{Lab1: F}, {Test: B}}}
jshell> Roster roster = new Roster("AY1920").put(natasha).put(tony)
jshell> roster
roster ==> AY1920: {Natasha: {CS2030: {{Lab2: C}, {PE: A+}}, CS2040: {{Lab1: B}}}, Tony: {CS1231: {{Test: A-}}, CS2100: {{Lab1: F}, {Test: B}}}}
jshell> roster.get("Tony").get("CS1231").get("Test").getGrade()
$.. ==> "A-"
jshell> roster.get("Natasha").get("CS2040").get("Lab1").getGrade()
$.. ==> "B"
jshell> roster.getGrade("Tony","CS1231","Test")
$.. ==> "A-"
jshell> roster.getGrade("Natasha","CS2040","Lab1")
$.. ==> "B"
jshell> try {
   ...>   roster.getGrade("Steve","CS1010","Lab1");
   ...> } catch (NoSuchRecordException e) {
   ...>   System.out.println(e.getMessage());
   ...> }
No such record: Steve CS1010 Lab1
jshell> try {
   ...>   roster.getGrade("Tony","CS1231","Exam");
   ...> } catch (NoSuchRecordException e) {
   ...>   System.out.println(e.getMessage());
   ...> }
No such record: Tony CS1231 Exam
jshell> 

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q < level3.jsh
Check your styling by issuing the following
$ checkstyle *.java
 

Level 4
Main
Now use the classes that you have built and write a Main class to solve the following:

Read the following from the standard input:

The first token read is an integer N, indicating the number of records to be read.
The subsequent inputs consist of N records, each record consists of four words, separated by one or more spaces. The four words correspond to the student id, the module code, the assessment name, and the grade, respectively.
The subsequent inputs consist of zero or more queries. Each query consists of three words, separated by one or more spaces. The three words correspond to the student id, the module code, and the assessment name.
For each query, if a match in the input is found, print the corresponding grade to the standard output. Otherwise, print "No Such Record:" followed by the three words given in the query, separated by exactly one space.
 

See sample input and output below. Inputs are underlined.

$ java Main
12
Jack   CS2040 Lab4    B
Jack   CS2040 Lab6    C
Jane   CS1010 Lab1    A
Jane   CS2030 Lab1    A+
Janice CS2040 Lab1    A+
Janice CS2040 Lab4    A+
Jim    CS1010 Lab9    A+
Jim    CS2010 Lab1    C
Jim    CS2010 Lab2    B
Jim    CS2010 Lab8    A+
Joel   CS2030 Lab3    C
Joel   CS2030 Midterm A
Jack   CS2040 Lab4
Jack   CS2040 Lab6
Janice CS2040 Lab1
Janice CS2040 Lab4
Joel   CS2030 Midterm
Jason  CS1010 Lab1
Jack   CS2040 Lab5
Joel   CS2040 Lab3
B
C
A+
A+
A
NoSuchRecordException: No such record: Jason CS1010 Lab1
NoSuchRecordException: No such record: Jack CS2040 Lab5
NoSuchRecordException: No such record: Joel CS2040 Lab3
Compile and check your styling by issuing the following

$ javac -Xlint:rawtypes *.java
$ checkstyle *.java
 

Submit this version of your code to CodeCrunch.

Level 5
Redoing Level 1-4 with Optional
In solving this lab during Level 1 - 4, you should have implemented multiple checks for null, or even encounter unexpected NullPointerException and have to debug your code carefully to get rid of them.

Modern programming languages, especially those that support functional paradigms, provide a construct that allows us to get rid of null in our code. This construct is called Optional in Java (Option in Scala; Maybe in C#).

You will re-solve the problem now using Optional and submit a new version of your code to CodeCrunch. Take a look at the Java documentation of Optional to familiarize yourself with the APIs available. You might find ofNullable, flatMap, map, orElseThrow, and ifPresentOrElse useful.

You can start by changing the return type of get in KeyableMap from V to Optional<V>. This should trigger further changes in the rest of your code.

Note:

On CodeCrunch, we will check for any use of null in your code. Any occurence of the string null would fail the CodeCrunch test. Thus, avoid variable or method names that contain the substring null.
Further, we will disallow the use of methods get and ifPresent from Optional, as the former could cause NullPointerException and the latter is essentially the same as checking for null.
 

Compile and check your styling by issuing the following

$ javac -Xlint:rawtypes *.java
$ checkstyle *.java
