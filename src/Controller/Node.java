package Controller;

public class Node {
    private String key;
    private Node right;
    private Node left;
    private Node parent;
    private boolean red;
    private String value;
    private String deskripsi;
    private String description;

    public Node(String key, String value, String deskripsi, String description) {
        this.key = key.toLowerCase();
        this.value = value.toLowerCase();
        this.deskripsi = deskripsi.toLowerCase();
        this.description = description.toLowerCase();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getDescription() {
        return description;
    }

    public String getGimmick() {
        if (key.compareTo("gunung") == 0 || key.compareTo("mountain") == 0) {
            return "gambar";
        }
        if (key.compareTo("musik") == 0 || key.compareTo("music") == 0) {
            return "play";
        }
        if (key.compareTo("mobil") == 0 || key.compareTo("car") == 0) {
            return "kendaraan";
        }
        if (key.compareTo("biru") == 0 || key.compareTo("blue") == 0) {
            return "color";
        }
        if (key.compareTo("peta") == 0 || key.compareTo("maps") == 0) {
            return "link";
        }
        return "none";
    }
}
