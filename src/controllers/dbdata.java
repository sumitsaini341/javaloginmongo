package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class dbdata {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty cname;
    private final SimpleStringProperty cfname;
    private final SimpleStringProperty cmname;
    private final SimpleStringProperty cemail;
    private final SimpleStringProperty dob;
    private final SimpleStringProperty cgender;
    private final SimpleStringProperty caddress;
    private final SimpleStringProperty ccity;
    private final SimpleStringProperty zipcode;

    public dbdata(Integer id, String cname, String cfname, String cmname, String cemail, String dob,String cgender,String caddress,String ccity,String zipcode)
    {
        this.id = new SimpleIntegerProperty(id);
        this.cname = new SimpleStringProperty(cname);
        this.cfname = new SimpleStringProperty(cfname);
        this.cmname = new SimpleStringProperty(cmname);
        this.cemail = new SimpleStringProperty(cemail);
        this.dob = new SimpleStringProperty(dob);
        this.cgender = new SimpleStringProperty(cgender);
        this.caddress = new SimpleStringProperty(caddress);
        this.ccity = new SimpleStringProperty(ccity);
        this.zipcode = new SimpleStringProperty(zipcode);


    }
        public Integer getId() {return id.get();}
        public String getCname() {return cname.get();}
        public String getCfname() {return cfname.get();}
        public String getCmname() {return cmname.get();}
        public String getCemail() {return cemail.get();}
        public String getDob() {return dob.get();}
        public String getCgender() {return cgender.get();}
        public String getCaddress() {return caddress.get();}
        public String getCcity() {return ccity.get();}
        public String getZipcide(){return zipcode.get();}

        public void setCname(String childname){cname.set(childname); }
        public void setCfname(String fathername){cfname.set(fathername); }
        public void setCmname (String mothername){cmname.set(mothername);}
        public void setCemail (String childemail){cemail.set(childemail);}
        public void setDob (String dateOFbirth) {dob.set(dateOFbirth);}
        public void setCgender (String gender){cgender.set(gender);}
        public void setCaddress (String address){caddress.set(address);}
        public void setCcity (String city){ccity.set(city);}
        public void setZipcode(String zip){zipcode.set(zip);}



}