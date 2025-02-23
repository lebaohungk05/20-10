package object;

/**
 * Class DichVu: Quản lý các dịch vụ khách sạn như ăn uống, spa, giặt là, thuê xe.
 */
public class DichVu {
    private String maDichVu;
    private String tenDichVu;
    private double giaDichVu;

    /**
     * Constructor khởi tạo dịch vụ.
     * @param maDichVu Mã dịch vụ duy nhất.
     * @param tenDichVu Tên dịch vụ.
     * @param giaDichVu Giá dịch vụ.
     */
    public DichVu(String maDichVu, String tenDichVu, double giaDichVu) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        setGiaDichVu(giaDichVu);
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public double getGiaDichVu() {
        return giaDichVu;
    }

    /**
     * Đặt giá dịch vụ, không cho phép giá trị âm.
     * @param giaDichVu Giá dịch vụ.
     * @throws IllegalArgumentException nếu giá trị âm.
     */
    public void setGiaDichVu(double giaDichVu) {
        if (giaDichVu >= 0) {
            this.giaDichVu = giaDichVu;
        } else {
            throw new IllegalArgumentException("Giá dịch vụ không thể âm!");
        }
    }

    @Override
    public String toString() {
        return "DichVu{" + "maDichVu='" + maDichVu + '\'' + ", tenDichVu='" + tenDichVu + '\'' + ", giaDichVu=" + giaDichVu + '}';
    }
}
