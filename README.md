# PS
- Uygulama genelinde case pdf'inde belirtilen ve görülmek istenen kriterlere dikkat edildi. 
- Uygulamaya login için firebase authentication kullanıldı
- Favorileri tutmak için firebase store'da kullanıcı email'i ile uygun bir tablo oluşturuldu ve kullanıldı. 
- Giriş ve favoriler kısmı tamamen firebase server tarafında.
- Run time permission görülmek istenmiş. Ancak uygulamada internet haricinde bir izin kullanılmadığı için, görebilmeniz açısından location izni aldım girişte.
- SpaceX api upcoming launches kısmında, api'den bilgilerin çoğuna yakını null gelmekte. Dolayısıyla launches list ve detay sayfalarında çok fazla bilgi gösteremedim.Gelenleri göstermeye çalıştım. Yinede tüm classlarını,adapterlarını vs yazmaya çalıştım.

# Dummy USERS and PASSWORDS
- user1@gmail.com password : 123456
- user2@gmail.com password : 123456
- user3@gmail.com password : 123456
- user4@gmail.com password : 123456

# SpaceXFan Steps
- Project is created
- Project skeleton is created
- Base classes are added
- Spacex service ,repository, datasource, use case, response model classes are added
- Di modules are added
- Fragments are designed and connected with apis.
- Firebase authentication is connected
- Firebase store is connected to keep favourites
- Favourite operations ; add, remove, updates are fixed with api
- Run time permission is fixed
- Firebase crashlytics is added.

# TechStack
- Kotlin
- Hilt
- Retrofit 
- Fragment
- Navigation Component
- MVVM , Clean Arch.
- Courutines , LiveData
- Databinding , Viewbinding
- Firebase Auth , Firebase Store, Firebase Crashlytics
- Ktlint
- Proguard

