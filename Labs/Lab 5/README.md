Immutable List
Topic Coverage
Generics
Exceptions
Functional Interfaces
Lambdas
Variable-Length Arguments
Problem Description
In this lab, we are going to explore functional interfaces, lambda expressions, and exceptions. This lab serves as an introduction to functional programming and will help you for when we encounter streams later in the module.

Task
The List implementations from the Java Collection Framework are mutable. In this lab, you will build you own an immutable list class (ImmutableList) that implements various methods to manipulate this list, by wrapping an immutable API over a mutable List object. This pattern is sometimes known as the immutable delegation class class and comes with a performance overhead. Note that a much more efficient implementation of an immutable list is possible (using a recursive data structure), but let's leave that to a future lab on infinite lists.

In this lab, by wrapping around an internal mutable List, implement the methods add, remove, replace, map, filter, limit, sorted, and toArray.

Level 1
Creating a Generic Immutable List
Create an immutable class ImmutableList that implements a list. The list should be ordered, i.e., the position of the items in the list matters. Your immutable list should not implement the List interface but should contain a List instead.

Your class should have two constructors, both accept items to populate your new immutable list. The first one takes in a generic List containing the items as an argument; the second takes in a sequence of items as variable-length arguments (or varargs). Variable-length arguments allow you to pass an unspecified number of arguments to a method or constructor. You can read up about varargs if you are unfamiliar with this Java feature.

Using varargs with generic type parameters could be unsafe. Varargs is a syntactic sugar for an array, which is covariant in Java and potentially unsafe as you have seen in class. To tell the compiler that you know what you are doing is type-safe, when you declare a method that takes in varargs with a generic type parameter, add an annotation @SafeVarargs before the method.

Your class should have add (append an item to the list), replace (replace all occurences of an item with another), and remove (remove the first occurence of an item) methods. Remember your list has to be immutable.

Your class should also have an appropriate toString method which prints out the contents of the List.

Remember, you should not suppress warnings unless you know what you are doing. (We will tell you when you can do so in a later level).

jshell> /open ImmutableList.java
jshell> new ImmutableList<Integer>(1,2,3)
$.. ==> [1, 2, 3]
jshell> new ImmutableList<Integer>(Arrays.asList(1,2,3))
$.. ==> [1, 2, 3]
jshell> List<Integer> mList = new ArrayList<>(Arrays.asList(1,2,3))
jshell> ImmutableList<Integer> imList = new ImmutableList<>(mList)
jshell> imList.remove(3)
$.. ==> [1, 2]
jshell> imList
imList ==> [1, 2, 3]
jshell> imList.remove(3).add(2)
$.. ==> [1, 2, 2]
jshell> imList
imList ==> [1, 2, 3]
jshell> imList.remove(6)
$.. ==> [1, 2, 3]
jshell> imList.add(1).replace(1,3)
$.. ==> [3, 2, 3, 3]
jshell> imList.add(1).replace(1,1)
$.. ==> [1, 2, 3, 1]
jshell> imList.replace(6,3)
$.. ==> [1, 2, 3]
jshell> mList.set(0,10)
$.. ==> 1
jshell> mList
mList ==> [10, 2, 3]
jshell> imList
imList ==> [1, 2, 3]
jshell> Integer[] array = {1, 2, 3}
jshell> ImmutableList<Integer> imList = new ImmutableList<>(array)
jshell> array[0] = 10
$.. ==> 10
jshell> imList
imList ==> [1, 2, 3]
jshell> new ImmutableList<>(List.of(4,5,6)).add(7)
$.. ==> [4, 5, 6, 7]
jshell> ImmutableList<String> stringList = new ImmutableList<>(Arrays.asList("One","Two","Three"))
jshell> stringList.add("Four");
$.. ==> [One, Two, Three, Four]
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q ImmutableList.java < level1.jsh
The -Xlint:rawtypes flag would warn you if you forget to specify generics and use raw types instead.

Check your styling by issuing the following

$ checkstyle *.java
Level 2
Implement Map and Filter Methods
Now, let's create two new methods in the ImmutableList class.

First, the filter method. The filter method will return an ImmutableList with elements based on a Predicate you pass to it. Remember that Predicate is a generic functional interface, and therefore it has one method to implement: test.

Next, implement the map method. The map method will return an ImmutableList in which all elements are transformed based on a Function you pass to it. Again, Function is a generic functional interface, and therefore it has one method to implement: apply. Remember that the input type may not be the same as the output type: consider a mapping from String to Integer using the length() method.

Try passing your own Predicates and Functions to these methods! You can use lambda expressions as in the following test cases below.

jshell> /open ImmutableList.java
jshell> new ImmutableList<Integer>(1,2,3).filter(x -> x % 2 == 0)
$.. ==> [2]
jshell> new ImmutableList<String>("one", "two", "three").filter(x -> x.hashCode()%10 > 5)
$.. ==> [two, three]
jshell> Predicate<Object> p = x -> x.hashCode()%10 == 0
jshell> new ImmutableList<String>("one", "two", "three").filter(p)
$.. ==> []
jshell> ImmutableList<Integer> list = new ImmutableList<String>("one", "two", "three").map(x -> x.length())
jshell> /var list
|    ImmutableList<Integer> list = [3, 3, 5]
jshell> Function<Object,Integer> f = x -> x.hashCode()
jshell> ImmutableList<Number> list = new ImmutableList<String>("one", "two", "three").map(f)
jshell> /var list
|    ImmutableList<Number> list = [110182, 115276, 110339486]
jshell> new ImmutableList<Integer>(1,2,3).filter(x -> x > 3).map(x -> x + 1)
$.. ==> []
jshell> new ImmutableList<Integer>(1,2,3).filter(x -> x > 2).map(x -> x + 1)
$.. ==> [4]
jshell> new ImmutableList<Integer>(1,2,3).map(x -> x + 1).filter(x -> x > 2)
$.. ==> [3, 4]
jshell> new ImmutableList<String>().filter(s -> s.endsWith("s"))
$.. ==> []
jshell> new ImmutableList<String>().map(s -> s.endsWith("s"))
$.. ==> []
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q ImmutableList.java < level2.jsh
Check your styling by issuing the following
$ checkstyle *.java
 

Level 3
Implementing the Limit Method
Let's create a new method limit in the ImmutableList class. The limit method takes in a long value and then returns an ImmutableList truncated to the length specified by that long value.

Consider the case where you pass in a negative number. In this case, let's throw an IllegalArgumentException with the exception message "limit size < 0".

It is important to note that, the test cases below catch unchecked exceptions for the purpose of testing only. It is an unusual coding practice to catch unchecked exceptions, as unchecked exceptions are usually caused by bugs or improper use of APIs, and the application usually cannot recover from such exceptions.

jshell> /open ImmutableList.java
jshell> new ImmutableList<Integer>(1,2,3).limit(1)
$.. ==> [1]
jshell> new ImmutableList<Integer>(1,2,3).limit(10)
$.. ==> [1, 2, 3]
jshell> ImmutableList<Integer> list = new ImmutableList<Integer>(1,2,3)
jshell> list.limit(0)
$.. ==> []
jshell> list
list ==> [1, 2, 3]
jshell> list = list.limit(0)
jshell> try {
   ...>   new ImmutableList<Integer>(1,2,3).limit(-1);
   ...> } catch (IllegalArgumentException e) {
   ...>   System.out.println(e);
   ...> }
java.lang.IllegalArgumentException: limit size < 0
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q ImmutableList.java < level3.jsh
Check your styling by issuing the following
$ checkstyle *.java
 

Level 4
Implement the Sorted Method
Let's create a new method sorted in the ImmutableList class.

The sorted method takes in no argument, and will return an ImmutableList sorted by using the natural order of the items in the ImmutableList. Thus, similar to java.util.PriorityQueue, we expect the items in the ImmutableList to implement the Comparable interface if this sorted method is invoked.

Consider the case where the items in ImmutableList is of a type which does not implement Comparable. In this case, let's throw an IllegalStateException with the exception message "List elements do not implement Comparable".

What if we would like to sort the list using an arbitrary Comparator? Let's now create an overloaded sorted method that takes in a Comparator and returns the ImmutableList as sorted by this Comparator.

What happens if we pass a null Comparator? In this case, let's throw a NullPointerException with the exception message "Comparator is null".

In this level, you will need to use the instanceof operator and then perform type casting. Type casting to a generic type causes Java compiler to alert you with a warning of possible failure during run time due to type safety. For this instance, and this instance only, you can use @SuppressWarnings("unchecked") to turn off the warning. It is a good programming practice to limit the scope of @SuppressWarnings to be the smallest possible. For this lab onwards, you should only write @SuppressWarnings above the line of a local variable assignment statement that triggers the warning.

jshell> /open ImmutableList.java
jshell> new ImmutableList<Integer>(3,2,1).sorted()
$.. ==> [1, 2, 3]
jshell> class A { }
jshell> try {
   ...>   new ImmutableList<A>(new A(), new A()).sorted();
   ...> } catch (IllegalStateException e) { 
   ...>   System.out.println(e);
   ...> }
java.lang.IllegalStateException: List elements do not implement Comparable
jshell> ImmutableList<Integer> list = new ImmutableList<Integer>().sorted()
jshell> list
list ==> []
jshell> new ImmutableList<A>().sorted()
$.. ==> []
jshell> list
list ==> []
jshell> list = new ImmutableList<Integer>(1,2,3)
jshell> list.sorted((x,y) -> y - x)
$.. ==> [3, 2, 1]
jshell> list
list ==> [1, 2, 3]
jshell> try {
   ...>   new ImmutableList<Integer>(1,2,3).sorted(null);
   ...> } catch (NullPointerException e) {
   ...>   System.out.println(e);
   ...> }
java.lang.NullPointerException: Comparator is null
jshell> new ImmutableList<Integer>(4,5,3,6,7,2,1).filter(x -> x % 2 == 0).sorted().map(i -> "P" + i);
$.. ==> [P2, P4, P6]
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q ImmutableList.java < level4.jsh
Check your styling by issuing the following
$ checkstyle *.java
 

Level 5
Implement a toArray Method
Create an overloaded method toArray which takes in either no argument, or an array as an argument. The toArray method without argument will return the items in the list as an array of type Object[]. The toArray method with an array as an argument is a generic method that will return the items in the list in an array of the same type as the argument. The behavior of toArray of your list is similar to that of toArray of List, with the only difference being the error message associated with the exception thrown.

When an array of the wrong type is being passed into the ImmutableList, Let's throw an ArrayStoreException with the exception message "Cannot add element to array as it is the wrong type".

What about how the method handles passing a null argument? Let's throw an NullPointerException with the exception message "Input array cannot be null".

jshell> /open ImmutableList.java
jshell> new ImmutableList<Integer>(1,2,3).toArray()
$.. ==> Object[3] { 1, 2, 3 }
jshell> new ImmutableList<Integer>().toArray()
$.. ==> Object[0] {  }
jshell> new ImmutableList<Integer>(1,2,3).toArray(new Integer[0])
$.. ==> Integer[3] { 1, 2, 3 }
jshell> try {
   ...>   new ImmutableList<Integer>(1,2,3).toArray(new String[0]);
   ...> } catch (ArrayStoreException e) {
   ...>   System.out.println(e);
   ...> }
java.lang.ArrayStoreException: Cannot add element to array as it is the wrong type
jshell> try {
   ...>   new ImmutableList<Integer>(1,2,3).toArray(null);
   ...> } catch (NullPointerException e) {
   ...>   System.out.println(e);
   ...> }
java.lang.NullPointerException: Input array cannot be null
jshell> /exit

Check the format correctness of the output by running the following on the command line:

$ javac -Xlint:rawtypes *.java
$ jshell -q ImmutableList.java < level5.jsh
Check your styling by issuing the following
$ checkstyle *.java
