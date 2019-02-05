package app.Enum;

public enum ResType {
    Success("成功", 200), Fail("失败", 500), Logout("登出", 506);
        // 成员变量  
    private String name;
    private int index;

    public int getIndex() {
        return index;
    }

    // 构造方法  
    private ResType(String name, int index) {
        this.name = name;
        this.index = index;
    }
    //覆盖方法 
    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }
}
