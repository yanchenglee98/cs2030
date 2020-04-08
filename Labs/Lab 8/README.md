Mutable Infinite List
Problem Description
You have seen several versions of ordered list implementations in this class, which abstracted away the looping logic from you, allowing you to pass in lambda expressions to operate on the elements. ImmutableList in Lab 5 is an immutable, finite, list. Stream from the Java API is a mutable, infinite, list. In this lab, we are going to implement our own version of mutable, infinite, list, called InfiniteList.

An InfiniteList is a generic list that can store elements of type T in order where duplicates are allowed. Unlike the previous lab, intermediate operations of InfiniteList should be lazily evaluated. Lazy evaluation allows us to create a list with a potentially infinite number of elements.

The Task
Just like Stream in Java, you will define InfiniteList as an interface, and you will implement each operation on an infinite list as a class that implements the InfiniteList interface. The InfiniteList interface has an abstract get() method, which is used to obtain the next element from the infinite list. The key to implementing an intermediate operation on the infinite list is to override the get method, including the logic of each method in its body.

This task is divided into several levels. Read through all the levels to see how the different levels are related. You need to complete all levels.

We have added some rules on CodeCrunch:

You are not allowed to use raw types nor @SuppressWarnings
As InfiniteList is similar to Java's Stream, you are not allowed to use Stream class or anything from java.util.stream package
You, are, however, allowed to use get, isPresent, and isEmpty from the class Optional.

Level 1: Data Generation
Create an InfiniteList<T> interface with a single abstract method Optional<T> get(). Then add two static members to start the stream pipeline via the following data sources:

InfiniteList.generate(Supplier<? extends T> supplier), which generates an infinite stream of elements produced by the supplier.
InfiniteList.iterate(T seed, UnaryOperator<T> f), which an infinite stream of elements seed, f(seed), f(f(seed), etc.
To implement the methods above, create an abstract class InfiniteListImpl<T> that implements the InfiniteList<T> interface, but keep the get method abstract.

The methods generate and iterate should then return an instance of a concrete subclass of InfiniteListImpl, overriding get with the appropriate logic to return the next element in the list. These concrete implementation of InfiniteListImpl might need to be mutable.

Note that in this lab, we allow get() to be called directly for testing and to illustrate the internals of an infinite list. In practice, get() should be hidden from the clients.

jshell> /open InfiniteList.java
jshell> /open InfiniteListImpl.java
jshell> InfiniteList<String> dots = InfiniteList.generate(() -> ".");
jshell> dots.get()
$.. ==> Optional[.]
jshell> dots.get()
$.. ==> Optional[.]
jshell> dots.get()
$.. ==> Optional[.]
jshell> InfiniteList<Integer> even = InfiniteList.iterate(0, i -> i + 2);
jshell> even.get()
$.. ==> Optional[0]
jshell> even.get()
$.. ==> Optional[2]
jshell> even.get()
$.. ==> Optional[4]
jshell> Random rnd = new Random(1) 
jshell> Supplier<List<Integer>> randListSupplier = () -> List.of(rnd.nextInt());
jshell> InfiniteList<List<? extends Number>> list = InfiniteList.generate(randListSupplier);
jshell> /exit

Check the format correctness of the output by typing the following Unix command

$ checkstyle *.java
$ javac -Xlint:rawtypes *.java
$ jshell -q < level1.jsh
Level 2: limit, forEach, toArray
Now, let's implement three operations on your InfiniteList. The limit operation is an intermediate operation that will return a truncated infinite list (so it is now finite); forEach is a terminal that takes in a Consumer object and consumes each element in the infinite list; toArray converts the Stream into an array of Object.

We will imitate the API of Stream, so two APIs you will implement for InfiniteList are:

InfiniteList<T> limit(long maxSize)
void forEach(Consumer<? super T> action)
Object[] toArray()
You should implement limit by creating an anonymous subclass of InfiniteListImpl with a get method that behaves as if the infinite list it is getting the item from is truncated to the given number of elements.

jshell> /open InfiniteList.java
jshell> /open InfiniteListImpl.java
jshell> InfiniteList<String> dots = InfiniteList.generate(() -> ".").limit(4)
jshell> dots.forEach(System.out::println) 
.
.
.
.
jshell> dots.forEach(System.out::println) 
jshell> InfiniteList<Integer> even = InfiniteList.iterate(0, i -> i + 2).limit(5)
jshell> even.forEach(System.out::println) 
0
2
4
6
8
jshell> even = InfiniteList.iterate(0, i -> i + 2).limit(2)
jshell> even.get()
$.. ==> Optional[0]
jshell> even.get()
$.. ==> Optional[2]
jshell> even.get()
$.. ==> Optional.empty
jshell> InfiniteList.iterate(0, i -> i + 2).limit(0).get()
$.. ==> Optional.empty
jshell> try { 
   ...>   InfiniteList.iterate(0, i -> i + 2).limit(-1);
   ...> } catch (IllegalArgumentException e) {
   ...>   System.out.println(e);
   ...> }
java.lang.IllegalArgumentException: -1
jshell> InfiniteList.iterate(0, i -> i + 1).limit(10).limit(3).toArray()
$.. ==> Object[3] { 0, 1, 2 }
jshell> InfiniteList.iterate(0, i -> i + 1).limit(3).limit(100).toArray()
$.. ==> Object[3] { 0, 1, 2 }
jshell> InfiniteList.generate(() -> 1).limit(0).toArray()
$.. ==> Object[0] {  }
jshell> InfiniteList.generate(() -> 1).limit(2).toArray()
$.. ==> Object[2] { 1, 1 }
jshell> 
jshell> 
jshell> Consumer<Object> blackhole = obj -> {}
jshell> InfiniteList.<Integer>generate(() -> 1).limit(3).forEach(blackhole)
jshell> /exit

Check the format correctness of the output by typing the following Unix command

$ checkstyle *.java
$ javac -Xlint:rawtypes *.java
$ jshell -q < level2.jsh
Level 3: map, filter, takeWhile
Now, implement the following three operations on an infinite list. Again, we are imitating the behaviour of Stream, so we will follow the corresponding specifications of Java's Stream APIs:

<S> InfiniteList<S> map(Function<? super T, ? extends S> mapper)
InfiniteList<T> filter(Predicate<? super T> predicate)
InfiniteList<T> takeWhile(Predicate<? super T> predicate)
Similar to limit, you should implement the three operations above by creating an anonymous subclass of InfiniteListImpl and override its get method.

jshell> /open InfiniteList.java
jshell> /open InfiniteListImpl.java
jshell> InfiniteList<String> s = InfiniteList.generate(() -> "A")
jshell> InfiniteList<String> s = InfiniteList.generate(() -> "A").map(x -> x + "-")
jshell> InfiniteList<Integer> i = InfiniteList.generate(() -> "A").map(x -> x + "-").map(str -> str.length())
jshell> InfiniteList<Integer> i = InfiniteList.generate(() -> "A").map(x -> x + "-").map(str -> str.length())
jshell> InfiniteList.generate(() -> "A").map(x -> x + "-").map(str -> str.length()).limit(4).toArray()
$.. ==> Object[4] { 2, 2, 2, 2 }
jshell> InfiniteList.generate(() -> "A").limit(4).map(x -> x + "-").map(str -> str.length()).toArray()
$.. ==> Object[4] { 2, 2, 2, 2 }
jshell> InfiniteList.generate(() -> "A").map(x -> x + "-").limit(4).map(str -> str.length()).toArray()
$.. ==> Object[4] { 2, 2, 2, 2 }
jshell> 
jshell> InfiniteList<Integer> i = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0)
jshell> InfiniteList<Integer> i = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).filter(x -> x % 4 == 0)
jshell> InfiniteList.iterate(1, x -> x + 1).limit(4).filter(x -> x % 2 == 0).toArray()
$.. ==> Object[2] { 2, 4 }
jshell> InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(4).toArray()
$.. ==> Object[4] { 2, 4, 6, 8 }
jshell> InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).filter(x -> x < 20).limit(5).toArray()
$.. ==> Object[5] { 11, 12, 13, 14, 15 }
jshell> InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x.hashCode() % 30).filter(x -> x < 20).limit(5).toArray()
$.. ==> Object[5] { 11, 12, 13, 14, 15 }
jshell> 
jshell> InfiniteList<Integer> i = InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 3)
jshell> InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 3).toArray()
$.. ==> Object[3] { 0, 1, 2 }
jshell> InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 0).toArray()
$.. ==> Object[0] {  }
jshell> InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).takeWhile(x -> x < 5).toArray()
$.. ==> Object[5] { 0, 1, 2, 3, 4 }
jshell> InfiniteList.iterate(0, x -> x + 1).map(x -> x).takeWhile(x -> x < 4).limit(1).toArray()
$.. ==> Object[1] { 0 }
jshell> InfiniteList.iterate(0, x -> x + 1).limit(4).takeWhile(x -> x < 2).toArray()
$.. ==> Object[2] { 0, 1 }
jshell> InfiniteList.iterate(0, x -> x + 1).map(x -> x * x).filter(x -> x > 10).limit(2).toArray()
$.. ==> Object[2] { 16, 25 }
jshell> InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x * x).limit(2).toArray()
$.. ==> Object[2] { 121, 144 }
jshell> 
jshell> Function<Object,List<Integer>> f = obj -> List.<Integer>of(obj.hashCode() % 100, obj.hashCode() % 10)
jshell> InfiniteList<List<? super Integer>> list = InfiniteList.iterate("", x -> "-" + x).map(f)
jshell> Predicate<Object> p = obj -> obj.hashCode() % 2 == 0
jshell> InfiniteList<String> s = InfiniteList.generate(() -> "A").filter(p)
jshell> InfiniteList<String> s = InfiniteList.<String>generate(() -> "A").takeWhile(p)
jshell> /exit

Check the format correctness of the output by typing the following Unix command

$ checkstyle *.java
$ javac -Xlint:rawtypes *.java
$ jshell -q < level3.jsh
Level 4: count and reduce
Now implement the following terminal operations by following the corresponding specifications of Java's Stream APIs:

long count()
Optional<T> reduce(BinaryOperator<T> accumulator)
T reduce(T identity, BinaryOperator<T> accumulator)
jshell> /open InfiniteList.java
jshell> /open InfiniteListImpl.java
jshell> InfiniteList.iterate(0, x -> x + 1).limit(0).count()
$.. ==> 0
jshell> InfiniteList.iterate(0, x -> x + 1).limit(1).count()
$.. ==> 1
jshell> InfiniteList.iterate(0, x -> x + 1).filter(x -> x % 2 == 1).limit(10).count()
$.. ==> 10
jshell> InfiniteList.iterate(0, x -> x + 1).limit(10).filter(x -> x % 2 == 1).count()
$.. ==> 5
jshell> InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).count()
$.. ==> 10
jshell> InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).filter(x -> x % 2 == 0).count()
$.. ==> 5
jshell> InfiniteList.iterate(0, x -> x + 1).limit(5).reduce(0, (x, y) -> x + y)
$.. ==> 10
jshell> InfiniteList.iterate(0, x -> x + 1).limit(0).reduce(0, (x, y) -> x + y)
$.. ==> 0
jshell> InfiniteList.iterate(0, x -> x + 1).map(x -> x * x).limit(5).reduce(0, (x, y) -> x + y)
$.. ==> 30
jshell> InfiniteList.iterate(0, x -> x + 1).limit(5).reduce((x, y) -> x + y)
$.. ==> Optional[10]
jshell> InfiniteList.iterate(0, x -> x + 1).limit(0).reduce((x, y) -> x + y)
$.. ==> Optional.empty
jshell> InfiniteList.iterate(0, x -> x + 1).map(x -> x * x).limit(5).reduce((x, y) -> x + y)
$.. ==> Optional[30]
jshell> /exit

Check the format correctness of the output by typing the following Unix command

$ checkstyle *.java
$ javac -Xlint:rawtypes *.java
$ jshell -q < level4.jsh
