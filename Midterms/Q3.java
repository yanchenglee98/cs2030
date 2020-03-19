   import java.util.List;
   import java.util.ArrayList;

   enum ContactNature    {  CASUAL, CLOSE, FAMILY}

   class Contact {
      ContactNature contactNature;
      Case case1;
      Case case2;
   }

   class ImportedCase extends Case  {
      String countryOfOrigin;
   }

   class LocalCase extends Case {

   }

   abstract class Case {
      int ID;

   }

   class Cluster {
      String clusterName;
      List<Case> listOfCases;

   }

