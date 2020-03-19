   import java.util.List;
   import java.util.ArrayList;
   enum Contact {  CASUAL, CLOSE, FAMILY}
   interface ConfirmedCases {
      public int getID();
      public Contact getContact();
   }
   class ImportedCase implements ConfirmedCases {
      int ID;
      Contact natureOfContact;
      int numberOfContacts;
      String countryOfOrigin;
   }
   class LocalCase implements ConfirmedCases {
      int ID;
      Contact natureOfContact;
      int numberOfContacts;
   }
   class Cluster {
      String ClusterName;
      List<ConfirmedCases> listOfCases;
   }
   public class Main{}
