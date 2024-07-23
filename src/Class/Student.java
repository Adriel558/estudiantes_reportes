package Class;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String lastName;
    private Date birthDate;
    private Status stateId;
    private CivilStatus maritalStatusId;
    private Document documentId;

    // Constructor
    public Student(int id, String name, String lastName, Date birthDate, Status stateId, CivilStatus maritalStatusId, Document documentId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.stateId = stateId;
        this.maritalStatusId = maritalStatusId;
        this.documentId = documentId;
    }

    public Student(int id, String name, String lastName, Date birthDate, int stateId, int maritalStatusId, int documentId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.stateId = new Status(stateId, null);
        this.maritalStatusId = new CivilStatus(maritalStatusId, null);
        this.documentId = new Document(documentId, null, "0");
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getStateId() {
        return stateId.getId();
    }

    public String getState() {
        return stateId.getName();
    }

    public void setStateId(int stateId) {
        this.stateId.setId(stateId);
    }

    public String getMaritalStatus() {
        return maritalStatusId.getStatus();
    }

    public int getMaritalStatusId() {
        return maritalStatusId.getId();
    }

    public void setMaritalStatusId(int maritalStatusId) {
        this.maritalStatusId.setId(maritalStatusId);
    }

    public int getDocumentId() {
        return documentId.getId();
    }

    public String getDocument() {
        return documentId.getType();
    }

    public void setDocumentId(int documentId) {
        this.documentId.setId(documentId);
    }
}
