/open Box.java
/open BooleanCondition.java
/open DivisibleBy.java
/open LongerThan.java

new DivisibleBy(5).test(4)
new DivisibleBy(5).test(10)
Box.of(10).filter(new DivisibleBy(2))
Box.of(3).filter(new DivisibleBy(2))
Box.of("hello").filter(new DivisibleBy(10))
Box<Integer> empty = Box.empty()
empty.filter(new DivisibleBy(10))

new LongerThan(6).test("123456")
new LongerThan(3).test("")
Box.of("chocolates").filter(new LongerThan(20))
Box.of("string").filter(new LongerThan(4))
Box<String> empty = Box.empty()
empty.filter(new LongerThan(10))
