package object;

import java.util.Date;

/**
 * Class DanhGiaPhanHoi: Lưu trữ thông tin đánh giá và phản hồi từ khách hàng.
 */
public class DanhGiaPhanHoi {
    private String maDanhGia;
    private String maKH;
    private int diemDanhGia;
    private String noiDung;
    private Date ngayDanhGia;

    /**
     * Constructor khởi tạo đối tượng đánh giá và phản hồi.
     * @param maDanhGia Mã đánh giá.
     * @param maKH Mã khách hàng.
     * @param diemDanhGia Điểm đánh giá từ 1 đến 5.
     * @param noiDung Nội dung phản hồi.
     * @param ngayDanhGia Ngày đánh giá.
     */
    public DanhGiaPhanHoi(String maDanhGia, String maKH, int diemDanhGia, String noiDung, Date ngayDanhGia) {
        this.maDanhGia = maDanhGia;
        this.maKH = maKH;
        setDiemDanhGia(diemDanhGia);
        this.noiDung = noiDung;
        this.ngayDanhGia = ngayDanhGia;
    }

    public String getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(String maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getDiemDanhGia() {
        return diemDanhGia;
    }

    /**
     * Đặt điểm đánh giá, phải từ 1 đến 5.
     * @param diemDanhGia Điểm đánh giá.
     * @throws IllegalArgumentException nếu điểm đánh giá không hợp lệ.
     */
    public void setDiemDanhGia(int diemDanhGia) {
        if (diemDanhGia < 1 || diemDanhGia > 5) {
            throw new IllegalArgumentException("Điểm đánh giá phải từ 1 đến 5!");
        }
        this.diemDanhGia = diemDanhGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(Date ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    @Override
    public String toString() {
        return "DanhGiaPhanHoi{" +
                "maDanhGia='" + maDanhGia + '\'' +
                ", maKH='" + maKH + '\'' +
                ", diemDanhGia=" + diemDanhGia +
                ", noiDung='" + noiDung + '\'' +
                ", ngayDanhGia=" + ngayDanhGia +
                '}';
    }
}
