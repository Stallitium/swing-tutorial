package stallitium;

public class People {
    private String name;
    private String id;
    private String type;

    public People(String saveData) {
        makePeople(saveData);
    }

    public People(String name,String comment,String type) {
        this.name = name;
        this.id = comment;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getForSave() {
        return name+"#"+ id +"#"+type;
    }

    void makePeople(String string) {
        String[] str = string.split("#");
        if (str.length != 3) {
            try {
                if (str.length > 3) {
                    throw new SaveDataException("save data is shortage"+str.toString());
                } else {
                    throw new SaveDataException("save data is excessive"+str.toString());
                }
            } catch (SaveDataException e) {
                throw new RuntimeException(e);
            }
        }
        name = str[0];
        id = str[1];
        type = str[2];
    }
}
