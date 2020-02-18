/open Box.java
/open BooleanCondition.java
/open AlwaysTrue.java
/open AlwaysFalse.java

Box.ofNullable(4).filter(new AlwaysTrue<>())
Box.ofNullable(null).filter(new AlwaysTrue<>())

Box.ofNullable("string").filter(new AlwaysFalse<>())
Box.empty().filter(new AlwaysFalse<>())
/exit
