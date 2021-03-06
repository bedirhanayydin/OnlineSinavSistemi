**Java ve Firebase kullanılarak yaptığım staj projem.**            

                      Açık Öğretim Fakültesi Online Sınav Sistemi
Makale Değerlendirme Sistemi sisteminde yetki ve roller şu şekildedir:
1. Sistem yöneticisi sadece masa üstü uygulama üzerinden sistemi kullanabilecektir.
2. Sisteme giriş yapacak olan öğrenci ve hoca mobil uygulaması ve web uygulaması üzerinden
sistemi kullanabilecektir.
3. Yönetici, hocaların sisteme kaydı, günceleme, silme süreçlerini yürütecektir
4. Hoca, alanı ile ilgili çoktan seçmeli soruyu sisteme kaydetme, güncelleme, silme, listeleme ve
ayrıca soru seviyesini (kolay-orta-zor) sisteme kaydetme yetkisine sahiptir.
5. Sınavın başlaması, bitişi, verilen süre, soru zorluk derecesi, soru puanlama gibi tanımlamalar
hoca tarafından sınav başlamasından en geç 1 saat önce sisteme kaydedilecektir. Aksi takdirde
kaydedilen tarihten 1 saat sonraki zamanda sınav başlatılacaktır.
6. Hoca, her bir zorluk derecesindeki soruların kaç puana karşılık geldiğini belirtecek ve bunun
ardından puanlamaya esas toplam değer mutlaka 100 puan olmalıdır.
Örnek: 5 tane zor, her biri 10 puan
20 tane kolay, her biri 2.5 puan
Olmak üzere toplam puan = 100 puan
7. Alan hocası online sınavını başlatmak için havuzdaki mevcut soru sınırını aşmamak kaydıyla
istediği kadar sorudan oluşan bir online sınav oluşturabilecektir. Bu soruların havuzdan
alınması esnasında karşılaşılabilecek senaryolar şunlardır:
a. Hoca, havuzdaki sorulardan fazla soru olmasını istemiş olabilir.
b. Hoca, soru zorluk derecesi seçiminde sistemde mevcut sorulardan fazla soru talep etmiş
olabilir.
Yukarıdaki her iki durum için online sınavda olabilecek soru sayısı havuzdaki ilgili kategorideki
soruların sınır sayısı olacaktır. Puanlamaya esas eksik soru var ise sistem uyaracaktır. Yani, sınav
sorularının toplam puanı mutlaka 100 olmalıdır.
8. Hocalar, alanlarında farklı hocalar tarafından sisteme girilmiş olan soruları listeleyebilecektir.
Hocanın hatalı olarak değerlendirdiği bir soru olursa bununla ilgili olarak o alandaki tüm
hocalara bilgilendirme maili iletilecek ve hocaların oylaması sonucu sorunun hatalı olup
olmadığına sistem karar verecektir. Oylama sonucu hatalı çıkarsa o soru öğrenci ekranında
listelenmeyecektir.
9. Soruların şıkları randomize olarak online sınav ekranında yerleştirilmelidir. Örneğin, bir
öğrencinin ekranında doğru cevap A iken diğer öğrencinin ekranında doğru cevap E olabilir.
Aynı durum, diğer doğru olmayan şıklar içinde geçerlidir.
10. Öğrenci, sistemdeki soruları listeleme ve cevaplama yetkisine sahiptir. Her öğrencinin cevabı
sistemde online olarak sınav bitiminde puanlandırılacaktır. 
