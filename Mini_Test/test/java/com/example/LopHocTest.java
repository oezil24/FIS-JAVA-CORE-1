package test.java.com.example;

import com.example.Diem;
import com.example.LopHoc;
import com.example.MonHoc;
import com.example.SinhVien;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LopHocTest {
    LopHoc lopHoc;
    Set<Diem> diems;
    public LopHocTest(){
        lopHoc = new LopHoc("FIS_RA","Phuoc");
        List<SinhVien> sinhViens  = new ArrayList<>();
        diems = new HashSet<>();
        diems.add(new Diem(new MonHoc("Toan",1,2),7));
        diems.add(new Diem(new MonHoc("Ly",2,3),6));
        diems.add(new Diem(new MonHoc("Hoa",5,4),5));
        sinhViens.add(new SinhVien("12345","Duc",diems));
        sinhViens.add(new SinhVien("12346","Anh",diems));
        sinhViens.add((new SinhVien("12347","Ha",diems)));
        lopHoc.setDsLop(sinhViens);


    }
    @Test
    void them() {
        lopHoc.them(new SinhVien("12348","Quynh",diems));
        SinhVien sv = lopHoc.getDsLop().get(lopHoc.getDsLop().size()-1);
        assertEquals(sv,lopHoc.getDsLop().get(lopHoc.getDsLop().size()-1));
        inDiem();
    }

    @Test
    void inDiem() {
        System.out.println(lopHoc.inDiem());
    }

    @Test
    void top10() {
        for(SinhVien sv : lopHoc.getDsLop()){
            System.out.println(sv);
        }
    }

    @Test
    void sinhVienYeu() {
        this.lopHoc.top10().forEach(sinhVien -> System.out.println(sinhVien));
    }
}