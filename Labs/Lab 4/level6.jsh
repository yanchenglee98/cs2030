/open BooleanCondition.java
/open Box.java
/open Transformer.java
/open LastDigitsOfHashCode.java
/open DivisibleBy.java
/open BoxIt.java

Box.of(4).map(new BoxIt<>())
Box.of(Box.of(5)).map(new BoxIt<>())
Box.ofNullable(null).map(new BoxIt<>())

class A {}

Box.of(new A()).map(new BoxIt<>())
