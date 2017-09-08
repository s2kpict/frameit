package pict.s2k.frameit.users;

/**
 * Created by suryaa on 3/9/17.
 */

public class UserModel {

    public static class BasicPersonalInformation {
        private long mobile_no;
        BasicPersonalInformation(){}

        public BasicPersonalInformation(long mobile_no) {
            this.mobile_no = mobile_no;
        }

        public long getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(long mobile_no) {
            this.mobile_no = mobile_no;
        }
    }

    public static class Photo {
        private String thumbnail ;
        Photo(){}

        public Photo(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
