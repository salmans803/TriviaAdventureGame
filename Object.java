public class Object {
   private String name;
   private String purpose;
   public Object(){
   }
   public Object(String name, String purpose) {
      this.name = name;
      this.purpose = purpose;
   }
   public String getObjectName() {
      return name;
   }
   public String getObjectPurpose() {
      return purpose;
   }
   public void setName() {
      this.name = name;
   }
   public void setPurpose() {
      this.purpose = purpose;
   }
   @Override
   public String toString() {
      return "Object name = " + name + " , purpose = " + purpose;
   }
   
}