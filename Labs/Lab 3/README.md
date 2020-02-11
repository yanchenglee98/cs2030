3D Shapes
Topic Coverage
Abstract Classes
Interfaces
Composition
Design Principles
Problem Description
In this lab, we are going to explore different design options for modeling 3D objects. In particular, we wish to model different 3D shapes. We can compute the volume and surface area of a given shape. For each shape, there is a possibility that the shape is solid with a given density. From the density and the volume, we can compute its mass.

For instance, a cuboid is a box-shaped object having six flat sides with all right-angled corners. From the three sides of the solid cuboid, one can compute the volume and surface area. In addition, a solid cuboid object is made up of a specific material, and by using the density of the material, the mass can be found.

Task
By using object-oriented modeling and applying the abstraction principle, design two different sets of classes/interfaces to facilitate the creation of a 3D shape and a solid 3D shape, starting with a cuboid and solid cuboid. Show how you can obtain the cuboid's volume, density as well as mass. Extend this concept to deal with other possible 3D shapes such as a sphere.

Your design should contain as little repetition of code (including method implementation and member declaration) as possible.

© Copyright 2009-2020 National University of Singapore. All Rights Reserved.

Terms of Use | Privacy | Non-discrimination

MySoC | Computing Facilities | Search | Campus Map
School of Computing, National University of Singapore

Level 1
Represent a Cuboid
Create an immutable class Cuboid. Its constructor has three parameters height, width, and length (in that order). All are of type double. Like all 3D shapes, Cuboid has a method getVolume() and getSurfaceArea(). The class has three new methods: Cuboid setHeight(double height), Cuboid setWidth(double width), and Cuboid setLength(double length).

The string representation of a Cuboid is in the form:

Cuboid [Height x Width x Length]
Height, Width, and Length will be to 2 decimal places. Recall from lab 1, the toString() method in the Point class.

jshell> new Cuboid(2,2,2)
$.. ==> Cuboid [2.00 x 2.00 x 2.00]
jshell> new Cuboid(2,2,2).getVolume()
$.. ==> 8.0
jshell> new Cuboid(2,2,2).getSurfaceArea()
$.. ==> 24.0
jshell> new Cuboid(2,2,2).setHeight(3)
$.. ==> Cuboid [3.00 x 2.00 x 2.00]
jshell> new Cuboid(2,2,2).setWidth(3)
$.. ==> Cuboid [2.00 x 3.00 x 2.00]
jshell> new Cuboid(2,2,2).setLength(3)
$.. ==> Cuboid [2.00 x 2.00 x 3.00]
jshell> new Cuboid(2,2,2).setWidth(5).setHeight(3)
$.. ==> Cuboid [3.00 x 5.00 x 2.00]
jshell> new Cuboid(2,2,2).setWidth(5).setHeight(3).getVolume()
$.. ==> 30.0
jshell> /exit
Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q [list of java files in order of dependency] < level1.jsh
Check your styling by issuing the following
$ checkstyle *.java
Click here to submit to CodeCrunch.

Level 2
Represent a SolidCuboid
Create an immutable class SolidCuboid. The SolidCuboid class has a constructor that takes in four parameters height, width, length, and density (in that order). All are of type double. Like all solid shapes, SolidCuboid has two methods getDensity() and getMass() that returns the density and the mass respectively.

The string representation of a SolidCuboid is in the form:

SolidCuboid [Height x Width x Length] with a mass of Mass
jshell> new SolidCuboid(2,2,2,0.5)
$.. ==> SolidCuboid [2.00 x 2.00 x 2.00] with a mass of 4.00
jshell> new SolidCuboid(2,2,2,0.5).getVolume()
$.. ==> 8.0
jshell> new SolidCuboid(2,2,2,0.5).getSurfaceArea()
$.. ==> 24.0
jshell> new SolidCuboid(2,2,2,0.5).getDensity()
$.. ==> 0.5
jshell> new SolidCuboid(2,2,2,0.5).getMass()
$.. ==> 4.0
jshell> new SolidCuboid(2,2,2,0.5).setHeight(2).getMass()
$.. ==> 4.0
jshell> /exit
Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q [list of java files in order of dependency] < level2.jsh
Check your styling by issuing the following
$ checkstyle *.java
Click here to submit to CodeCrunch.

Level 3
Introducing a new shape Sphere
Create an immutable class Sphere, its constructor has one parameter radius of type double. The class has one new method: Sphere setRadius(double radius).

The string representation of a Sphere is in the form:

Sphere [Radius]
Radius will be to 2 decimal places.

You may wish to revisit your solution for the previous levels and change its design to improve adherence to the abstraction principle.

jshell> new Sphere(2);
$.. ==> Sphere [2.00]
jshell> new Sphere(2).getVolume()
$.. ==> 33.510321638291124
jshell> new Sphere(2).getSurfaceArea()
$.. ==> 50.26548245743669
jshell> new Sphere(2).setRadius(3)
$.. ==> Sphere [3.00]
jshell> new Sphere(2).setRadius(3).getVolume()
$.. ==> 113.09733552923255
jshell> /exit
Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q [list of java files in order of dependency] < level3.jsh
Check your styling by issuing the following
$ checkstyle *.java
Click here to submit to CodeCrunch.

Level 4
Design the SolidSphere class
Create an immutable class SolidSphere. The SolidSphere class has a constructor which takes in two parameters radius, and density (in that order). All are of type double.

The string representation of a SolidSphere is in the form:

SolidSphere [Radius] with a mass of Mass
You may wish to revisit your solution for the previous levels and change its design to improve adherence to the abstraction principle.

jshell> new SolidSphere(2,0.5)
$.. ==> SolidSphere [2.00] with a mass of 16.76
jshell> new SolidSphere(2,0.5).getVolume()
$.. ==> 33.510321638291124
jshell> new SolidSphere(2,0.5).getSurfaceArea()
$.. ==> 50.26548245743669
jshell> new SolidSphere(2,0.5).getDensity()
$.. ==> 0.5
jshell> new SolidSphere(2,0.5).getMass()
$.. ==> 16.755160819145562
jshell> new SolidSphere(2,0.5).setRadius(3)
$.. ==> SolidSphere [3.00] with a mass of 56.55
jshell> new SolidSphere(2,0.5).setRadius(3).getMass()
$.. ==> 56.548667764616276
jshell> /exit
Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q [list of java files in order of dependency] < level4.jsh
Check your styling by issuing the following
$ checkstyle *.java
Click here to submit to CodeCrunch.

Level 5
SolidShape3D using Composition
You should see that it is not easy to model the four classes, Cuboid, SolidCuboid, Sphere, and SolidSphere, above without some repetition of code, violating the abstraction principles. In this level, we will explore a different design.

A density is really a property of a material not of a shape. Let's create a Material class, with a constructor that takes in two parameters, the name of the material and the density of the material. The first term is a string and the second term is a double.

What if we created a new class that contains both a Shape3D and a Material. Implement a class SolidShape3D which has a constructor that takes in a Shape3D and a Material (in that order). The class should implement a double getMass() and double getDensity() methods.

Would this SolidShape3D class still work if we created a new class Prism that inherits from Shape3D?

jshell> Material glass = new Material("Glass",2.5)
jshell> Material steel = new Material("Steel",7870)
jshell> new SolidShape3D(new Cuboid(2,2,2),glass)
$.. ==> SolidCuboid [2.00 x 2.00 x 2.00] with a mass of 20.00
jshell> new SolidShape3D(new Cuboid(2,2,2),steel)
$.. ==> SolidCuboid [2.00 x 2.00 x 2.00] with a mass of 62960.00
jshell> new SolidShape3D(new Cuboid(2,2,2),steel).getMass()
$.. ==> 62960.0
jshell> new SolidShape3D(new Cuboid(2,2,2).setHeight(3),glass)
$.. ==> SolidCuboid [3.00 x 2.00 x 2.00] with a mass of 30.00
jshell> new SolidShape3D(new Sphere(2),glass)
$.. ==> SolidSphere [2.00] with a mass of 83.78
jshell> new SolidShape3D(new Sphere(2),steel)
$.. ==> SolidSphere [2.00] with a mass of 263726.23
jshell> new SolidShape3D(new Sphere(2),steel).getMass()
$.. ==> 263726.23129335116
jshell> new SolidShape3D(new Sphere(2).setRadius(3),glass)
$.. ==> SolidSphere [3.00] with a mass of 282.74
jshell> /exit
Check the format correctness of the output by running the following on the command line:

$ javac *.java
$ jshell -q [list of java files in order of dependency] < level5.jsh
Check your styling by issuing the following
$ checkstyle *.java
Click here to submit to CodeCrunch.
