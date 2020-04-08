Box
Topic Coverage
Static members
Interface
Generics
Wildcards
@SuppressWarnings
CS2030 Java Style Guide
Problem Description
In this lab, we are going to extend the Box class with a few more functionalities. Recall that the Box class we built during Lecture 5 is a generic class that can be used to store an item of any reference type.

Level 1
A Box
Build the Box class with the following methods:

A static of method that allows a box to be created with a given object. If null is passed into of, then a null should be returned.
Override the equals method so that we can compare if two boxes are the same. Two boxes are the same if the content of the box equals to each other, as decided by their respective equals method.
When you implement equals, you need to cast an Object object to a Box<T> object so that you can compare the content. Here, you will encounter a warning from the compiler about unchecked conversion. You should consider such a warning carefully and check if your type-casting makes sense. If you have already checked that the Object object you cast has a run-time type of Box, then you can use the annotation

    @SuppressWarnings("unchecked")
to suppress this warning. In general, you should not suppress warnings unless you know what you are doing.

jshell> Box.of(4)
$.. ==> [4]
jshell> Box.of(4).equals(Box.of(4))
$.. ==> true
jshell> Box.of(4).equals(4)
$.. ==> false
jshell> Box.of(Box.of(0)).equals(Box.of(0))
$.. ==> false
jshell> Box.of(Box.of(0)).equals(Box.of(Box.of(0)))
$.. ==> true
jshell> Box.of("string")
$.. ==> [string]
jshell> Box.of("string").equals(Box.of(4))
$.. ==> false
jshell> Box.of("string").equals(Box.of("null"))
$.. ==> false
jshell> Box.of("delights").equals(Box.of("chocolates"))
$.. ==> false
jshell> Box.of(null)
$.. ==> null
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q <list of java files in dependency order> < level1.jsh
Check your styling by issuing the following

$ checkstyle *.java
Level 2
An Empty Box
The of method returns a null if it is given a null. An alternative (some might say, cleaner) design is to make our factory method returns an empty box instead if we try to create a box of null.

Add a method in Box called empty() that creates and returns an empty box, i.e., a box with a null item stored in it.

Since empty boxes are likely common, we want to cache the empty box, that is, create one as a private final static member called EMPTY_BOX, and whenever we need to return an empty box, EMPTY_BOX is returned.

What should the type of EMPTY_BOX be? The type should be general enough to hold a box of any type (Box<Cruise>, Box<Circle>, etc). EMPTY_BOX should, therefore, be assigned the most general generic Box type. Hint: It is not Box<Object>.

Your method empty() should do nothing more than to type-cast EMPTY_BOX to the right type (i.e., to Box<T>) before returning, to ensure that type consistency.

Note that here, we are casting a more general generic type to a more specific generic type, so the compiler gives us an unchecked conversion warning. Again, you can use the annotation

    @SuppressWarnings("unchecked")
to suppress this warning. Let me repeat again: in general, you should not suppress warnings unless you know what you are doing. Here, we are sure we can type-cast since an empty box can be treated as a box of any type.

Add a boolean method isPresent that returns true if the box contains something; false if the box is empty.

Finally, add a static factory method called ofNullable, which behaves just like of if the input is non-null, and returns an empty box if the input is null.

jshell> Box.ofNullable(4)
$.. ==> [4]
jshell> Box.ofNullable("string")
$.. ==> [string]
jshell> Box.ofNullable(null)
$.. ==> []
jshell> Box.empty() == Box.empty()
$.. ==> true
jshell> Box.ofNullable(null) == Box.empty()
$.. ==> true
jshell> Box.ofNullable(null).equals(Box.empty())
$.. ==> true
jshell> Box.ofNullable(null).equals(Box.of(null))
$.. ==> false
jshell> 
jshell> Box.ofNullable("string").isPresent()
$.. ==> true
jshell> Box.ofNullable(null).isPresent()
$.. ==> false
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q <list of java files in dependency order> < level2.jsh
Check your styling by issuing the following
$ checkstyle *.java
Level 3
Checking the Content of the Box
So far, we can only keep things inside our Box, which is not very exciting. In the rest of the levels, we will expand Box to support operations on the item inside.

Let's start by writing a generic interface called BooleanCondition<T> with a single abstract boolean method test. The method test should take a single argument of type T.

Now, one can create a variety of classes by implementing this interface. By implementing the method test differently, we can create different conditions and check if the item contained in the box satisfies a given condition or not.

Create a method filter in Box that takes in a BooleanCondition as a parameter. The method filter should return an empty box if the item in the box failed the test (i.e., the call to test returns false). Otherwise, filter leaves the box untouched and returns the box as it is. Calling filter on an empty box just returns an empty box.

jshell> class AlwaysTrue<T> implements BooleanCondition<T> {
   ...>   public boolean test(T t) { return true; } 
   ...> }
jshell> class AlwaysFalse<T> implements BooleanCondition<T> {
   ...>   public boolean test(T t) { return false; } 
   ...> }
jshell> 
jshell> Box.ofNullable(4).filter(new AlwaysTrue<>())
$.. ==> [4]
jshell> Box.ofNullable(null).filter(new AlwaysTrue<>())
$.. ==> []
jshell> 
jshell> Box.ofNullable("string").filter(new AlwaysFalse<>())
$.. ==> []
jshell> Box.empty().filter(new AlwaysFalse<>())
$.. ==> []
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q <list of java files in dependency order> < level3.jsh
Check your styling by issuing the following
$ checkstyle *.java
Level 4
Implement Your Own Conditions
The test cases above show you how you could create a class that implements a BooleanCondition. Now you should implement your own.

Create a class called DivisibleBy that implements BooleanCondition on Integer that checks if a given integer is divisible by another integer. The test method should return true if it is divisible; return false otherwise.

Create another class called LongerThan that implements BooleanCondition on String that checks if a given string is longer than a given limit. The test method should return true if it is longer; return false otherwise.

jshell> new DivisibleBy(5).test(4)
$.. ==> false
jshell> new DivisibleBy(5).test(10)
$.. ==> true
jshell> Box.of(10).filter(new DivisibleBy(2))
$.. ==> [10]
jshell> Box.of(3).filter(new DivisibleBy(2))
$.. ==> []
jshell> Box.of("hello").filter(new DivisibleBy(10))
|  Error:
|  incompatible types: DivisibleBy cannot be converted to BooleanCondition<? super java.lang.String>
|  Box.of("hello").filter(new DivisibleBy(10))
|                         ^-----------------^
jshell> Box<Integer> empty = Box.empty()
jshell> empty.filter(new DivisibleBy(10))
$.. ==> []
jshell> 
jshell> new LongerThan(6).test("123456")
$.. ==> false
jshell> new LongerThan(3).test("")
$.. ==> false
jshell> Box.of("chocolates").filter(new LongerThan(20))
$.. ==> []
jshell> Box.of("string").filter(new LongerThan(4))
$.. ==> [string]
jshell> Box<String> empty = Box.empty()
jshell> empty.filter(new LongerThan(10))
$.. ==> []
jshell> 

Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q <list of java files in dependency order> < level4.jsh
Check your styling by issuing the following
$ checkstyle *.java
Level 5
Transforming a Box
Now, we are going to write an interface (along with its implementations) and a method in Box that allows a box to be transformed into another box, possibly containing a different type.

First, create an interface called Transformer<T,U> with an abstract method called transform that takes in an argument of generic type T and returns a value of generic type U. (This might be the first time you see a generic type with more than one type parameters, but not to worry, it behaves the same, just more type parameters!)

Write a method called map in the class Box that takes in a Transformer, and use the given Transformer to transform the box (and the value inside) into another box of type Box<U>. Calling map on an empty box should just return an empty box.

In addition, implement your own Transformer in a class called LastDigitsOfHashCode to transform the item of the box into an integer, the value of which is the last k digits of the hash code of the content of the original box. The value k is passed in through the constructor of the LastDigitsOfHashCode object.

If you find yourself in a situation where map needs to accept objects of different types, consider using bounded wildcards!

jshell> class AddOne implements Transformer<Integer,Integer> {
   ...>   public Integer transform(Integer t) { return t + 1; } 
   ...> }
jshell> class StringLength implements Transformer<String,Integer> {
   ...>   public Integer transform(String t) { return t.length(); }
   ...> }
jshell> 
jshell> Box.of(4).map(new AddOne())
$.. ==> [5]
jshell> Box<Integer> empty = Box.empty()
jshell> empty.map(new AddOne())
$.. ==> []
jshell> 
jshell> Box.of("string").map(new StringLength())
$.. ==> [6]
jshell> Box.of("string").map(new StringLength()).map(new AddOne())
$.. ==> [7]
jshell> Box.of("string").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne())
$.. ==> []
jshell> Box.of("chocolates").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne())
$.. ==> [11]
jshell> 
jshell> Box<String> empty = Box.empty()
jshell> empty.map(new StringLength())
$.. ==> []
jshell> 
jshell> class AlwaysNull implements Transformer<Integer,Object> {
   ...>   public Object transform(Integer t) { return null; }
   ...> }
jshell> Box.of(4).map(new AlwaysNull())
$.. ==> []
jshell> 
jshell> new LastDigitsOfHashCode(4).transform("string")
$.. ==> 5903
jshell> new LastDigitsOfHashCode(4).transform(123456)
$.. ==> 3456
jshell> Box.of("string").map(new LastDigitsOfHashCode(2))
$.. ==> [3]
jshell> Box.of(123456).map(new LastDigitsOfHashCode(5))
$.. ==> [23456]
jshell> 

Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q <list of java files in dependency order> < level5.jsh
Check your styling by issuing the following
$ checkstyle *.java
Level 6
Box in a Box
The Transformer interface allows us to transform the content of the box from one type into any other type, including a box! You have seen examples above where we have a box inside a box: Box.of(Box.of(0)).

Now, implement your own Transformer in a class called BoxIt<T> to transform an item into a box containing the item. The corresponding type T is transformed into Box<T>. This transformer, when invoked with map, results in a new box within the box.

jshell> Box.of(4).map(new BoxIt<>())
$.. ==> [[4]]
jshell> Box.of(Box.of(5)).map(new BoxIt<>())
$.. ==> [[[5]]]
jshell> Box.ofNullable(null).map(new BoxIt<>())
$.. ==> []
jshell> 

Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q <list of java files in dependency order> < level6.jsh
Check your styling by issuing the following
$ checkstyle *.java
