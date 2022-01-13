package kr.co.goodee39.vo;

public class BBSVO {
   private int num;
   private String title;
   private String content;
   private String ownerid;
   private String ownername;
   private String createdate;
   private String isdelete;
   private String filelist;
   private int start;
   private int count;
   
   public BBSVO() {
      this.start = 0;
      this.count = 10;
   }
   
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getOwnerid() {
      return ownerid;
   }
   public void setOwnerid(String ownerid) {
      this.ownerid = ownerid;
   }
   public String getOwnername() {
      return ownername;
   }
   public void setOwnername(String ownername) {
      this.ownername = ownername;
   }
   public String getCreatedate() {
      return createdate;
   }
   public void setCreatedate(String createdate) {
      this.createdate = createdate;
   }
   public String getIsdelete() {
      return isdelete;
   }
   public void setIsdelete(String isdelete) {
      this.isdelete = isdelete;
   }
   public int getStart() {
      return start;
   }
   public void setStart(int start) {
      this.start = start;
   }
   public int getCount() {
      return count;
   }
   public void setCount(int count) {
      this.count = count;
   }

   public String getFilelist() {
      return filelist;
   }

   public void setFilelist(String filelist) {
      this.filelist = filelist;
   }
   
   
}