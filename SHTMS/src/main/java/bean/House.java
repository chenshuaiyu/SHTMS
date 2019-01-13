package main.java.bean;


public class House {
    private int id; // 房源ID
    private String province;
    private String city;
    private String county;
    private String communityName;
    private String buildingNo;
    private int floor;
    private int no; // 门牌号
    private int area;
    private float price;
    private int uId; // 户主ID
    private int propertyCost;
    private int isDecorated;
    private int hasGarage;
    private int roomNum;
    private int livingRoomNum;
    private int iId; //中介人员

    public House() {
    }

    public House(int id, String province, String city, String county, String communityName,
                 String buildingNo, int floor, int no, int area, float price,
                 int uId, int propertyCost, int isDecorated, int hasGarage,
                 int roomNum, int livingRoomNum, int iId) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.county = county;
        this.communityName = communityName;
        this.buildingNo = buildingNo;
        this.floor = floor;
        this.no = no;
        this.area = area;
        this.price = price;
        this.uId = uId;
        this.propertyCost = propertyCost;
        this.isDecorated = isDecorated;
        this.hasGarage = hasGarage;
        this.roomNum = roomNum;
        this.livingRoomNum = livingRoomNum;
        this.iId = iId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getPropertyCost() {
        return propertyCost;
    }

    public void setPropertyCost(int propertyCost) {
        this.propertyCost = propertyCost;
    }

    public int getIsDecorated() {
        return isDecorated;
    }

    public void setIsDecorated(int isDecorated) {
        this.isDecorated = isDecorated;
    }

    public int getHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(int hasGarage) {
        this.hasGarage = hasGarage;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getLivingRoomNum() {
        return livingRoomNum;
    }

    public void setLivingRoomNum(int livingRoomNum) {
        this.livingRoomNum = livingRoomNum;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }
}
