public class ItemType {
    private int value;
    public int compareTo(ItemType item){
        if(value==item.getValue()){
            return 0;
        }else if(value < item.getValue()){
            return -1;
        }else {
            return 1;
        }
    }
    // returns inputed value
    public int getValue(){
        return value;
    }
    // assigns inputed value
    public ItemType(int value) {
        this.value = value;
    }
}