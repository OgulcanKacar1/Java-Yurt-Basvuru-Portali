# Java-Yurt-Basvuru-Portali

Bu proje, İşık Üniversitesi yurt başvuru sürecini düzenlemek ve yurt yetkililerinin oda atamalarını daha etkin bir şekilde yönetmesini sağlamak için geliştirilmiştir. Sistem, JavaFX kullanarak masaüstü bir uygulama olarak tasarlanmıştır ve veritabanı entegrasyonu için MSSQL kullanılmaktadır.

## Özellikler
- **Öğrenci Kayıt ve Girişi:** Öğrenciler kendi hesaplarını oluşturup sisteme giriş yapabilir.
- **Yurt Başvurusu:** Öğrenciler uygun yurt ve oda seçeneklerini görerek başvuru yapabilir.
- **Başvuru Kontrol Sistemi:** İlgili öğrencinin hali hazırda bir başvurusu olup olmadığı kontrol edilir.
- **Yetkili Paneli:** Yurt yetkilileri odaları ve başvuruları görüntüleyebilir ve oda atamalarını yapabilir.
- **Şifre Değiştirme:** Kullanıcılar hesapları için şifre değişikliği yapabilir.
- **Oda Takibi:** Öğrenciler mevcut yurt ve oda bilgilerini görüntüleyebilir.

## Kullanılan Teknolojiler
- **Java**: Backend için
- **JavaFX**: Kullanıcı arayüzü
- **MSSQL**: Veritabanı
- **JDBC**: Veri tabanı bağlantısı
- **FXML & CSS**: UI tasarımı

## Kurulum
1. **Projeyi Klonlayın:**
    ```sh
    git clone https://github.com/OgulcanKacar1/Java-Yurt-Basvuru-Portali.git
    ```
2. **IntelliJ IDEA veya Eclipse ile Açın**
3. **Veritabanı Bağlantısını Ayarlayın:**
    - `DBConnection` sınıfında MSSQL bağlantı bilgilerini girin.
4. **Uygulamayı Çalıştırın:**
    - `Main.java` sınıfını çalıştırın.

## Veritabanı Yapısı
**Ana Tablolar:**
- **Ogrenci (ogrenciID, ad, soyad, mail, odaID FK)**
- **Oda (odaID, odaNo, yurtID FK)**
- **Yurt (yurtID, kategoriAdi)**
- **YurtYetkilisi (yetkiliID, ad, soyad, mail, sifre)**


## Geliştirici Bilgileri
- **Oğulcan Kacar**  
  - [GitHub](https://github.com/OgulcanKacar1)  
  - [LinkedIn]([https://www.linkedin.com/in/ogulcan-kacar/](https://www.linkedin.com/in/o%C4%9Fulcan-kacar-768ba0202/))

