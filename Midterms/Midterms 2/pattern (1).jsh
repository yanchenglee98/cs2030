 
   String pattern(int n) {
      return Stream.iterate(1, x -> x +1)
                              .map(y -> Stream.iterate(y, i -> i > 0, z -> z -1)
                                                            .map(j -> j.toString)
                                                            .reduce("", (x, y) -> x+y))
                              .limit(n)
                              .reduce("", (x, y) -> x+y+" ");
   }
